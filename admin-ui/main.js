
import 'iview/dist/styles/iview.css';
import axios from 'axios';

import Vue from 'vue';
import iview from 'iview';
import VueRouter from 'vue-router'
import Frame from './view/frame';
import routes from './data/routes';

// 暴露在全局
window.axios = axios;

Vue.use(iview);
Vue.use(VueRouter);

new Vue({
    el: '#frame',
    router: new VueRouter(routes),
    render: h => h(Frame)
});