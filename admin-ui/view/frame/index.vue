<style>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        overflow: hidden;
    }
    .layout-logo{
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    .layout-nav{
        width: 100px;
        margin: 0 auto;
        margin-right: 20px;
    }
</style>
<template>
    <div v-if="status==-1">
        <Spin size="large" fix key="__status_1">{{msg}}</Spin>
    </div>
    <div v-else-if="status==0">
        <Spin size="large" fix key="__status_0"></Spin>
    </div>
    <div v-else-if="status==1">
        <InitializeView @finished="handleInitializeFinished"></InitializeView>
    </div>
    <div v-else="status==2">
        <LoginView v-if="isUnauthorized" @finished="handleLoginFinished"></LoginView>
        <div v-else class="layout">
            <Layout>
                <Header>
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                        <Avatar style="background-color: #87d068; margin-right:10px;" icon="ios-person" />
                        <Dropdown @on-click="handleUserAction">
                            <a style="color: #ffffff;" href="javascript:void(0)">
                                <span>{{user.name || user.username}}</span>
                                <Icon type="ios-arrow-down"></Icon>
                            </a>
                            <DropdownMenu slot="list">
                                <DropdownItem name="USER_ZONE">用户中心</DropdownItem>
                                <DropdownItem name="USER_LOGOUT">用户登出</DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                    </div>
                </Header>
                <Layout>
                    <Sider hide-trigger :style="{background: '#fff'}">
                        <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                            <Submenu name="1">
                                <template slot="title">
                                    <Icon type="ios-navigate"></Icon>
                                    Item 1
                                </template>
                                <MenuItem name="1-1">Option 1</MenuItem>
                                <MenuItem name="1-2">Option 2</MenuItem>
                                <MenuItem name="1-3">Option 3</MenuItem>
                            </Submenu>
                            <Submenu name="2">
                                <template slot="title">
                                    <Icon type="ios-keypad"></Icon>
                                    Item 2
                                </template>
                                <MenuItem name="2-1">Option 1</MenuItem>
                                <MenuItem name="2-2">Option 2</MenuItem>
                            </Submenu>
                            <Submenu name="3">
                                <template slot="title">
                                    <Icon type="ios-analytics"></Icon>
                                    Item 3
                                </template>
                                <MenuItem name="3-1">Option 1</MenuItem>
                                <MenuItem name="3-2">Option 2</MenuItem>
                            </Submenu>
                        </Menu>
                    </Sider>
                    <Layout :style="{padding: '0 24px 24px'}">
                        <Breadcrumb :style="{margin: '24px 0'}">
                            <BreadcrumbItem>Home</BreadcrumbItem>
                            <BreadcrumbItem>Components</BreadcrumbItem>
                            <BreadcrumbItem>Layout</BreadcrumbItem>
                        </Breadcrumb>
                        <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                            Content
                        </Content>
                    </Layout>
                </Layout>
            </Layout>
        </div>
    </div>
</template>
<script>
    import LoginView from './login';
    import InitializeView from './Initializer';

    export default {
        components: {
            LoginView: LoginView,
            InitializeView: InitializeView
        },
        data() {
            return {
                status: 0,
                msg: '正在初始化...',
                isUnauthorized: true,
                user: null,
            }
        },
        created() {
            window.__vueFrame = this;
            this.$axios.get('initialize/isInitialize.json')
                .then(response => {
                    // 未初始化
                    if (!response.data) {
                        this.status = 1;
                    }
                    // 已初始化
                    else {
                        this.$axios.get('user/current.json').then(response => {
                            this.user = response.data;
                            this.isUnauthorized = false;
                            this.status = 2;
                        }).catch(error => {
                            if (error.code === -401) {
                                this.status = 2;
                            } else {
                                this.status = -1
                                this.msg = error.msg;
                            }
                        });
                    }
                }).catch(error => {
                    this.status = -1;
                    this.msg = error.msg;
                }
            );
        },
        methods: {
            toggleVisible() {
                this.visible = !this.visible;
            },
            handleInitializeFinished() {
                this.status = 2;
            },
            handleLoginFinished(userInfo) {
                this.user = userInfo;
                this.isUnauthorized = false;
            },
            handleLogout(){
                this.$axios.post('auth/logout.do').then(response=>{
                    this.isUnauthorized = true;
                }).catch(error=>{
                    this.$Message.error(error.msg);
                }).finally(()=>{

                });
            },
            handleUserAction(name){
                if(name === 'USER_LOGOUT'){
                    this.handleLogout();
                }
            }
        }
    }
</script>