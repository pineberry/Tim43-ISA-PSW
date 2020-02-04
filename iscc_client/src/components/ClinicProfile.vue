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
                    <div class="col-8">
                        <p>Name: {{this.clinic.name}}</p>
                        <p>Descrption: {{this.clinic.description}}</p>
                        <p>Address: {{this.clinic.address}}</p>
                    </div>
                    <div class="col-4">
                        <router-link class="btn btn-primary" :to="{path: '/editClinic', query: {name: this.clinic.name}}">Edit</router-link>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 2}">
                <RoomList/>
            </div>
        </div>
    </div>
</template>

<script>
    import RoomList from "../views/SearchMedicalRooms.vue"
    export default {
        name: "ClinicProfile",
        components: {RoomList},
        data: function () {
            return {
                clinic: null,
                clinicName: undefined,
                tab: 1
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/clinic/" + this.$route.query.name)
                .then(response => {this.clinic = response.data})
        }
    }
</script>

<style scoped>

</style>