<<<<<<< HEAD
import Vue from "vue";
import App from "./App.vue";
import VueRouter from 'vue-router'
import "./registerServiceWorker";
import router from "./router";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import VueResource from "vue-resource";

Vue.mixin({
	data: function() {
		return {
			role : localStorage.getItem('typeOfUser')
		}
	}
});

Vue.config.productionTip = false;
Vue.prototype.axios = axios;
Vue.use(VueRouter);

// telling vue.js to use this package
Vue.use(VueResource);

axios.interceptors.request.use((config) => {
	const authToken = localStorage.getItem('auth')
	if( authToken )
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
=======
import Vue from "vue";
import App from "./App.vue";
import VueRouter from 'vue-router'
import "./registerServiceWorker";
import router from "./router";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import VueResource from "vue-resource";


Vue.use(require('vue-moment'));
Vue.config.productionTip = false;
Vue.prototype.axios = axios;
Vue.use(VueRouter);

// telling vue.js to use this package
Vue.use(VueResource);

axios.interceptors.request.use((config) => {
	const authToken = localStorage.getItem('auth')
	if( authToken )
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
>>>>>>> 0039212423cc909c7af3e5bfc117a166d9bd683f
