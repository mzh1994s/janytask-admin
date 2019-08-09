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
                <Form ref="loginForm" :model="loginInfo" :label-width="80">
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
                    username: '',
                    password: '',
                    verifyCode: ''
                }
            }
        },
        methods: {
            refreshVerifyCode(){
                this.verifyCodeCnt++;
            },
            handleSubmit() {
                alert(this.loginInfo.username);
                this.$axios.post2('/', loginInfo).then(response => {
                    console.log(response.data);
                })
            }
        },
        computed: {
            verifyCodeUri(){
                return "verify_code/generate.do?key=login&t=" + this.verifyCodeCnt
            }
        }
    }
</script>