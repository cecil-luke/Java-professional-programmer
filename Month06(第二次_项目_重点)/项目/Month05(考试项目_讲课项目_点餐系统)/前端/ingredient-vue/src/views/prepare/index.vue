<template>
  <div>
    <!-- 本次预制菜品 -->
    <el-table
      :data="cartList"
      size="mini"
      style="width: 60%; margin-bottom: 20px">
      <el-table-column
        prop="name"
        label="菜品名称"
        width="200">
      </el-table-column>
      <el-table-column
        label="数量"
        width="180"
        align="center">
        <template slot-scope="{row}">
          <el-input-number
            v-model="row.num"
            :min="1"
            :max="10"
            size="small"
            @change="numChange(row)">
          </el-input-number>
        </template>
      </el-table-column>
      <el-table-column align="center">
        <template slot-scope="{row}">
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            plain
            @click="deleteIngredient(row)"
          >删 除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-if="cartList.length" style="display: flex; flex-direction: row-reverse">
      <el-button
        plain
        type="primary"
        icon="el-icon-plus"
        size="mini"
        :disabled="!prepareTime"
        @click="createOrder"
        >预制
      </el-button>

      <el-date-picker
        v-model="prepareTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        size="mini"
        placeholder="请选择预制时间"
        >
      </el-date-picker>
    </div>

    <!-- 查询表单 -->
    <el-form
      ref="queryForm"
      :model="queryForm"
      label-width="80px"
      size="mini"
      inline>
      <!-- 主要食材 -->
      <el-form-item prop="mainIngredient">
        <el-select
          v-model="queryForm.mainIngredient"
          clearable
          placeholder="请选择主要食材">
          <el-option
            v-for="type in mainIngredientList"
            :key="type.name"
            :label="type.name"
            :value="type.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 辅助食材 -->
      <el-form-item prop="minorIngredient">
        <el-select
          v-model="queryForm.minorIngredient"
          clearable
          placeholder="请选择辅助食材">
          <el-option
            v-for="type in minorIngredientList"
            :key="type.name"
            :label="type.name"
            :value="type.id"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 调料 -->
      <el-form-item prop="seasoning">
        <el-select
          v-model="queryForm.seasoning"
          clearable
          placeholder="请选择调料">
          <el-option
            v-for="type in seasoningList"
            :key="type.name"
            :label="type.name"
            :value="type.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          plain
          icon="el-icon-refresh"
          type="info"
          @click="resetQueryForm"
        >重置
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 列表数据 -->
    <el-row>
      <el-col :span="6" v-for="item in tableData" :key="item.id">
        <el-card :body-style="{ padding: '0px' }" style="margin: 5px" shadow="hover">
          <img :src="base_url + '/ingredient-sys' + item.url" class="image">
          <div style="padding: 14px;">
            <div class="card">
              <span>{{ item.name }}</span>
              <el-button type="text" @click="addToCart(item)">加入预制订单</el-button>
            </div>
            <div class="card-unit">
              <span>主要食材：{{ item.mainIngredientName }}</span>
            </div>
            <div class="card-unit">
              <span>辅助食材：{{ item.minorIngredientName }}</span>
            </div>
            <div class="card-unit">
              <span>调料：{{ item.seasoningName }}</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 分页 -->
    <el-pagination
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[8, 12, 16, 20, 100]"
      :current-page="pageNumber"
      :page-size="pageSize"
      :total="total"
      background
      @current-change="pageNumberChange"
      @size-change="pageSizeChange"
    ></el-pagination>
  </div>
</template>

<script>
import {getTypeList, getIngredientList} from "@/api/ingredient";
import {modifyCart, getCartList, deleteIngredient} from '@/api/cart'
import {createOrder} from '@/api/order'

