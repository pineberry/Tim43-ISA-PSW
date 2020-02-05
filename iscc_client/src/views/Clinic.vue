<template>
    <div class="container">
        <div class="col card m-1 mt-2">
            <div class="row card-title justify-content-center m-0">
                <p class="h3 m-0 mt-2 text-primary">{{clinic.name}}</p>
            </div>
            <div class="row card-title justify-content-center m-0">
                <p class="text-muted m-0">-{{clinic.description}}-</p>
            </div>
            <hr class="my-2">
            <div class="row m-0">
                <div class="w-05 card m-1" v-for="doctor in doctors" :key="doctor.id">
                    <div class="card-body">
                        <h3 class="card-title">{{doctor.firstName}} {{doctor.lastName}}</h3>
                        <hr class="my-1">
                        <div class="row mt-2">
                            <div class="col-3">
                                <div class="row">
                                    <div class="col">
                                        <img src="../images/doctor.png" alt="..." class="rounded float-left">
                                    </div>
                                </div>
                            </div>
                            <div class="col-8">
                                <p class="m-0 border-bottom"><small>Specialization:</small> <br> <b> {{doctor.specialized.typeName}} - {{doctor.specialized.typeDescription}}</b></p>
                                <p class="m-0 border-bottom"><small>Clinic:</small> <br>
                                    <b>
                                        <a class="special-link" :href="'http://localhost:8081/clinic_'+doctor.clinic.id"> {{doctor.clinic.name}}</a>
                                    </b>
                                    <small class="text-muted"><br>{{doctor.address}}, {{doctor.city}}</small>
                                </p>
                                <p class="m-0"><small>Average rating: <b>{{doctor.averageRating}}</b></small></p>
                            </div>
                        </div>
                        <div class="text-right">
                            <router-link class="btn mt-2 btn-outline-primary btn-sm" :to="'scheduling_'+doctor.id">Request an appointment</router-link>
                        </div>
                    </div>
                </div>
            </div>
            <hr class="my-2">
            <div class="row m-0">
                <p class="m-0">Clinic rating: <b>{{clinic.averageRating}}</b></p>
            </div>
            <div class="row m-0">
                <p class="m-0 mb-2">Address: <b>{{clinic.address}}</b></p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "clinic",
    data : function() {
        return {
            clinic : undefined,
            doctors : [], 
            clinicID : undefined
        }
    },
    beforeMount : function() {
        this.clinicID = this.$route.params.id;
        this.axios.get("http://localhost:8080/clinic/single/" + this.clinicID)
        .then(response => {
            this.clinic = response.data;
            this.doctors = this.clinic.doctors;
        });
    }
}
</script>

<style>

</style>