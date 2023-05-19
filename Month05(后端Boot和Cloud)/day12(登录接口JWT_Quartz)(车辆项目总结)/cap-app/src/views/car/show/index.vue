<template>
  <div>
    <el-form
      ref="queryForm"
      :model="queryForm"
      size="mini"
      label-width="80px">
      <!-- 品牌 单选 -->
      <el-form-item label="品牌">
        <el-radio-group v-model="queryForm.brand">
          <el-radio
            v-for="brand in brandList"
            :key="brand"
            :label="brand">
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 车系 单选 -->
      <el-form-item label="车系">
        <el-radio-group v-model="queryForm.series">
          <el-radio
            v-for="series in seriesList"
            :key="series"
            :label="series">
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 价格 多选 -->
      <el-form-item label="价格">
        <el-checkbox-group v-model="queryForm.priceList">
          <el-checkbox
            v-for="dict in dict.priceList"
            :key="dict.name"
            :label="dict.value"
          >{{ dict.name }}
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <!-- 车龄 单选 -->
      <el-form-item label="车龄">
        <el-radio-group v-model="queryForm.carAge">
          <el-radio
            v-for="dict in dict.carAgeList"
            :key="dict.name"
            :label="dict.value"
          >{{ dict.name }}
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <el-row :gutter="10">
        <!-- 级别 -->
        <el-col :span="6">
          <el-form-item label="级别">
            <el-select v-model="queryForm.level" placeholder="请选择级别" clearable>
              <el-option
                v-for="dict in dict.levelList"
                :key="dict.name"
                :label="dict.name"
                :value="dict.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <!-- 变速箱 -->
        <el-col :span="6">
          <el-form-item label="变速箱">
            <el-select v-model="queryForm.gearbox" placeholder="请选择变速箱" clearable>
              <el-option
                v-for="dict in dict.gearboxList"
                :key="dict.name"
                :label="dict.name"
                :value="dict.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <!-- 排量 -->
        <el-col :span="6">
          <el-form-item label="排量">
            <el-select v-model="queryForm.disp" placeholder="请选择排量" clearable>
              <el-option
                v-for="dict in dict.dispList"
                :key="dict.name"
                :label="dict.name"
                :value="dict.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="归属地">
            <el-cascader
              :options="areaOptions"
              v-model="areaOptionsValue"
              clearable
              :props="{ checkStrictly: true, value: 'id', label: 'name' }"
              @change="areaOptionsChange"
            ></el-cascader>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row>
      <el-col :span="6" v-for="car in carList" :key="car.id">
        <el-card :body-style="{ padding: '0px' }" style="margin: 15px 5px" shadow="hover">
          <img :src="base_url + car.savePath" style="display: block; width: 100%">
          <div style="padding: 14px;">
            <div class="card-name">
              <span>{{ car.brand }}  {{ car.series }} {{ car.gearboxName }} {{ car.levelName }} {{
                  car.dispName
                }}</span>
            </div>

            <div class="card-unit">
              <span>{{ car.mileage }}万公里   {{ car.licenseDate }}   {{ car.location }}</span>
            </div>
            <div class="price-card">
              <span class="price">
                <em>{{ car.price }}</em> 万
              </span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[8, 12, 16, 20, 40, 100]"
      :page-size="pageSize"
      :current-page="pageNumber"
      :total="total"
      @current-change="pageNumberChange"
      @size-change="pageSizeChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import {getDictList} from '@/api/dict'
import {getAreaList} from '@/api/area'
import {getBrand, getSeries, getCarList} from '@/api/car'

export default {
  name: "index",
  data() {
    return {
      brandList: [], // 品牌列表
      seriesList: [], // 车系列表
      dict: { /* 字典数据 */
        levelList: [],
        gearboxList: [],
        dispList: [],
        priceList: [], // 价格列表
        carAgeList: [] // 车龄列表
      },
      areaOptions: [], // 省市区列表数据
      areaOptionsValue: [], // 选中的省市区列表

      pageNumber: 1, // 页码
      pageSize: 8, // 每页显示记录数
      total: 0, // 总记录数
      carList: [], // 车辆列表

      queryForm: { /* 查询表单选中的数据 */
        brand: '',
        series: '',
        priceList: [], // 多选框
        carAge: '',
        level: '',
        gearbox: '',
        disp: '',
        province: '',
        city: '',
        area: ''
      }
    }
  },

  watch: {
    queryForm: {
      deep: true,
      handler: function(newParam, oldParam) {
        this.pageNumber = 1
        this.getCarList()
      }
    },

    'queryForm.brand': function() {
      this.queryForm.series = ''
      this.getSeries()
    }
  },

  methods: {
    /* 获取字典数据 */
    async getDict() {
      const {data: {data: levelList}} = await getDictList('level')
      this.dict.levelList = levelList

      const {data: {data: gearboxList}} = await getDictList('gearbox')
      this.dict.gearboxList = gearboxList

      const {data: {data: dispList}} = await getDictList('disp')
      this.dict.dispList = dispList

      const {data: {data: priceList}} = await getDictList('price')
      this.dict.priceList = priceList

      const {data: {data: carAgeList}} = await getDictList('car_age')
      this.dict.carAgeList = carAgeList
    },

    /* 省市区列表数据 */
    async getAreaList() {
      const {data: {data}} = await getAreaList()
      this.areaOptions = data
    },

    /* 品牌列表 */
    async getBrand() {
      const {data: {data}} = await getBrand()
      this.brandList = data
    },

    /* 车系列表 */
    async getSeries() {
      const {data: {data}} = await getSeries(this.queryForm.brand)
      this.seriesList = data
    },

    /* 车辆列表 */
    async getCarList() {
      const {data: {data}} = await getCarList(this.pageNumber, this.pageSize, this.queryForm)

      this.pageNumber = data.pageNumber
      this.pageSize = data.pageSize
      this.total = data.total
      this.carList = data.rows
    },

    pageNumberChange(pageNumber) {
      this.pageNumber = pageNumber
      this.getCarList()
    },

    pageSizeChange(pageSize) {
      this.pageSize = pageSize
      this.getCarList()
    },

    areaOptionsChange(selection) {
      this.queryForm.province = selection[0] || ''
      this.queryForm.city = selection[1] || ''
      this.queryForm.area = selection[2] || ''
    }

  },

  mounted() {
    this.getDict()
    this.getAreaList()
    this.getBrand()
    this.getSeries()
    this.getCarList()
  }
}
</script>

<style scoped>
.card-name {
  color: #1B1B1B;
  line-height: 1.4;
  height: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 14px;
  white-space: nowrap;
}

.card-unit {
  margin-top: 8px;
  font-size: 12px;
  color: #999;
}

.price-card {
  height: 20px;
  overflow: hidden;
  margin-top: 8px;
}

.price {
  font-size: 14px;
  color: #F9712C;
  float: left;
}

.price em {
  font-size: 20px;
  line-height: 1;
}

em {
  font-style: normal;
  font-weight: 500;
}
</style>