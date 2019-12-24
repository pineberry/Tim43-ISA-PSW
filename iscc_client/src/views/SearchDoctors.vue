<template>
    <div class="fluid row m-3">
        <div class="col-12">
            <label>Enter info to filter:</label>
            <form>
                <div class="row">
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
                <div class="card m-1 col-5" v-for="doctor in filteredDoctors" :key="doctor.id">
                    <div class="card-body">
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
                                <p>Location: <b><a class="card-link" :href="'http://localhost:8081/clinic/'+doctor.clinic.id">{{doctor.clinic.name}}</a></b> {{doctor.address}} - {{doctor.city}}</p>
                            </div>
                        </div>
                        <div class="text-right">
                            <a :href="'http://localhost:8081/scheduling?doctor='+doctor.id" class="btn btn-outline-primary btn-sm">Request an appointment</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>


export default {
    name: "searchDoctors",
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
    created: function() {
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