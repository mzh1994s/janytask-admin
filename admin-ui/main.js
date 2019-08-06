import 'iview/dist/styles/iview.css';
import Http from './lib/http';

import Vue from 'vue';
import iview from 'iview';
import VueRouter from 'vue-router'
import Frame from './view/frame';
import routes from './lib/routes';

// 暴露在全局
window.$http = Http;

Vue.use(iview);
Vue.use(VueRouter);

new Vue({
    el: '#frame',
    router: new VueRouter(routes),
    render: h => h(Frame)
});