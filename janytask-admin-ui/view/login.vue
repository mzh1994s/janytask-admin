<template>
    <Form ref="loginForm" :model="loginInfo" :rules="ruleInline" inline>
        <FormItem prop="user">
            <Input type="text" v-model="loginInfo.username" placeholder="用户名">
            <Icon type="ios-person-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem prop="password">
            <Input type="password" v-model="loginInfo.password" placeholder="密码">
            <Icon type="ios-lock-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem>
            <Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
        </FormItem>
    </Form>
</template>
<script>
    export default {
        data(){
            return {
                loginInfo: {
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            handleSubmit(){
                console.log(this.loginInfo);
            }
        }
    }
</script>