<template>
  <el-card class="login-card">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginFormRules"
      label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>

      <el-row :gutter="15">
        <el-col :span="16">
          <el-form-item label="验证码" prop="code" >
            <el-input v-model="loginForm.code" placeholder="请输入验证码"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <img :src="codeUrl" style="height: 40px" @click="getCode">
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" @click="login">登 录</el-button>
        <el-button type="info" @click="resetLoginForm">取 消</el-button>
        <el-button type="info" @click="registerTo">注册</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import {getCode} from '@/api/code'
import {login} from '@/api/user'

export default {
  name: "Login",
  data() {
    return {
      codeUrl: '', // 显示验证码

      loginForm: {
        username: '',
        password: '',
        uuid: '',
        code: ''
      },

      loginFormRules: {
        username: [ {required: true, message: '请输入用户名'} ],
        password: [ {required: true, message: '请输入密码'} ],
        code: [ {required: true, message: '请输入验证码'} ],
      }
    }
  },

  methods: {
    async getCode() {
      const {data: { uuid, codeUrl } } = await getCode()
      this.loginForm.uuid = uuid
      this.codeUrl = codeUrl
      
    },

    login() {
      this.$refs.loginForm.validate(async v => {
        if (v) {
          const {code, message, data} = await login(this.loginForm)
          if (code === 200) {
            this.$message.success('登录成功！')
            sessionStorage.setItem('token', data)
            this.$router.push('/')
          } else {
            this.$message.error(message)
            this.getCode()
          }
        } else {
          return
        }
      })
    },

    registerTo() {
      this.$router.push({
          path:'/register',
      })
    },


    resetLoginForm() {
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
.login-card {
  width: 420px;
  height: 280px;
  position: absolute;
  left: 50%;
  top: 50%;
  /* 向自已内部移动一半 */
  transform: translate(-50%, -50%);
}
</style>