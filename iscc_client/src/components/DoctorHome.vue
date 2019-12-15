<template>
  <div class="container">
      <ul class="nav nav-tabs swatch-cyan">
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="http://localhost:8081/doctorHome" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
                <div class="dropdown-menu">
					<router-link class="dropdown-item" to="/patientHome">Profile</router-link>
					<a class="dropdown-item bg-danger text-white" v-on:click="logout" href="http://localhost:8081">
                        Logout
                    </a>
                </div>
            </li>
          <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 1}" v-on:click="tab = 1">Reports</a></li>
        </ul>
      <div class="tab-content">
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 1}" >
              <ExaminationReports/>
          </div>
      </div>
  </div>
</template>

<script>
import ExaminationReports from "./ExaminationReports";
export default {
    components: {ExaminationReports},
    data : function() {
        return {
            user : localStorage.getItem('user'),
            tab : 1
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