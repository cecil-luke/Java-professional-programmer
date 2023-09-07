<template>
  <div>
    <!-- 1、查询表单 -->
    <el-form
      ref="queryForm"
      :model="queryForm"
      size="mini"
      inline
      label-position="left"
      label-width="40px">
      <!-- 名称 -->
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryForm.name"
          @keyup.enter.native="search"
          placeholder="请输入名称">
        </el-input>
      </el-form-item>
      <!-- 类型 -->
      <el-form-item label="类型" prop="ingredientType">
        <el-select
          v-model="queryForm.ingredientType"
          placeholder="请选择类型"
          @change="search"
          clearable>
          <el-option
            v-for="dict in ingredientTypeList"
            :key="dict.name"
            :label="dict.name"
            :value="dict.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          plain
          icon="el-icon-search"
          @click="search"
        >查 询
        </el-button>
        <el-button
          type="info"
          plain
          icon="el-icon-refresh"
          @click="resetQueryForm"
        >重 置
        </el-button>
      </el-form-item>
      <el-form-item style="float: right">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          @click="handleAdd"
        >添 加
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 2、表格数据 -->
    <el-table
      :data="tableData"
      border
      stripe
      size="mini"
      style="width: 100%">
      <el-table-column label="名称" prop="name"></el-table-column>
      <el-table-column label="类型">
        <template slot-scope="{ row }">
          <el-tag type="danger">{{ getDictName(ingredientTypeList, row.ingredientType) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center" width="100px">
        <template slot-scope="{ row }">
          <el-image
            :src="base_url + '/ingredient-sys' + row.url"
            style="width: 40px; height: 40px"
            :preview-src-list="(base_url + '/ingredient-sys' + row.url).split()"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="主要食材" prop="mainIngredientName"></el-table-column>
      <el-table-column label="辅助食材" prop="minorIngredientName"></el-table-column>
      <el-table-column label="调料" prop="seasoningName"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="{ row }">
          <el-button
            size="mini"
            plain
            type="primary"
            icon="el-icon-edit"
            circle
            @click="updateIngredient(row)"
          ></el-button>
          <el-button
            size="mini"
            plain
            type="danger"
            icon="el-icon-delete"
            circle
            @click="deleteIngredient(row.id)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 3、分页条 -->
    <el-pagination
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      background
      :page-sizes="[5, 10, 15, 20, 50]"
      :page-size="pageSize"
      :current-page="pageNumber"
      @current-change="pageNumberChange"
      @size-change="pageSizeChange"
    >
    </el-pagination>

    <!-- 4、添加、修改对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="ingredientFormVisible"
      :close-on-click-modal="false">
      <el-form
        ref="ingredientForm"
        :model="ingredientForm"
        :rules="ingredientFormRules"
        label-width="110px"
        size="mini">
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="ingredientForm.name"
            placeholder="请输入名称">
          </el-input>
        </el-form-item>
        <!-- 上传 -->
        <el-form-item label="图片" prop="url">
          <el-upload
            :headers="uploadHeaders"
            :action="base_url + '/ingredient-sys/upload/image'"
            :file-list="fileList"
            list-type="picture"
            :on-success="uploadSuccess"
            :before-upload="beforeUpload"
            :limit="fileLimit"
            :on-exceed="onExceed"
            :on-remove="onRemove"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <!-- 类型 -->
        <el-form-item label="类型" prop="ingredientType" v-if="isAdd">
          <el-radio-group @change="ingredientTypeChange" v-model="ingredientForm.ingredientType">
            <el-radio
              v-for="dict in ingredientTypeList"
              :key="dict.name"
              :label="dict.value"
            >{{ dict.name }}
            </el-radio>
          </el-radio-group>
        </el-form-item>

        <div v-show="ingredientForm.ingredientType == 1">
          <!-- 菜品需要的内容 -->
          <div style="display: flex">
            <el-form-item label="主要食材" prop="mainIngredient">
              <el-select
                v-model="ingredientForm.mainIngredient"
                placeholder="请选择类型"
                clearable>
                <el-option
                  v-for="type in ingredientType.mainIngredientList"
                  :key="type.name"
                  :label="type.name"
                  :value="type.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="份 量(g)">
              <el-input-number
                v-model="ingredientForm.mainIngredientNum"
                :min="50"
                :step="10">
              </el-input-number>
            </el-form-item>
          </div>

          <div style="display: flex">
            <el-form-item label="辅助食材" prop="minorIngredient">
              <el-select
                v-model="ingredientForm.minorIngredient"
                placeholder="请选择类型"
                clearable>
                <el-option
                  v-for="type in ingredientType.minorIngredientList"
                  :key="type.name"
                  :label="type.name"
                  :value="type.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="份 量(g)">
              <el-input-number
                v-model="ingredientForm.minorIngredientNum"
                :min="50"
                :step="10">
              </el-input-number>
            </el-form-item>
          </div>

          <div style="display: flex">
            <el-form-item label="调料" prop="seasoning">
              <el-select
                v-model="ingredientForm.seasoning"
                placeholder="请选择类型"
                clearable>
                <el-option
                  v-for="type in ingredientType.seasoningList"
                  :key="type.name"
                  :label="type.name"
                  :value="type.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="份 量(g)">
              <el-input-number
                v-model="ingredientForm.seasoningNum"
                :min="50"
                :step="10">
              </el-input-number>
            </el-form-item>
          </div>
        </div>

        <el-form-item label="描述" prop="description">
          <el-input
            type="textarea"
            v-model="ingredientForm.description"
            placeholder="请填写描述信息">
          </el-input>
        </el-form-item>

      </el-form>
      <div slot="footer">
        <el-button size="mini" @click="ingredientFormVisible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getDictList} from '@/api/dict'
import {addIngredient, getIngredientList, getTypeList, deleteIngredient, updateIngredient} from '@/api/ingredient'

export default {
  name: "index",
  data() {
    /* 自定义校验 */
    var checkIngredient = (rule, value, callback) => {
      // 选择菜品类型 并且 未选择主要食材、辅助食材、调料时  校验失败
      if (this.ingredientForm.ingredientType == 1 && !value) {
        return callback(new Error('请选择食材'));
      } else {
        callback()
      }
    }

    return {
      uploadHeaders:{
        Authorization: sessionStorage.getItem("token")
      },

      pageNumber: 1,   // 当前页码
      pageSize: 5,     // 每页记录数
      total: 0,        // 总数
      tableData: [],   // 菜品、食材列表数据
      queryForm: {     /* 查询表单参数 */
        name: '',      // 名称
        ingredientType: '' // 类型
      },
      ingredientTypeList: [], // 食材类型  字典项

      ingredientFormVisible: false,
      title: '添加',
      isAdd: true, // 是否是添加操作
      fileList: [],
      fileLimit: 1,
      ingredientForm: {
        name: '',
        url: '',
        mainIngredient: '',
        mainIngredientNum: '',
        minorIngredient: '',
        minorIngredientNum: '',
        seasoning: '',
        seasoningNum: '',
        description: '',
        ingredientType: ''
      },

      ingredientFormRules: {
        name: [{required: true, message: '必填项'}],
        url: [{required: true, message: '必填项'}],

        mainIngredient: [{validator: checkIngredient}],
        minorIngredient: [{validator: checkIngredient}],
        seasoning: [{validator: checkIngredient}],

        description: [{required: true, message: '必填项'}],
        ingredientType: [{required: true, message: '必填项'}]
      },

      ingredientType: {}
    }
  },

  methods: {
    /* 查询食材类型列表 */
    async getIngredientType() {
      const {data} = await getDictList('ingredient')
      this.ingredientTypeList = data
    },

    /* 条件修改了, 点击查询, 重置 */
    search() {
      this.pageNumber = 1
      this.getTableData()
    },

    /* 重置 */
    resetQueryForm() {
      this.$refs.queryForm.resetFields()
      this.search()
    },

    /* 查询菜品或食材列表 */
    async getTableData() {
      try{
        const {data} = await getIngredientList(this.pageNumber, this.pageSize, this.queryForm)
        this.tableData = data.rows
        this.pageNumber = data.pageNumber
        this.pageSize = data.pageSize
        this.total = data.total
      }catch(e) {
        console.log(e)
      }
    },

    /* 获取字典名称 */
    getDictName(dictList, value) {
      return dictList.find(dict => dict.value == value).name
    },

    /* 页码改变时触发 */
    pageNumberChange(pageNumber) {
      this.pageNumber = pageNumber
      this.getTableData()
    },

    /* 每页显示记录数改变时触发 */
    pageSizeChange(pageSize) {
      this.pageSize = pageSize
      this.getTableData()
    },

    /* 点击添加按钮执行 */
    async handleAdd() {
      this.ingredientFormVisible = true

      const { data } = await getTypeList()
      this.ingredientType = data

      this.$nextTick(() => {
        this.title = '添加'
        this.isAdd = true
        this.ingredientForm = {
          name: '',
          url: '',
          mainIngredient: '',
          mainIngredientNum: '',
          minorIngredient: '',
          minorIngredientNum: '',
          seasoning: '',
          seasoningNum: '',
          description: '',
          ingredientType: ''
        }
        this.fileList = []
        this.$refs.ingredientForm.resetFields()
      })
    },

    /* 上传前校验 */
    beforeUpload(file) {
      const isJPG = file.type == 'image/jpeg'
      const isPNG = file.type == 'image/png'
      const isLt2MB = file.size / 1024 / 1024 < 2

      if (!isJPG && !isPNG) {
        this.$message.warning('仅支持jpg和png格式')
        return false
      }

      if (!isLt2MB) {
        this.$message.warning('图片不能大于2MB')
        return false
      }
    },

    /* 图片上传成功之后执行 */
    uploadSuccess(response, file, fileList) {
      this.ingredientForm.url = response.data
      // 重新校验url
      this.$refs.ingredientForm.validateField('url')
    },

    /* 超出上传限制时执行 */
    onExceed(file, fileList) {
      this.$message.warning('仅能上传' + this.fileLimit + '张图片')
    },

    /* 删除图片 */
    onRemove(file, fileList) {
      this.ingredientForm.url = ''
      this.fileList = []
      // 重新校验url
      this.$refs.ingredientForm.validateField('url')
    },

    submit() {
      this.$refs.ingredientForm.validate(async valid => {
        if (valid) {
          if (this.isAdd) {
            if (this.ingredientForm.ingredientType != 1) {
              this.ingredientForm.mainIngredientNum = ''
              this.ingredientForm.minorIngredientNum = ''
              this.ingredientForm.seasoningNum = ''
            }

            const {code, message} = await addIngredient(this.ingredientForm)
            if (code === 200) {
              this.$message.success('添加成功！')
              this.getTableData()
              this.ingredientFormVisible = false
            } else {
              this.$message.warning(message)
            }
          } else { // 修改
            const {code, message} = await updateIngredient(this.ingredientForm.id, this.ingredientForm)
            if (code === 200) {
              this.$message.success('更新成功！')
              this.getTableData()
              this.ingredientFormVisible = false
            } else {
              this.$message.warning(message)
            }
          }
        } else {
          return
        }
      })
    },

    ingredientTypeChange() {
      if (this.ingredientForm.ingredientType != 1) {
        this.ingredientForm.mainIngredient = ''
        this.ingredientForm.minorIngredient = ''
        this.ingredientForm.seasoning = ''
        this.$refs.ingredientForm.clearValidate()
      }
    },

    /* 删除菜品或食材 */
    deleteIngredient(id) {
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const {code, message} = await deleteIngredient(id)
        if (code == 200) {
          this.$message.success('删除成功!')
          setTimeout(() => {
            this.getTableData()
          }, 1000)
        } else {
          this.$message.error(message)
        }
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    },

    async updateIngredient(ingredient) {
      this.isAdd = false

      const {data} = await getTypeList()
      this.ingredientType = data

      this.ingredientForm = {...ingredient}

      this.fileList = [{url: this.base_url + '/ingredient-sys' + ingredient.url}]

      this.ingredientFormVisible = true
    }

  },

  created() {
    this.getIngredientType()
  },

  mounted() {
    this.getTableData()
  }
}
</script>

<style scoped>

</style>