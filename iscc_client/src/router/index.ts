import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import AddClinic from "../views/AddClinic.vue";
import Register from "../components/Register.vue";
import Login from "../components/Login.vue";
import AddClinicAdmin from "../views/AddClinicAdmin.vue";
import RegistrationRequest from "../views/RegistrationRequest.vue";
import Codebook from "../views/Codebook.vue";
import AddCenterAdmin from "../views/AddCenterAdmin.vue";
import ActivateAccount from "../components/ActivateAccount.vue";
import AddDoctor from "../views/AddDoctor.vue";
import AddProcedureType from "../views/AddProcedureType.vue";
import AddMedicalRoom from "../views/AddMedicalRoom.vue";
import AddAppointment from "../views/AddAppointment.vue";
import Report from "../views/Report.vue";
import PatientHome from "../components/PatientHome.vue";
import SearchAvailableDoctorsAtClinic from "../views/SearchAvailableDoctorsAtClinic.vue";
import AdminPasswordChange from "../views/AdminPasswordChange.vue"
import SeachMedicalRooms from "../views/SearchMedicalRooms.vue";
import EditProcedureType from "../views/EditProcedureType.vue";
import SearchProcedureType from "../views/SearchProcedureTypes.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/changePassword",
    name: "adminPasswordChange",
    component: AdminPasswordChange
  },
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
  },
  {
    path: "/addCenterAdmin",
    name: "addCenterAdmin",
    component: AddCenterAdmin
  },
  {
    path: "/activateAccount/:id",
    name: "activateAccount",
    component: ActivateAccount
  },
  {
    path: "/addDoctor",
    name: "addDoctor",
    component: AddDoctor
  },
  {
    path: "/addProcedureType",
    name: "addProcedureType",
    component: AddProcedureType
  },
  {
    path: "/addMedicalRoom",
    name: "addMedicalRoom",
    component: AddMedicalRoom
  },
  {
    path: "/addAppointment",
    name: "addAppointment",
    component: AddAppointment
  },
  {
    path: "/patientHome",
    name: "patientHome",
    component: PatientHome
  },
  {
    path: "/searchAvailable",
    name: "searchAvailable",
    component: SearchAvailableDoctorsAtClinic
  },
  {
    path: "/report",
    name: "report",
    component: Report
  },
  {
    path: "/searchRooms",
    name: "searchMedicalRooms",
    component: SeachMedicalRooms
  },
  {
    path: "/editType",
    name: "editProcedureType",
    component: EditProcedureType
  },
  {
    path: "/searchTypes",
    name: "searchProcedureTypes",
    component: SearchProcedureType
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
