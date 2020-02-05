<template>
  <div class="home">
    <div
      class="fluid"
      v-if="!user"
    >
      <Login />
    </div>
    <div v-else>
      <div v-if="role === 'patient'">
        <PatientHome />
      </div>
      <div v-if="role === 'clinicCenterAdministrator'">
        <ClinicCenterAdministratorHome />
      </div>
      <div v-if="role === 'doctor'">
        <DoctorHome />
      </div>
      <div v-if="role === 'nurse'">
        <NurseHome />
      </div>
      <div v-if="role === 'clinicAdministrator'">
        <ClinicAdministratorHome />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  components: {
    Login: () => import("../components/Login.vue"),
    PatientHome : () => import('../components/PatientHome.vue'),
    ClinicCenterAdministratorHome : () => import('../components/ClinicCenterAdministratorHome.vue'),
    DoctorHome : () => import('../components/DoctorHome.vue'),
    NurseHome : () => import('../components/NurseHome.vue'),
    ClinicAdministratorHome : () => import('../components/ClinicAdministratorHome.vue')
  },
  data: function() {
    return {
      user: localStorage.getItem("user")
    };
  },
  methods: {
    logout: function() {
      localStorage.removeItem("auth");
      alert("Izlogovani ste");
    }
  }
};
</script>
