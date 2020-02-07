<template>
  <div class="container" v-if="doctor">
      <ul class="nav nav-tabs swatch-cyan">
          <li class="nav-item">
              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="http://localhost:8081/doctorHome" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
              <div class="dropdown-menu">
                  <a class="dropdown-item bg-danger text-white" v-on:click="logout" href="http://localhost:8081">
                      Logout
                  </a>
              </div>
          </li>
          <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 2}" v-on:click="tab = 2">Profile</a></li>
          <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 3}" v-on:click="tab = 3">Patients</a></li>
          <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 4}" v-on:click="tab = 4">Calendar</a></li>
          <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 5}" v-on:click="tab = 5">Vacation request</a></li>
        </ul>
      <div class="tab-content">
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 2}" >
              <div v-if="doctor" class="row marginTop">
                  <div class="col-4">
                      <p>First name: {{doctor.firstName}}</p>
                      <p>Last name: {{doctor.lastName}}</p>
                      <p>Address: {{doctor.address}}</p>
                      <p>City: {{doctor.city}}</p>
                      <p>State: {{doctor.state}}</p>
                      <p>Phone number: {{doctor.phoneNumber}}</p>
                      <p>E-mail: {{doctor.email}}</p>
                  </div>
                  <div class="col-4">
                      <p>Clinic: {{clinic.name}}</p>
                      <p>Working time: {{doctor.workingtimeStart}} - {{doctor.workingtimeEnd}}</p>
                      <p>Specialization: {{specialized.typeName}}</p>
                      <p>Average rating: {{doctor.averageRating}}</p>
                  </div>
                  <div class="col-4">
                      <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle" type="button" id="drdpButton"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Settings
                          </button>
                          <div class="dropdown-menu" aria-labelledby="drdpButton">
                              <router-link class="dropdown-item" to="/editDoctor">Edit profile</router-link>
                              <router-link class="dropdown-item" to="/changePasswordDoctor">Change password</router-link>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 3}" >
              <SearchPatients/>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 4}" v-if="doctor.medicalProcedures && doctor.attendingProcedures">
              <Calendar :doctor="doctor"/>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 5}" v-if="doctor">
              <VacationRequest :doctor="doctor" :nurse="null" />
          </div>
      </div>
  </div>
</template>

<script>
import SearchPatients from "../views/SearchPatients.vue";
import Calendar from "../components/Calendar";
import VacationRequest from "./VacationRequest";
export default {
    components: {SearchPatients, Calendar, VacationRequest},
    data : function() {
        return {
            user : localStorage.getItem('user'),
            tab : 4,
            doctor: Object,
            clinic: Object,
            specialized : Object
        }
    },
    mounted: function() {
      this.axios.get("http://localhost:8080/doctor/" + localStorage.getItem("user_id"))
          .then(response => {
              this.doctor = response.data;
              this.clinic = this.doctor.clinic;
              this.specialized = this.doctor.specialized;
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

<style scoped>
    .marginTop {
        margin-top: 10px;
    }
</style>