import Axios from './lib/axios';
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router'
import Frame from './view/frame';
import routes from './lib/routes';

// 暴露在全局
Vue.prototype.$axios = Axios;

Vue.use(ElementUI);
Vue.use(VueRouter);

new Vue({
    el: '#frame',
    router: new VueRouter(routes),
    render: h => h(Frame)
});