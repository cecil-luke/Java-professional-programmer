<template>
  <div>
    <!-- 查询表单 -->
    <el-form
      ref="queryForm"
      :model="queryForm"
      label-width="50px"
      size="mini"
      inline
    >
      <el-form-item label="品牌" prop="brand">
        <el-input
          v-model="queryForm.brand"
          placeholder="请输入品牌"
          @keyup.enter.native="search"
        ></el-input>
      </el-form-item>
      <el-form-item label="车系" prop="series">
        <el-input
          v-model="queryForm.series"
          placeholder="请输入车系"
          @keyup.enter.native="search"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          plain
          icon="el-icon-search"
          @click="search">查 询</el-button>
        <el-button
          type="info"
          plain
          icon="el-icon-refresh"
          @click="resetQueryForm">重 置</el-button>
      </el-form-item>
    </el-form>

    <!-- 添加、修改、删除按钮 -->
    <el-row :gutter="10">
      <el-col :span="2">
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-plus"
          plain
          @click="handleAdd"
        >添加
        </el-button>
      </el-col>

      <el-col :span="2">
        <el-button
          type="success"
          size="mini"
          icon="el-icon-edit"
          plain
          :disabled="disableEdit"
          @click="showEditDialog"
        >修改
        </el-button>
      </el-col>
      <el-col :span="2">
        <el-button
          type="danger"
          size="mini"
          icon="el-icon-delete"
          plain
          :disabled="disableDelete"
          @click="deleteCar"
        >删除
        </el-button>
      </el-col>
    </el-row>

    <!-- 添加、修改对话框 -->
    <el-dialog :title="title" :visible.sync="carFormVisible" :close-on-click-modal="false">
      <el-form
        ref="carForm"
        :model="carForm"
        :rules="carFormRules"
        size="mini"
        label-width="80px">
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="carForm.brand" placeholder="请输入品牌"></el-input>
        </el-form-item>

        <el-form-item label="车系" prop="series">
          <el-input v-model="carForm.series" placeholder="请输入车系"></el-input>
        </el-form-item>

        <el-form-item label="价格" prop="price">
          <el-input v-model="carForm.price" placeholder="请输入价格">
            <template slot="append">万元</template>
          </el-input>
        </el-form-item>

        <el-form-item label="上牌时间" prop="licenseDate">
          <el-date-picker
            v-model="carForm.licenseDate"
            type="date"
            :picker-options="pickerOptions"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="级别" prop="level">
          <el-select v-model="carForm.level" placeholder="请选择级别" clearable>
            <el-option
              v-for="dict in dict.levelList"
              :key="dict.name"
              :label="dict.name"
              :value="dict.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="变速箱" prop="gearbox">
          <el-radio-group v-model="carForm.gearbox">
            <el-radio
              v-for="dict in dict.gearboxList"
              :key="dict.name"
              :label="dict.value"> {{ dict.name }}
            </el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="变速箱" prop="disp">
          <el-select v-model="carForm.disp" placeholder="请选择变速箱" clearable>
            <el-option
              v-for="dict in dict.dispList"
              :key="dict.name"
              :label="dict.name"
              :value="dict.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="里程" prop="mileage">
          <el-input v-model="carForm.mileage" placeholder="请输入里程">
            <template slot="append">万公里</template>
          </el-input>
        </el-form-item>

        <el-form-item label="归属地" prop="province">
          <el-cascader
            v-model="areaOptionsValue"
            :options="areaOptions"
            :props="{ value: 'id', label: 'name' }"
            @change="areaOptionsChange"
            clearable
          >
          </el-cascader>
        </el-form-item>

        <el-form-item label="图片" prop="savePath">
          <el-upload
            :headers="uploadHeader"
            :action="base_url + '/upload/image'"
            :limit="1"
            list-type="picture"
            :file-list="fileList"
            :on-success="onSuccess"
            :before-upload="beforeUpload"
            :on-exceed="onExceed"
            :on-remove="onRemove"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>

        <el-form-item label="概述" prop="summary">
          <el-input type="textarea" v-model="carForm.summary" placeholder="请输入概述"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="carFormVisible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 表格 -->
    <el-table
      :data="carList"
      border
      stripe
      style="width: 100%; margin: 15px 0px"
      @selection-change="selectionChange">
      <el-table-column type="selection" align="center"></el-table-column>
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="brand" label="品牌"></el-table-column>
      <el-table-column prop="series" label="车系"></el-table-column>
      <el-table-column label="价格">
        <template slot-scope="{ row }">
          <el-tag>{{ row.price }}万</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="licenseDate" label="上牌时间"></el-table-column>
      <el-table-column prop="levelName" label="级别"></el-table-column>
      <el-table-column prop="gearboxName" label="变速箱"></el-table-column>
      <el-table-column prop="dispName" label="排量"></el-table-column>
      <el-table-column label="里程">
        <template slot-scope="{ row }">
          <el-tag>{{ row.mileage }}万公里</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="location" label="归属地"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="160"></el-table-column>
      <el-table-column label="图片" align="center">
        <template slot-scope="{ row }">
          <el-image
            :src="base_url + row.savePath"
            style="width: 30px; height: 30px"
            :preview-src-list="(base_url + row.savePath).split()"></el-image>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页条-->
    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      :page-size="pageSize"
      :page-sizes="[2, 10, 20, 30, 40, 50, 100]"
      :current-page="pageNumber"
      @current-change="pageNumberChange"
      @size-change="pageSizeChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import {getDictList} from '@/api/dict'
