<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col-4">
                <label>Enter data to search:</label>
                <form v-on:submit.prevent="search">
                    <div class="form-group row">
                        <label for="firstName" class="col-sm-4 col-form-label">Doctor's first name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="firstName" v-model="firstName">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="lastName" class="col-sm-4 col-form-label">Doctor's last name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="lastName" v-model="lastName">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="rating" class="col-sm-4 col-form-label">Rating</label>
                        <div class="col-sm-8">
                            <input type="number" min="0" max="5" step=".1" class="form-control" id="rating" v-model="rating">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="allRatingsChecked" v-model="allRatingsChecked">
                                <label class="form-check-label" for="allRatingsChecked">
                                    Consider all doctors regardless of rating
                                </label>
                            </div>
                        </div>
                        <div class="col-sm-8 ">
                            
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-8">
                
                <div class="card text-center m-1" v-for="doctor in filteredDoctors" :key="doctor.id">
                    <div class="card-body">
                        <h3 class="card-title">{{doctor.firstName}} {{doctor.lastName}}</h3>
                        <hr class="my-4">
                        <p class="card-text">Avarage rating: {{doctor.averageRating}}</p>
                        <a href="#" class="btn btn-dark">...</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "searchDoctors",
    data: function() {
        return {
            doctors : [],
            firstName : '',
            lastName : undefined,
            rating : 0,
            allRatingsChecked : false,
            response: undefined
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
    mounted: function() {
        this.axios.get("http://localhost:8080/doctor/all")
        .then(response => {
                this.response = response
                console.log(response)
                this.doctors = response.data
                console.log(this.doctors)
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

</style>