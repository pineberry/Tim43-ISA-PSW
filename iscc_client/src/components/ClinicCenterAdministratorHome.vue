<template>
  <div class="container">
      <ul class="nav nav-tabs swatch-cyan">
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="http://localhost:8081/clinicCenterAdministratorHome" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
                <div class="dropdown-menu">
					<router-link class="dropdown-item" to="/patientHome">Profile</router-link>
					<a class="dropdown-item bg-danger text-white" v-on:click="logout" href="http://localhost:8081">
                        Logout
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Add</a>
                <div class="dropdown-menu">
                    <router-link class="dropdown-item" to="/addClinic">Clinic</router-link>
                    <router-link class="dropdown-item" to="/addClinicAdmin">Clinic administrator</router-link>
                    <router-link class="dropdown-item" to="/addCenterAdmin">Clinic center administrator</router-link>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Codebooks</a>
                <div class="dropdown-menu">
                    <router-link class="dropdown-item" to="/codebook">Codebook</router-link>
                </div>
            </li>
        </ul>
  </div>
</template>

<script>
export default {
    data : function() {
        return {
            user : localStorage.getItem('user')
        }
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