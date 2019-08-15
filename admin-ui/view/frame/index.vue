<style>
    .layout {
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        overflow: hidden;
    }

    .layout-logo {
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }

    .layout-nav {
        width: 100px;
        margin: 0 auto;
        margin-right: 20px;
    }

    .status__1 {
        position: fixed;
        margin: auto;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }

    .status__1 {
        height: 400px;
    }
</style>
<template>
    <el-card v-if="status===-1" v-loading="!isError" class="status__1 message-card" :element-loading-text="message">
        <div v-if="isError" class="message-wrap">
            <i class="el-icon-error message-error-icon"></i>
            <p class="message-text">{{message}}</p>
        </div>
    </el-card>
    <div v-else-if="status===0">
        <v-initialize-view @finished="handleInitializeFinished"></v-initialize-view>
    </div>
    <div v-else-if="status===1">
        <v-login-view @finished="handleLoginFinished"></v-login-view>
    </div>
    <div v-else>
        <div class="layout">
            aaa
        </div>
    </div>
</template>
<script>
    import '../common.css';
    import LoginView from './login';
    import InitializeView from './Initializer';

    export default {
        components: {
            'v-login-view': LoginView,
            'v-initialize-view': InitializeView
        },
        data() {
            return {
                status: -1,
                message: '正在检测初始化...',
                isError: false,
                isInitialized: false,
                user: {},
            }
        },
        created() {
            window.__vueFrame = this;
            this.$axios.get('initialize/isInitialize.json')
                .then(response => {
                    // 未初始化
                    if (!response.data) {
                        this.status = 0;
                    }
                    // 已初始化
                    else {
                        this.$axios.get('user/current.json').then(response => {
                            this.user = response.data;
                            this.status = 2;
                        }).catch(error => {
                            if (error.code === -401) {
                                this.status = 1;
                            } else {
                                this.setStatusError(error.msg);
                            }
                        });
                    }
                }).catch(error => {
                    this.setStatusError(error.msg);
                }
            );
        },
        methods: {
            setStatusError(message) {
                this.status = -1;
                this.message = message;
                this.isError = true;
            },
            toggleVisible() {
                this.visible = !this.visible;
            },
            handleInitializeFinished() {
                this.status = 1;
            },
            handleLoginFinished(userInfo) {
                this.user = userInfo;
                this.status = 2;
            },
            handleLogout() {
                this.$axios.post('auth/logout.do').then(response => {
                    this.isUnauthorized = true;
                }).catch(error => {
                    this.$Message.error(error.msg);
                }).finally(() => {

                });
            },
            handleUserAction(name) {
                if (name === 'USER_LOGOUT') {
                    this.handleLogout();
                }
            }
        }
    }
</script>