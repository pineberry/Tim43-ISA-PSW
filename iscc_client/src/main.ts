import Vue from "vue";
import App from "./App.vue";
import VueRouter from 'vue-router'
import "./registerServiceWorker";
import router from "./router";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";

Vue.config.productionTip = false;
Vue.prototype.axios = axios
Vue.use(VueRouter)


new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
