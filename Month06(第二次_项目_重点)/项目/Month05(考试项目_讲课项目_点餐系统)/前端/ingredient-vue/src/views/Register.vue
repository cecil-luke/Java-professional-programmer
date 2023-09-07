<template>
    <el-card class="register-card">
        <div>注册</div>
        <br>
        <el-form
            ref="registerForm"
            :model="registerForm"
            :rules="registerFormRules"
            label-width="80px">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model="registerForm.age" placeholder="请输入年龄"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="register">注 册</el-button>
                <el-button type="info" @click="resetRegisterForm">取 消</el-button>
                <el-button type="info" @click="registerTo">登录</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
    import { register } from "@/api/user"
    export default {
        data() {
            return {
                registerForm:{
                    username:'',
                    email:'',
                    age:null
                },

                registerFormRules: {
                    username: [ {required: true, message: '请输入用户名'} ],
                    email: [ {required: true, message: '请输入邮箱'} ],
                    age: [ {required: true, message: '请输入年龄'} ],
                }

            }
        },
        methods: {

            register() {
                this.$refs.registerForm.validate(async v => {
                        if (v) {
                        const {code, message, data} = await register(this.registerForm)
                        if (code === 200) {
                            this.$message.success('注册成功！')
                            
                            this.$router.push('/login')
                        } else {
                            this.$message.error(message)
                        }
                        } else {
                            return
                        }
                    })
            },

            resetRegisterForm() {
                this.$refs.registerForm.resetFields()
            },

            registerTo() {
                this.$router.push({
                    path:'/login',
                })
            },
        },
        moundted() {

        }
    }
</script>

<style scoped>
    .register-card {
        width: 420px;
        height: 280px;
        position: absolute;
        left: 50%;
        top: 50%;
        /* 向自已内部移动一半 */
        transform: translate(-50%, -50%);
    }
</style>