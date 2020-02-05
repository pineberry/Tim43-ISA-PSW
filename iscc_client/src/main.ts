import Vue from "vue";
import App from "./App.vue";
import VueRouter from 'vue-router'
import "./registerServiceWorker";
import router from "./router";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import VueResource from "vue-resource";
import lodash from 'lodash';

import modal from "vue-js-modal";

Vue.use(lodash);
Vue.prototype.lodash = lodash;


Vue.config.silent = true;

Vue.mixin({
	data: function () {
		return {
			role: localStorage.getItem('typeOfUser')
		}
	}
});

Vue.config.productionTip = false;
Vue.prototype.axios = axios;
Vue.use(VueRouter);
Vue.use(modal, { dialog: true, dynamic: true })

// telling vue.js to use this package
Vue.use(VueResource);

axios.interceptors.request.use((config) => {
	const authToken = localStorage.getItem('auth')
	if (authToken)
		config.headers['Authorization'] = authToken;
	return config;
}, (error) => {
	Promise.reject(error)
	return error;
});

axios.interceptors.response.use((response) => {

	return response;
}, (error) => {
	return Promise.reject(error.message);
});



new Vue({
	router,
	render: h => h(App)
}).$mount("#app");