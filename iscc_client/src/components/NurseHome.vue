<template>
  <div class="container">
      <ul class="nav nav-tabs swatch-cyan" role="tablist">
        <li class="nav-item">
          <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="http://localhost:8081/nurseHome" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
          <div class="dropdown-menu">
              <a class="dropdown-item bg-danger text-white" v-on:click="logout" href="http://localhost:8081/login">
                  Logout
              </a>
          </div>
        </li>
        <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 1}" v-on:click="tab = 1">Patients</a></li>
        <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 2}" v-on:click="tab = 2">Unchecked Prescriptions</a></li>
        <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 3}" v-on:click="tab = 3">Checked Prescriptions</a></li>
        <li class="nav-item"><a class="nav-link tabic" v-bind:class="{active: tab === 4}" v-on:click="tab = 4">Calendar</a></li>
        <li class="nav-item"><a class="nav-link tabic" v-bind:class="{active: tab === 5}" v-on:click="tab = 5">User Details</a></li>
        <li class="nav-item"><a class="nav-link tabic" v-bind:class="{active: tab === 6}" v-on:click="tab = 6">Vacation request</a></li>
      </ul>
      <div class="tab-content">
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 1}" >
              <Patients/>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 2}" >
              <UncheckedPrescriptions/>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 3}" >
              <CheckedPrescriptions/>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 4}" >
              <h2>To be implemented</h2>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 6}" >
              <h2>To be implemented</h2>
          </div>
          <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 5}" >
              <form>
                <div class="row" v-if="nurse">
                    <div class="col-4">
                      <div class="form-group">
                          <label>First Name</label>
                          <input type="text" class="form-control" v-model="nurse.firstName" readonly="!edit">
                      </div>
                      <div class="form-group">
                          <label>Last Name</label>
                          <input type="text" class="form-control" v-model="nurse.lastName" readonly="!edit">
                      </div>
                      <div class="form-group">
                          <label>Address</label>
                          <input type="text" class="form-control" v-model="nurse.address" readonly="!edit">
                      </div>
                      <div class="form-group">
                          <label>City</label>
                          <input type="text" class="form-control" v-model="nurse.city" readonly="!edit">
                      </div>
                      <div class="form-group">
                          <label>State</label>
                          <input type="text" class="form-control" v-model="nurse.state" readonly="!edit">
                      </div>
                    </div>
                    <div class="col-4">
                      <div class="form-group">
                          <label>Email</label>
                          <input type="text" class="form-control" v-model="nurse.email" readonly="!edit">
                      </div>
                      <div class="form-group">
                          <label>Phone Number</label>
                          <input type="text" class="form-control" v-model="nurse.phoneNumber" readonly="!edit">
                      </div>
                    </div>
                  </div>
              </form>
          </div>
      </div>
  </div>
</template>

<script>
import UncheckedPrescriptions from "../views/UncheckedPrescriptions";
import Patients from "./Patients";
import CheckedPrescriptions from "./CheckedPrescriptions";
export default {
    name : "NurseHome",
    components: {Patients, UncheckedPrescriptions, CheckedPrescriptions},
    data : function() {
        return {
            user : localStorage.getItem('user'),
            tab : 1,
            nurse : null,
            edit : false
        }
    },
    mounted() {
        this.getNurse();
    },
    methods : {
        logout : function () {
            localStorage.removeItem("auth");
            localStorage.removeItem("email");
            localStorage.removeItem("user");
            localStorage.removeItem("user_id");
            localStorage.removeItem("typeOfUser");
        },
        getNurse : function () {
            this.axios.get("http://localhost:8080/nurse/" + localStorage.getItem('user_id'))
                .then(response => {this.nurse = response.data})
                .catch(error => {alert(error)})
        }
    }
}
</script>

<style>
    a.tabic:hover{
        cursor: pointer;
    }
</style>