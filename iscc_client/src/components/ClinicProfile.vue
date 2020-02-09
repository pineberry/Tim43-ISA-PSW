<template>
    <div class="container">
        <ul class="nav nav-tabs swatch-cyan" role="tablist">
            <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 1}" v-on:click="tab = 1">Informations</a></li>
            <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 2}" v-on:click="tab = 2">Medical Rooms</a></li>
            <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 3}" v-on:click="tab = 3">Doctors</a></li>
            <li class="nav-item"><a class="nav-link tabic" v-bind:class="{active: tab === 4}" v-on:click="tab = 4">Pricelist</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 1}">
                <div v-if="clinic" class="row">
                    <div class="col-6">
                        <p>Name: {{this.clinic.name}}</p>
                        <p>Descrption: {{this.clinic.description}}</p>
                        <p>Address: {{this.clinic.address}}</p>
                        <p><router-link class="btn btn-primary" :to="{path: '/editClinic', query: {name: this.clinic.name}}">Edit</router-link></p>
                    </div>
                    <div class="col-6">
                        <l-map :zoom="zoom" :center="center">
                            <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
                            <l-marker v-if="marker" :lat-lng="marker"></l-marker>
                        </l-map>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 2}">
                <RoomList/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 3}">
                <div class="row marginTop">
                    <div class="col-6">
                        <div class="form-group">
                            <div class="form-group">
                                <label for="inputName">Name:</label>
                                <input id="inputName" type="text" class="form-control" placeholder="Enter name" v-model="doctorName">
                            </div>
                            <div class="form-group">
                                <label for="inputLastName">Last name:</label>
                                <input id="inputLastName" type="text" class="form-control" placeholder="Enter last name" v-model="doctorLastName">
                            </div>
                        </div>
                        <button type="submit" v-on:click="searchDoctors()" class="btn btn-primary">Search</button>
                    </div>
                </div>

                <table class="table marginTop">
                    <thead class="thead-dark">
                        <th scope="col">Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Remove</th>
                    </thead>
                    <tbody>
                        <tr v-for="doc in doctors" :key="doc.id">
                            <td>{{doc.firstName}} {{doc.lastName}}</td>
                            <td>{{doc.address}}</td>
                            <td><button class="btn btn-primary" v-on:click="remove(doc)">Remove</button> </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 4}">
                <SearchProcedureTypes/>
            </div>
        </div>
    </div>
</template>

<script>
    import RoomList from "../views/SearchMedicalRooms.vue";
    import SearchProcedureTypes from "@/views/SearchProcedureTypes";
    import {LMap, LTileLayer, LMarker } from 'vue2-leaflet';
    import { OpenStreetMapProvider } from 'leaflet-geosearch';
    const provider = new OpenStreetMapProvider();

    export default {
        name: "ClinicProfile",
        components: {RoomList,
                     SearchProcedureTypes,
                     LMap,
                     LTileLayer,
                     LMarker
                },
        data: function () {
            return {
                clinic: null,
                doctors: null,
                clinicName: undefined,
                doctorName: undefined,
                doctorLastName: undefined,
                types: [],
                tab: 1,
                zoom:13,
                center: L.latLng(45.25, 19.80),
                url:'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
                attribution:'&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
                marker: undefined,
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/clinic/" + this.$route.query.name)
                .then(response => {
                    this.clinic = response.data;
                    provider
                        .search({ query: this.clinic.address })
                        .then(function(result) {
                            this.center = L.latLng(result[0].y, result[0].x);
                            this.marker = L.latLng(result[0].y, result[0].x);
                        }.bind(this));
                })

            this.axios.get("http://localhost:8080/doctor/clinic/doctors/" + localStorage.getItem("user_id"))
                .then(response => {
                    this.doctors = response.data;
                })
        },
        methods: {
            remove: function(doc) {
                if(localStorage.getItem("typeOfUser") != "clinicAdministrator") {
                    alert('Only clinic admin has permission for this action!');
                    return;
                }
                this.axios.delete("http://localhost:8080/doctor/" + doc.id)
                    .then(response => {
                        let index = this.doctors.indexOf(doc);
                        this.doctors.splice(index, 1);
                    })
                    .catch(error => {
                        alert('This medic cannot be deleted due to scheduled appointments!');
                    })
            },

            searchDoctors: function() {
                let name = 'empty';
                let lastName = 'empty';

                if (this.doctorName != undefined && this.doctorName.trim() != '') {
                    name = this.doctorName.trim();
                }

                if (this.doctorLastName != undefined && this.doctorLastName.trim() != '') {
                    lastName = this.doctorLastName.trim();
                }
                if (name != 'empty' || lastName != 'empty') {
                    this.axios.get("http://localhost:8080/doctor/find/" + name+ '/' + lastName
                        + '/' + localStorage.getItem("user_id"))
                        .then(response => {
                            this.doctors = response.data;
                        })
                } else {
                    alert('At least one search parameter has to be entered!');
                }
            }
        }
    }
</script>

<style scoped>
    .marginTop {
        margin-top: 10px;
    }
</style>