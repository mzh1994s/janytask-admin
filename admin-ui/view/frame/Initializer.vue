<style>
    .initialize_layout {
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

    .base-card{
        width: 400px;
        min-height: 200px;
        text-align: center;
    }

    .demo-spin-icon-load {
        animation: ani-demo-spin 1s linear infinite;
    }
    .info-container{
        text-align: center; padding: 20px;
    }
</style>
<template>
    <div class="initialize_layout">
        <div v-if="step==1" class="login_form">
            <Card class="base-card">
                <Alert type="warning">此账号为您的管理员账号，请牢记！</Alert>
                <Form ref="adminForm" :model="prepareData.admin" :rules="userValidation" :label-width="80"
                      style="margin: 20px 10px 0 0;">
                    <FormItem label="电子邮箱" prop="email">
                        <Input type="text" v-model="prepareData.admin.email"></Input>
                    </FormItem>
                    <FormItem label="用户名" prop="username">
                        <Input type="text" v-model="prepareData.admin.username"></Input>
                    </FormItem>
                    <FormItem label="昵称" prop="name">
                        <Input type="text" v-model="prepareData.admin.name"></Input>
                    </FormItem>
                    <FormItem label="密码" prop="password">
                        <Input type="password" v-model="prepareData.admin.password"></Input>
                    </FormItem>
                    <FormItem label="确认密码" prop="passwordCheck">
                        <Input type="password" v-model="prepareData.admin.passwordCheck"></Input>
                    </FormItem>
                </Form>
                <Button type="primary" @click="handleStep1()">下一步</Button>
            </Card>
        </div>
        <div v-if="step==2" class="login_form">
            <Card class="base-card">
                <Spin fix v-if="isSubmitting">
                    <Icon type="ios-loading" size=30 class="demo-spin-icon-load"></Icon>
                    <div style="margin-top: 20px;">初始化进行中...</div>
                </Spin>
                <div v-else-if="error!=null" class="info-container">
                    <Icon type="ios-close-circle" size="40" color="#ff0000"/>
                    <p style="margin-top: 20px;">错误：{{error}}</p>
                    <Button type="primary" @click="handleInitialize()" style="margin-top: 20px;">重&nbsp;&nbsp;&nbsp;&nbsp;试</Button>
                    <Button @click="handleToStep(-1)" style="margin-top: 20px;">上一步</Button>
                </div>
                <div v-else class="info-container">
                    <Icon type="md-checkmark-circle" size="40" color="#ff0000"/>
                    <p style="margin-top: 20px;">恭喜，所有初始化项均已初始化完成，请开始您的任务监控之旅！</p>
                    <Button type="primary" @click="handleFinish()" style="margin-top: 20px;">前往登录</Button>
                </div>
            </Card>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            let passwordCheckValidator = (rule, value, callback) => {
                if (value !== this.prepareData.admin.password) {
                    return callback(new Error());
                } else {
                    return callback();
                }
            };

            return {
                step: 1,
                isSubmitting: false,
                error: null,
                prepareData: {
                    admin: {
                        email: 'email@mzhong.cn',
                        username: 'admin',
                        name: '老M',
                        password: 'mzh176506',
                        passwordCheck: 'mzh176506'
                    }
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
            handleStep1() {
                this.$refs['adminForm'].validate(valid => {
                    if (valid) {
                        this.handleInitialize();
                    }
                });
            },
            handleToStep(rel = 1){
                this.step = this.step + rel;
            },
            handleInitialize() {
                this.step = 2
                this.isSubmitting = true;
                this.$axios.post2('center/awaitInitialize.json', this.prepareData).then(response => {
                    // do nothing
                }).catch(error => {
                    this.error = error.msg;
                }).finally(() => {
                    this.isSubmitting = false;
                });
            },
            handleFinish() {
                // 触发finish事件，将数据提交给frame
                this.$emit('finished', this.prepareData);
            }
        }
    }
</script>