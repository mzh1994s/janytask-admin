<style>
    .login_layout {
        width: 100%;
        height: 100%;
    }

    .login_form {
        position: fixed;
        width: 400px;
        height: 300px;
        margin: auto;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }
</style>
<template>
    <div class="login_layout">
        <div class="login_form">
            <Card style="padding: 30px 30px 0 0">
                <Form ref="loginForm" :model="loginInfo" :rules="loginInfoRules" :label-width="80">
                    <FormItem label="用户名" prop="user">
                        <Input type="text" v-model="loginInfo.username"></Input>
                    </FormItem>
                    <FormItem label="密码" prop="password">
                        <Input type="password" v-model="loginInfo.password"></Input>
                    </FormItem>
                    <FormItem label="验证码" prop="verifyCode">
                        <Input type="text" v-model="loginInfo.verifyCode" number></Input>
                        <img :src="verifyCodeUri" alt="验证码"/>
                        <a @click="refreshVerifyCode()">看不清</a>
                    </FormItem>
                    <FormItem>
                        <Button type="primary" @click="handleSubmit()" long>登录</Button>
                    </FormItem>
                </Form>
            </Card>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
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
                this.$axios.post('/auth/login.do', this.loginInfo).then(response => {
                    this.$emit('finished', response.data);
                }).catch(error => {
                    if (error.code === -401) {
                        this.refreshVerifyCode();
                    }
                    this.$Message.error(error.msg);
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