import {getAreaList} from '@/api/area'
import { addCar, getCarList, deleteCar, updateCar } from '@/api/car'

export default {
  name: "index",
  data() {
    return {
      uploadHeader: { // 文件上传的请求头
        Authorization: sessionStorage.getItem('token')
      },
      idList: [], // 要删除的id
      disableEdit: true, // 不允许点击修改
      disableDelete: true, // 不允许点击删除
      queryForm: {/* 查询表单 */
        brand: '',
        series: '',
      },

      pageNumber: 1,
      pageSize: 2,
      total: 0,

      carList: [], // 表格数据

      title: '添 加', // 添加、修改模态框的title
      carFormVisible: false, // 是否展示对话框
      dict: { // 字典数据
        levelList: [],
        gearboxList: [],
        dispList: []
      },
      areaOptions: [], // 省市区数据
      areaOptionsValue: [], // 保存选择的省市区id列表
      fileList: [],
      carForm: {
        brand: '',
        series: '',
        price: '',
        licenseDate: '',
        level: '',
        gearbox: '',
        disp: '',
        mileage: '',
        province: '',
        city: '',
        area: '',
        savePath: '',
        summary: '',
      },
      carFormRules: { /* 校验规则 */
        brand: [{required: true, message: '必填项'}],
        series: [{required: true, message: '必填项'}],
        price: [
          {required: true, message: '必填项'},
          {pattern: /^(\d{1,3})(\.{0}|\.\d{1,2})$/, message: '支持3位整数, 两位小数位'}
        ],
        licenseDate: [{required: true, message: '必填项'}],
        level: [{required: true, message: '必填项'}],
        gearbox: [{required: true, message: '必填项'}],
        disp: [{required: true, message: '必填项'}],
        mileage: [
          {required: true, message: '必填项'},
          {pattern: /^(\d{1,3})(\.{0}|\.\d{1,2})$/, message: '支持3位整数, 两位小数位'}
        ],
        province: [{required: true, message: '必填项'}],
        savePath: [{required: true, message: '必填项'}],
        summary: [{required: true, message: '必填项'}],
      },

      pickerOptions: {
        // 禁用日期: 禁用比今天之后的日期; 返回true表示禁用
        disabledDate(date) {
          return date > new Date()
        }
      }
    }
  },

  methods: {
    handleAdd() {
      this.carFormVisible = true

      this.$nextTick(() => {
        this.title = '添加'
        this.carForm.id = undefined
        // this.carForm = {
        //   brand: '',
        //   series: '',
        //   price: '',
        //   licenseDate: '',
        //   level: '',
        //   gearbox: '',
        //   disp: '',
        //   mileage: '',
        //   province: '',
        //   city: '',
        //   area: '',
        //   savePath: '',
        //   summary: '',
        // }

        this.$refs.carForm.resetFields()
        this.areaOptionsValue = []
        this.fileList = []
      })
    },

    async getDictList() {
      // https://es6.ruanyifeng.com/#docs/destructuring  2.对象的解构赋值
      // const levelList = resp.data.data
      // 第一个data是resp.data;  第二个data是resp.data.data
      try {
        const {data: {data: levelList}} = await getDictList('level')
        this.dict.levelList = levelList

        const {data: {data: gearboxList}} = await getDictList('gearbox')
        this.dict.gearboxList = gearboxList

        const {data: {data: dispList}} = await getDictList('disp')
        this.dict.dispList = dispList
      } catch (e) {
        console.log(e)
      }
    },

    async getAreaList() {
      // const data = resp.data.data
      try {
        const { data: {data} } = await getAreaList()
        this.areaOptions = data
      } catch (e) {
        console.log(e)
      }
    },

    // area参数是数组
    areaOptionsChange(area) {
      this.carForm.province = area[0] || ''
      this.carForm.city = area[1] || ''
      this.carForm.area = area[2] || ''
    },

    /* 图片上传前执行 */
    beforeUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isPNG = file.type === 'image/png'
      const isLt1MB = file.size / 1024 / 1024 < 1
      if (!isJPG && !isPNG) {
        this.$message.warning('仅支持jpg和png格式');
        return false
      }

      if (!isLt1MB) {
        this.$message.warning('图片不能大于1MB');
        return false
      }
    },

    /* 图片上传成功后执行的方法 */
    onSuccess(response, file, fileList) {
      this.carForm.savePath = response.data
      // 重新校验savePath
      this.$refs.carForm.validateField('savePath')
    },

    /* 图片上传数量超出限制时执行 */
    onExceed(file, fileList) {
      this.$message.warning('图片超出上传限制')
    },

    /* 删除图片时执行 */
    onRemove(file, fileList) {
      this.carForm.savePath = ''
      this.fileList = []
      // 重新校验savePath
      this.$refs.carForm.validateField('savePath')
    },

    submit() {
      this.$refs.carForm.validate(async valid => {
        if (valid) {
          if (!this.carForm.id) {/* 添加 */
            const resp = await addCar(this.carForm)
            const {code, msg: message} = resp.data
            if (code === 200) {
              this.$message.success('添加成功')
              this.carFormVisible = false
            } else {
              this.$message.error(message)
            }
          } else { /* 更新 */
            updateCar(this.carForm.id, this.carForm).then(resp => {
              const {data: { code, msg }} = resp
              if (code === 200) {
                this.$message.success('更新成功')
                this.carFormVisible = false
                setTimeout(() => {
                  this.getCarList()
                }, 1000)
              } else {
                this.$message.error(msg)
              }
            }).catch(e => {
              console.log(e)
            })
          }
        } else {
          return false
        }
      })
    },

    async getCarList() {
      /*
       this.$ajax.get(`/car?pageNumber=${pageNumber}&pageSize=${pageSize}`, {
         params: this.queryForm
       }).then(resp => {

       }).catch(e => {

       })
      */

      // getCarList(this.pageNumber, this.pageSize, this.queryForm).then(resp => {
      //   this.carList = resp.data.data.rows
      // }).catch(e => { })

      try {
        const {data: result} = await getCarList(this.pageNumber, this.pageSize, this.queryForm)
        this.pageNumber = result.data.pageNumber
        this.pageSize = result.data.pageSize
        this.total = result.data.total
        this.carList = result.data.rows
      } catch (e) {
        console.log(e)
      }
    },

    /* 点击查询按钮, 从第一页查询 */
    search() {
      this.pageNumber = 1
      this.getCarList()
    },

    resetQueryForm() {
      this.$refs.queryForm.resetFields()
      this.search()
    },

    pageNumberChange(pageNumber) {
      this.pageNumber = pageNumber
      this.getCarList()
    },

    pageSizeChange(pageSize) {
      this.pageNumber = 1
      this.pageSize = pageSize
      this.getCarList()
    },

    // 选中的carList中的数据
    selectionChange(selection) {
      // false的时候能点击
      this.disableEdit = selection.length != 1
      this.disableDelete = !selection.length

      this.idList = selection.map(car => car.id)
    },

    deleteCar() {
      this.$confirm('是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const { data: { code, msg } } = await deleteCar(this.idList)
        if (code === 200) {
          this.$message.success('删除成功！')
          this.getCarList()
        } else {
          this.$message.error(msg)
        }
      }).catch(() => {
        this.$message.info('已取消删除')
      });
    },

    showEditDialog() {
      this.carFormVisible = true
      this.$nextTick(() => {
        this.title = '更 新'

        this.$refs.carForm.resetFields()

        this.carForm = {... this.carList.find(car => car.id === this.idList[0])}
        this.areaOptionsValue = [this.carForm.province, this.carForm.city, this.carForm.area]
        this.fileList = [{ url: this.base_url + this.carForm.savePath }]
      })
    }
  },

  mounted() {
    this.getDictList()
    this.getAreaList()
    this.getCarList()
  }
}
</script>

<style scoped>

</style>