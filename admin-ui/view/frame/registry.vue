<style>
    .login_layout {
        width: 100%;
        height: 100%;
    }

    .login_form {
        position: fixed;
        width: 400px;
        height: 430px;
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
            <Card style="width: 400px;">
                <Alert type="warning">此账号为您的管理员账号，请牢记！</Alert>
                <Spin size="large" fix v-if="isSubmitting"></Spin>
                <Form ref="registryForm" :model="user" :rules="userValidation" :label-width="80" style="margin: 20px 10px 0 0;">
                    <FormItem label="电子邮箱" prop="email">
                        <Input type="text" v-model="user.email"></Input>
                    </FormItem>
                    <FormItem label="用户名" prop="username">
                        <Input type="text" v-model="user.username"></Input>
                    </FormItem>
                    <FormItem label="昵称" prop="name">
                        <Input type="text" v-model="user.name"></Input>
                    </FormItem>
                    <FormItem label="密码" prop="password">
                        <Input type="password" v-model="user.password"></Input>
                    </FormItem>
                    <FormItem label="确认密码" prop="passwordCheck">
                        <Input type="password" v-model="user.passwordCheck"></Input>
                    </FormItem>
                    <FormItem>
                        <Button type="primary" @click="handleSubmit()" long>确定</Button>
                    </FormItem>
                </Form>
            </Card>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            let passwordCheckValidator = (rule, value, callback) => {
                if (value !== this.user.password) {
                    return callback(new Error());
                } else {
                    return callback();
                }
            };

            return {
                isSubmitting: false,
                user: {
                    email: '',
                    username: 'admin',
                    name: '',
                    password: '',
                    passwordCheck: ''
                },
                userValidation: {
                    email: [{required: true, message: '请输入邮箱'}, {type: 'email', message: '请输入正确的邮箱地址！'}],
                    username: {required: true, message: '请输入用户名'},
                    name: [],
                    password: {required: true, message: '请输入密码'},
                    passwordCheck: [
                        {required: true, message: '请输入确认密码'},
                        {validator: passwordCheckValidator, message: '确认密码与密码不一致！'}
                    ]
                }
            }
        },
        methods: {
            handleSubmit() {
                this.$refs['registryForm'].validate(valid => {
                    if (!valid) {
                        this.isSubmitting = true;
                        $http.post2('center/awaitInit.json', this.user).then(response => {
                            this.status = 2;
                        }).catch(error => {
                            this.$Message.info({
                                content: error.msg,
                                duration: 10,
                                closable: true
                            });
                        }).finally(() => {
                            this.isSubmitting = false;
                        });
                    }
                });
            }
        }
    }
</script>