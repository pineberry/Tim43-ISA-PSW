<template>
  <div id="app" class="container">
    <!-- <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Add
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link class="nav-item nav-link" to="/addClinic">Clinic</router-link>
              <router-link class="nav-link nav-item" to="/addClinicAdmin">Clinic Admin</router-link>
              <router-link class="nav-link nav-item" to="/codebook">Codebook</router-link>
              <router-link class="nav-link nav-item" to="/addCenterAdmin">Center Admin</router-link>
              <router-link class="nav-link nav-item" to="/addDoctor">Doctor</router-link>
              <router-link class="nav-link nav-item" to="/addProcedureType">Procedure type</router-link>
              <router-link class="nav-link nav-item" to="/addMedicalRoom">Medical room</router-link>
              <router-link class="nav-link nav-item" to="/addAppointment">Procedure</router-link>
              <router-link class="nav-link nav-item" to="/report">Report</router-link>
              <router-link class="nav-link nav-item" to="/searchRooms/0">Search rooms</router-link>
              <router-link class="nav-link nav-item" to="/searchTypes">Search types</router-link>
              <router-link class="nav-link nav-item" to="/adminProfile">Clinic Admin</router-link>
            </div>
          </div>
        </div>
      </div>
    </nav> -->
    <div class="row mt-3 mb-3">
        <div class="col-1 p-0 ml-3">
            <router-link to="/">
              <img src="./images/iscc_icon.svg" alt="nema">
            </router-link>
        </div>
        <div class="align-self-center ml-3">
            <p class="h1 color-lilac font-weight-bold m-0">Clinic center</p>
        </div>
    </div>
    <div v-if="role === 'patient'">
        <PatientHome />
    </div>
    <div v-if="role === 'nurse'">
        <NurseHome />
    </div>
    <div v-if="role === 'clinicAdministrator'">
        <ClinicAdministratorHome />
    </div>
    <div v-if="role === 'clinicCenterAdministrator'">
        <ClinicCenterAdministratorHome />
    </div>
    <div v-if="role === 'doctor'">
        <DoctorHome />
    </div>
    <router-view />
  </div>
</template>

<script>

export default {
  components: {
    PatientHome : () => import('../src/components/PatientHome.vue'),
    DoctorHome : () => import('../src/components/DoctorHome.vue'),
    ClinicAdministratorHome : () => import('../src/components/ClinicAdministratorHome.vue'),
    ClinicCenterAdministratorHome : () => import('../src/components/ClinicCenterAdministratorHome.vue'),
    NurseHome : () => import('../src/components/NurseHome.vue')
  },
  data : function () {
    return {
      
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Ubuntu');

#app {
  font-family: "Ubuntu";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
.nav-link{
    color: var(--gray-dark);
}
.nav-link:hover{
  color: #b4d8ff;
}
.color-lilac {
  background: -webkit-linear-gradient(top left, #007BFF, #b4d8ff);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

#app a:hover {
  text-decoration: none;
}

#app .w-05 {
    width: 49% !important;
}
#app .w-025 {
    width: 24.5% !important;
}
</style>
