<template>
    <div class="fluid row m-3">
        <div class="col-12">
            <label>Enter info to filter:</label>
            <form>
                <div class="row justify-content-center">
                    <input type="text" class="form-control col m-1" placeholder="First name" id="firstName" v-model="firstName">
                    <input type="text" class="form-control col m-1" placeholder="Last name" id="lastName" v-model="lastName">
                    <input type="number" min="0" max="5" step=".1" class="form-control col m-1" placeholder="Rating" id="rating" v-model="rating"
                    data-toggle="tooltip" data-placement="top" data-html="true" title="Average rating">
                    <div class="form-check col m-1 align-self-center">
                            <input class="form-check-input" type="checkbox" id="allRatingsChecked" v-model="allRatingsChecked">
                            <label class="form-check-label" for="allRatingsChecked">
                                <small>Disregard ratings</small>
                            </label>
                        </div>
                </div>
            </form>
        </div>
        <div class="col-12">
            <div class="row">
                <div class="card m-1 w-05" v-for="doctor in filteredDoctors" :key="doctor.id">
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
        </div>
    </div>
</template>

<script>


export default {
    name: "doctors",
    components: {
    },
    data: function() {
        return {
            doctors : [],
            firstName : '',
            lastName : undefined,
            rating : 0,
            allRatingsChecked : false,
            response: undefined,
            user : localStorage.getItem('user')
        }
    },
    computed: {
        filteredDoctors (){
            if (!this.allRatingsChecked)
            {
                if (this.rating) {
                    return this.filterByLastName.filter((doctor) => {
                        return doctor.averageRating>=this.rating;
                    })
                } else {
                    return this.filterByLastName;
                }
            } else {
                return this.filterByLastName;
            }
        },
        filterByFirstName () {
            if (this.firstName) {
                return this.doctors.filter((doctor) => {
                    return doctor.firstName.toLowerCase().startsWith(this.firstName.toLowerCase());
                })
            } else {
                return this.doctors;
            }
        },
        filterByLastName () {
            if (this.lastName) {
                return this.filterByFirstName.filter((doctor) => {
                    return doctor.lastName.toLowerCase().startsWith(this.lastName.toLowerCase());
                })
            } else {
                return this.filterByFirstName;
            }
        }
    },
    updated: function (){
        if (this.allRatingsChecked) {
            document.getElementById('rating').setAttribute('disabled', 'disabled');
        } else if (!this.allRatingsChecked) {
            document.getElementById('rating').removeAttribute('disabled');
        }
    },
    beforeCreate: function() {
        this.axios.get("http://localhost:8080/doctor/all")
        .then(response => {
                this.response = response
                this.doctors = response.data
            })
        .catch(error => {
            alert(error.response.data)
            })
		},
    methods: {
    }

}
</script>

<style>
img {
    width: 100%;
}
</style>