import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import AddClinic from "../views/AddClinic.vue";
import Register from "../components/Register.vue";
import Login from "../components/Login.vue";
import AddClinicAdmin from "../views/AddClinicAdmin.vue";
import RegistrationRequest from "../views/RegistrationRequest.vue";
import Codebook from "../views/Codebook.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "/addClinic",
    name: "addClinic",
    component: AddClinic
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "/addClinicAdmin",
    name: "addClinicAdmin",
    component: AddClinicAdmin
  },
  {
    path: "/registrationRequest/:id",
    name: "registrationRequest",
    component: RegistrationRequest
  },
  {
    path: "/codebook",
    name: "codebook",
    component: Codebook
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
