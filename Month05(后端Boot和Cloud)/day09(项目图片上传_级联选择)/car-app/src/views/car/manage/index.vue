<template>
  <div>
    <!-- 查询表单 -->

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
        >修改
        </el-button>
      </el-col>
      <el-col :span="2">
        <el-button
          type="danger"
          size="mini"
          icon="el-icon-delete"
          plain
        >删除
        </el-button>
      </el-col>
    </el-row>

    <!-- 添加、修改对话框 -->

    <el-dialog :title="title" :visible.sync="carFormVisible">
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
            action="http://localhost:8000/upload/image"
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
    <!-- 分页条-->
  </div>
</template>

<script>
import {getDictList} from "@/api/dict";
import {getAreaList} from "@/api/area";

export default {
  name: "index",
  data() {
    return {
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
        price: [{required: true, message: '必填项'}],
        licenseDate: [{required: true, message: '必填项'}],
        level: [{required: true, message: '必填项'}],
        gearbox: [{required: true, message: '必填项'}],
        disp: [{required: true, message: '必填项'}],
        mileage: [{required: true, message: '必填项'}],
        province: [{required: true, message: '必填项'}],
        savePath: [{required: true, message: '必填项'}],
        summary: [{required: true, message: '必填项'}],
      }
    }
  },

  methods: {
    handleAdd() {
      this.carFormVisible = true
    },

    async getDictList() {
      // https://es6.ruanyifeng.com/#docs/destructuring  2.对象的解构赋值
      // const levelList = resp.data.data
      // 第一个data是resp.data;  第二个data是resp.data.data
      const {data: { data: levelList } } = await getDictList('level')
      this.dict.levelList = levelList

      const {data: { data: gearboxList } } = await getDictList('gearbox')
      this.dict.gearboxList = gearboxList

      const {data: { data: dispList } } = await getDictList('disp')
      this.dict.dispList = dispList
    },

    async getAreaList() {
      // const data = resp.data.data
      const {data} = await getAreaList()
      this.areaOptions = data
    },

    // area参数是数组
    areaOptionsChange(area) {
      
    },

    beforeUpload(file) {

    },

    onSuccess(response, file, fileList) {

    },

    onExceed(file, fileList) {

    },

    onRemove(file, fileList) {

    },

    submit() {
      this.$refs.carForm.validate(async valid => {
        if (valid) {
          // const {data: {code, msg } } = await addCar()

        } else {
          return false
        }
      })
    }


  },

  mounted() {
    this.getDictList()
    this.getAreaList()
  }
}
</script>

<style scoped>

</style>