<template>
    <div class="container">
        <ul class="nav nav-tabs swatch-cyan" role="tablist">
            <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 1}" v-on:click="tab = 1">Informations</a></li>
            <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 2}" v-on:click="tab = 2">Medical Rooms</a></li>
            <li class="nav-item" ><a class="nav-link tabic" v-bind:class="{active: tab === 3}" v-on:click="tab = 3">Doctors</a></li>
            <li class="nav-item"><a class="nav-link tabic" v-bind:class="{active: tab === 4}" v-on:click="tab = 4">Pricelist</a></li>
            <li class="nav-item"><a class="nav-link tabic" v-bind:class="{active: tab === 5}" v-on:click="tab = 5">Free appointments</a></li>
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
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 5}">
            </div>
        </div>
    </div>
</template>

<script>
    import RoomList from "../views/SearchMedicalRooms.vue"
    import {LMap, LTileLayer, LMarker } from 'vue2-leaflet';
    import { OpenStreetMapProvider } from 'leaflet-geosearch';
    const provider = new OpenStreetMapProvider();

    export default {
        name: "ClinicProfile",
        components: {RoomList,
                     LMap,
                     LTileLayer,
                     LMarker
                },
        data: function () {
            return {
                clinic: null,
                clinicName: undefined,
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
        }
    }
</script>

<style scoped>

</style>