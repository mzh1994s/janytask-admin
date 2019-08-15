<style>
    .initialize_layout {
        width: 100%;
        height: 100%;
    }

    .step_1 {
        width: 400px;
        height: 450px;
    }

    .step_2 {
        width: 600px;
        height: 300px;
    }
</style>
<template>
    <div class="initialize_layout">
        <el-card v-if="step===1" class="step_1 message-card">
            <div class="message-wrap">
                <el-alert type="warning" :closable="false">此账号为您的管理员账号，请牢记！</el-alert><br>
                <el-form ref="adminForm" :model="prepareData.admin" :rules="userValidation" label-width="80px">
                    <el-form-item label="电子邮箱" prop="email">
                        <el-input type="text" v-model="prepareData.admin.email"></el-input>
                    </el-form-item>
                    <el-form-item label="用户名" prop="username">
                        <el-input type="text" v-model="prepareData.admin.username"></el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="name">
                        <el-input type="text" v-model="prepareData.admin.name"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="prepareData.admin.password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="passwordCheck">
                        <el-input type="password" v-model="prepareData.admin.passwordCheck"></el-input>
                    </el-form-item>
                </el-form>
                <el-button type="primary" @click="handleStep1()">下一步</el-button>
            </div>
        </el-card>
        <el-card v-else-if="step===2" class="step_2 message-card" v-loading="isSubmitting"
                 element-loading-text="初始化进行中...">
            <div v-if="!isSubmitting" class="message-wrap">
                <template v-if="isError">
                    <i class="el-icon-circle-close message-error-icon"/>
                    <p class="message-text">错误：{{message}}</p>
                    <el-button type="primary" @click="handleInitialize()" style="margin-top: 20px;">重&nbsp;&nbsp;&nbsp;&nbsp;试</el-button>
                    <el-button @click="handleToStep(-1)" style="margin-top: 20px;">上一步</el-button>
                </template>
                <template v-else>
                    <i class="el-icon-circle-check message-success-icon"/>
                    <p class="message-text">恭喜，所有初始化项均已初始化完成，请开始您的任务监控之旅！</p>
                    <el-button type="primary" @click="handleFinish()" style="margin-top: 20px;">前往登录</el-button>
                </template>
            </div>
        </el-card>
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
            handleToStep(rel = 1) {
                this.step = this.step + rel;
            },
            handleInitialize() {
                this.step = 2
                this.isSubmitting = true;
                this.$axios.post2('initialize/awaitInitialize.json', this.prepareData).then(response => {
                    this.isError = false;
                }).catch(error => {
                    this.isError = true;
                    this.message = error.msg;
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