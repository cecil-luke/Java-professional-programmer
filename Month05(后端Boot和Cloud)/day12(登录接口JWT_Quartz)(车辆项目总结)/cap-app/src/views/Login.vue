<template>
  <el-card class="login">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginFormRules"
      label-width="80px"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密 码" prop="password">
        <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>

      <el-row :gutter="10">
        <!-- 验证码输入框 -->
        <el-col :span="12">
          <el-form-item label="验证码" prop="code">
            <el-input
              v-model="loginForm.code"
              placeholder="请输入验证码"
              @keyup.enter.native="login"></el-input>
          </el-form-item>
        </el-col>
        <!-- 验证码图片 -->
        <el-col :span="6">
          <img :src="codeUrl" @click="getCode">
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
        <el-button @click="reset">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import {getCode, login} from '@/api/login'

export default {
  name: "Login",
  data() {
    return {
      codeUrl: '', // 验证码的Base64(img src的值)
      loginForm: {
        username: '',
        password: '',
        uuid: '',
        code: '',
      },
      loginFormRules: {
        username: [{required: true, message: '请输入用户名'}],
        password: [{required: true, message: '请输入密码'}],
        code: [{required: true, message: '请输入验证码'}],
      },
    }
  },
  methods: {
    /* 获取验证码 */
    async getCode() {
      const {data: {data}} = await getCode()
      this.codeUrl = data.code
      this.loginForm.uuid = data.uuid
    },

    /* 登录 */
    login() {
      this.$refs.loginForm.validate(async valid => {
        if (valid) {
          const {data: {code, msg, data}} = await login(this.loginForm)
          if (code === 200) {
            this.$message.success('登录成功')

            setTimeout(() => {
              sessionStorage.setItem('token', data)
              this.$router.push('/')
            }, 1000)
          } else {
            this.$message.warning(msg)
            this.getCode()
          }
        } else {
          return false
        }
      })
    },

    reset() {
      this.$refs.loginForm.resetFields()
      this.getCode()
    }
  },

  mounted() {
    this.getCode()
  }
}
</script>

<style scoped>
.login {
  width: 400px;
  height: 280px;
  position: absolute;
  left: 50%; /* 向右50% */
  top: 50%; /* 向下50% */
  transform: translate(-50%, -50%); /* 向左和向上移动自身的一半 */
}
</style>