export default {
  name: "index",
  data() {
    return {

      cartList: [], // 预制菜品列表

      mainIngredientList: [], // 主要食材列表
      minorIngredientList: [], // 辅助食材列表
      seasoningList: [], // 调料列表

      pageNumber: 1,
      pageSize: 8,
      total: 0,
      tableData: [],

      queryForm: {
        ingredientType: 1, // 默认查询菜品
        mainIngredient: '', // 主要食材id
        minorIngredient: '', // 辅助食材id
        seasoning: '' // 调料id
      },

      prepareTime: '', // 预制时间
    }
  },

  watch: {
    /* 监听queryForm的变化 */
    queryForm: {
      deep: true,
      handler: function (newVal, oldVal) {
        this.pageNumber = 1
        this.getTableData()
      }
    }
  },

  methods: {
    /* 获取食材 */
    async getTypeList() {
      const {data} = await getTypeList()
      this.mainIngredientList = data.mainIngredientList
      this.minorIngredientList = data.minorIngredientList
      this.seasoningList = data.seasoningList
    },

    /* 查询列表数据 */
    async getTableData() {
      // resp = {code: 200, message: 'OK', data: { pageNumber: 1, pageSize: 8, rows: [], total: 3 }}
      const {data: {pageNumber: pageNum, pageSize, rows, total}}
        = await getIngredientList(this.pageNumber, this.pageSize, this.queryForm)
      this.pageNumber = pageNum
      this.pageSize = pageSize
      this.tableData = rows
      this.total = total
    },

    pageNumberChange(pageNumber) {
      this.pageNumber = pageNumber
      this.getTableData()
    },

    pageSizeChange(pageSize) {
      this.pageSize = pageSize
      this.getTableData()
    },

    /* 重置查询表单 */
    resetQueryForm() {
      this.$refs.queryForm.resetFields()
    },

    /* 购物车中菜品列表 */
    getCartList() {
      getCartList().then(resp => {
        this.cartList = resp.data
      }).catch(e => {
        console.log(e)
      })
    },

    addToCart(item) {
      let ingredient = this.cartList.find(x => x.id === item.id)

      if (!ingredient) {
        ingredient = {id: item.id, name: item.name, num: 1}
        this.cartList.push(ingredient)
      } else {
        ingredient.num += 1
      }

      modifyCart(ingredient).then(resp => {
        const {code, message} = resp
        if (code === 200) {
          this.$message.success('添加成功！')
        } else {
          this.$message.error(message)
        }
      }).catch(e => {
        console.log(e)
      })
    },

    /* 点击加减号 */
    numChange(ingredient) {
      modifyCart(ingredient).then(resp => {
        const {code, message} = resp
        if (code === 200) {
          this.$message.success('修改成功！')
        } else {
          this.$message.error(message)
        }
      }).catch(e => {
        console.log(e)
      })
    },

    deleteIngredient(row) {
      deleteIngredient(row.id).then(resp => {
        const {code, message} = resp
        if (code === 200) {
          this.$message.success('删除成功！')

          // 获取元素的索引值
          let index = this.cartList.indexOf(row)

          // 第一个参数是起始索引
          this.cartList.splice(index, 1)
        } else {
          this.$message.error(message)
        }
      }).catch(e => {
        console.log(e)
      })
    },
    /* 提交预制菜品 */
    createOrder() {
      createOrder(this.prepareTime, this.cartList).then(resp => {
        const {code, message} = resp
        if (code === 200) {
          this.$message.success('添加预制订单成功！')
          this.prepareTime = ''
          this.cartList = []

        } else {
          this.$message.error(message)
        }
      }).catch(e => {
        console.log(e)
      })
    },
    
  },

  mounted() {
    this.getTypeList()
    this.getTableData()
    this.getCartList()
  }
}
</script>

<style scoped>
.image {
  width: 100%;
  display: block;
}

.card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #1B1B1B;
  line-height: 1.4;
  height: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 14px;
  font-weight: bold;
  white-space: nowrap;
}

.card-unit {
  margin-top: 5px;
  font-size: 12px;
  color: #999;
}
</style>