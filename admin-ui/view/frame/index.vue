<style>
    .demo-spin-icon-load {
        animation: ani-demo-spin 1s linear infinite;
    }
</style>
<template>
    <div v-if="status==-1">
        <Spin size="large" fix key="__status_1">{{msg}}</Spin>
    </div>
    <div v-else-if="status==0">
        <Spin size="large" fix key="__status0"></Spin>
    </div>
    <div v-else-if="status==1">
        <RegistryView @finish="finishRegistry"></RegistryView>
    </div>
    <div v-else="status==2">
        <LoginView v-if="isUnauthorized"></LoginView>
        <div v-else class="layout">
            <Layout>
                <Header>
                    <Menu mode="horizontal" theme="dark" active-name="1">
                        <div class="layout-logo"></div>
                        <div class="layout-nav">
                            <MenuItem name="1">
                                <Icon type="ios-navigate"></Icon>
                                Item 1
                            </MenuItem>
                            <MenuItem name="2">
                                <Icon type="ios-keypad"></Icon>
                                Item 2
                            </MenuItem>
                            <MenuItem name="3">
                                <Icon type="ios-analytics"></Icon>
                                Item 3
                            </MenuItem>
                            <MenuItem name="4">
                                <Icon type="ios-paper"></Icon>
                                Item 4
                            </MenuItem>
                        </div>
                    </Menu>
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
    import RegistryView from './registry';

    export default {
        components: {
            LoginView: LoginView,
            RegistryView: RegistryView
        },
        data() {
            return {
                status: 0,
                msg: '正在初始化...',
                isUnauthorized: true,
                isNeedRegistry: false,
            }
        },
        created() {
            $http.get('center/isInited.json')
                .then(response => {
                    // 未初始化
                    if (!response.data) {
                        this.status = 1;
                    }
                    // 已初始化
                    else {
                        this.status = 2;
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
            finishRegistry(user){
                console.log(user);
                this.status = 2;
            }
        }
    }
</script>