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
import SearchDoctors from "../views/SearchDoctors.vue";
import ScheduleAppointment from "../views/ScheduleAppointment.vue"
import EditProcedureType from "../views/EditProcedureType.vue";
import SearchProcedureType from "../views/SearchProcedureTypes.vue";
import ClinicCenterAdministratorHome from '../components/ClinicCenterAdministratorHome.vue';
import UncheckedPrescriptions from "../views/UncheckedPrescriptions.vue";
import EditMedicalRecord from "../views/EditMeidcalRecord.vue";
import EditMedicalRoom from "../views/EditMedicalRoom.vue";
import ClinicAdminProfile from "../views/ClinicAdminProfile.vue";
import EditClinicAdmin from "../views/EditClinicAdmin.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/editMedicalRecord/:id",
    name: "editMedicalRecord",
    component: EditMedicalRecord
  },
  {
    path: "/uncheckedPrescriptions",
    name: "uncheckedPrescriptions",
    component: UncheckedPrescriptions
  },
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
        path: "/searchRooms/:id",
        name: "searchMedicalRooms",
        component: SeachMedicalRooms
    },
    {
        path: "/searchDoctors",
        name: "searchDoctors",
        component: SearchDoctors
    },
    {
        path: "/scheduling",
        name: "scheduleAppointment",
        component: ScheduleAppointment
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
  },
  {
    path: "/clinicCenterAdministratorHome",
    name: "clinicCenterAdministratorHome",
    component : ClinicCenterAdministratorHome
  },
  {
    path: "/clinicAdministratorHome",
    name: "clinicAdministratorHome",
    component : () => import('../components/ClinicAdministratorHome.vue')
  },
  {
    path: "/nurseHome",
    name: "nurseHome",
    component : () => import('../components/NurseHome.vue')
  },
  {
    path: "/doctorHome",
    name: "doctorHome",
    component : () => import('../components/DoctorHome.vue')
  },
  {
    path: "/patientProfile",
    name: "patientProfile",
    component: () => import('../views/PatientProfile.vue')
  },
  {path: "/editRoom",
    name: "editMedicalRoom",
    component: EditMedicalRoom
  },
  {
    path: "/adminProfile",
    name: "clinicAdminProfile",
    component: ClinicAdminProfile
  },
  {
    path: "/editAdmin",
    name: "editClinicAdmin",
    component: EditClinicAdmin
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;

