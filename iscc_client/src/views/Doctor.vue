<template>
  <div class="container row justify-content-center">
        <div class="card m-2 col-6">
            <div class="card-body" v-if="doctor">
                <h3 class="card-title">{{doctor.firstName}} {{doctor.lastName}}</h3>
                <div class="row">
                    <div class="col-6">
                        <div class="row">
                            <div class="col-6">
                                <img src="../images/doctor.png" alt="..." class="rounded float-left">
                            </div>
                        </div>
                        <p>Average rating: {{doctor.averageRating}}</p>
                    </div>
                    <div class="col-6">
                        <p>Specialization: {{doctor.specialized.typeName}} - {{doctor.specialized.typeDescription}}</p>
                        <p>Location: <b><a class="card-link" :href="'http://localhost:8081/clinic_'+doctor.clinic.id">{{doctor.clinic.name}}</a></b> {{doctor.address}} - {{doctor.city}}</p>
                        <p>Working time: {{doctor.workingtimeStart}} - {{doctor.workingtimeEnd}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "doctor",
    data: function() {
        return{
            doctor: Object,
            doctorID: undefined
        }
    }, 
    mounted: function() {
        this.doctorID = this.$route.params.id;
        this.axios.get("http://localhost:8080/doctor/"+this.doctorID)
        .then(response => {
                this.response = response
                this.doctor = this.response.data
                console.log(response);
                console.log(this.doctor);
                
                this.workingtimeEnd = +this.doctor.workingtimeEnd.slice(0,2)
                this.workingtimeStart = +this.doctor.workingtimeStart.slice(0,2)
            })
        .catch(error => {
            alert(error.response.data)
            })
    }
}
</script>

<style>

</style>