<template>
  <div class="container">
      <ul class="nav nav-tabs swatch-cyan">
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="http://localhost:8081/clinicAdministratorHome" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
                <div class="dropdown-menu">
					<router-link class="dropdown-item" to="/patientHome">Profile</router-link>
					<a class="dropdown-item bg-danger text-white" v-on:click="logout" href="http://localhost:8081">
                        Logout
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <router-link class="nav-link" :to="{path: '/clinicProfile',query: {name: this.clinicName}}">Clinics</router-link>
            </li>
          <li class="nav-item">
              <router-link class="nav-link" :to="{path: '/businessReport'}">Report</router-link>
          </li>
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Add</a>
                <div class="dropdown-menu">
                    <router-link class="dropdown-item" to="/addDoctor">Doctor</router-link>
                    <router-link class="dropdown-item" to="/addProcedureType">Procedure type</router-link>
                    <router-link class="dropdown-item" to="/addMedicalRoom">Medical room</router-link>
                    <router-link class="dropdown-item" to="/addAppointment">Procedure</router-link>
                </div>
            </li>
        </ul>
        <router-view/>
  </div>
</template>

<script>
export default {
    data : function() {
        return {
            user : localStorage.getItem('user'),
            admin: null,
            clinicName: undefined
        }
    },
    mounted: function() {
        this.axios.get("http://localhost:8080/clinic/admin/" + localStorage.getItem('user_id'))
            .then(response =>{
                this.admin = response.data;
                this.clinicName = this.admin.clinicName;
            })
    },
    methods : {
        logout : function () {
            localStorage.removeItem("auth");
            localStorage.removeItem("email");
            localStorage.removeItem("user");
            localStorage.removeItem("user_id");
            localStorage.removeItem("typeOfUser");
        }
    }
}
</script>

<style>

</style>