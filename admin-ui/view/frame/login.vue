<style>
    .login_layout {
        width: 100%;
        height: 100%;
    }

    .login_form {
        width: 400px;
        height: 400px;
    }
</style>
<template>
    <div class="login_layout">
        <el-card v-loading="isSubmitting" element-loading-text="登录中..." class="message-card login_form">
            <div class="message-wrap">
                <el-form ref="loginForm" :model="loginInfo" :rules="loginInfoRules" label-width="70px">
                    <el-form-item label="用户名" prop="user">
                        <el-input type="text" v-model="loginInfo.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="loginInfo.password"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" prop="verifyCode">
                        <el-input type="text" v-model="loginInfo.verifyCode" number></el-input>
                        <img :src="verifyCodeUri" alt="验证码"/>
                        <a @click="refreshVerifyCode()" style="cursor: pointer;">看不清</a>
                    </el-form-item>
                </el-form>
                <el-button type="primary" @click="handleSubmit()" style="width:80%;">登录</el-button>
            </div>
        </el-card>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                isSubmitting: false,
                verifyCodeCnt: 0,
                loginInfo: {
                    username: 'admin',
                    password: 'mzh176506',
                    verifyCode: ''
                },
                loginInfoRules: {
                    username: {required: true, message: '请输入用户名'},
                    password: {required: true, message: '请输入密码'},
                    verifyCode: [{required: true, message: '请输入验证码'}]
                }
            }
        },
        methods: {
            refreshVerifyCode() {
                this.verifyCodeCnt++;
            },
            handleSubmit() {
                this.isSubmitting = true;
                this.$axios.post('/auth/login.do', this.loginInfo).then(response => {
                    this.$emit('finished', response.data);
                }).catch(error => {
                    if (error.code === -403) {
                        this.refreshVerifyCode();
                    }
                    this.$Message.error(error.msg);
                }).finally(() => {
                    this.isSubmitting = false;
                })
            }
        },
        computed: {
            verifyCodeUri() {
                return "verify-code/generate.do?key=login&t=" + this.verifyCodeCnt
            }
        }
    }
</script>