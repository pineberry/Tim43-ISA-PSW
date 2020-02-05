<template>
	<div class="container">
		<div class="col-12 mt-3">
			<label>Enter info to filter:</label>
            <form>
                <div class="row justify-content-center">
                    <input type="text" class="form-control col m-1" placeholder="Clinic name" id="name" v-model="name">
                    <input type="text" class="form-control col m-1" placeholder="Address" id="address" v-model="address">
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
		<div class="row m-0">
			<div class="card m-1 w-05" v-for="clinic in filteredClinics" :key="clinic.id">
				<router-link class="clinic-card" :to="'clinic_'+clinic.id">
					<div class="card-body">
						<h3 class="card-title m-0">{{clinic.name}}</h3>
						<hr class="my-1">
						<small class="card-subtitle m-0 text-muted">Address: {{clinic.address}}</small>
						<p>Average rating: <span><b>{{clinic.averageRating}}</b></span></p>
						<hr class="my-1">
						<footer class="blockquote-footer"><small>{{clinic.description}}</small></footer>
					</div>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "clinics",
	data : function () {
		return {
			clinics : [],
			name : undefined,
			address : undefined,
			rating : 0,
			allRatingsChecked : false
		}
	}, 
	mounted : function() {
		this.axios.get("http://localhost:8080/clinic/clinics")
		.then(response => {
			this.clinics = response.data
		})
	}, 
	methods : {

	},
    computed: {
        filteredClinics (){
            if (!this.allRatingsChecked)
            {
                if (this.rating) {
                    return this.filterByAddress.filter((clinic) => {
                        return clinic.averageRating>=this.rating;
                    })
                } else {
                    return this.filterByAddress;
                }
            } else {
                return this.filterByAddress;
            }
        },
        filterByName () {
            if (this.name) {
                return this.clinics.filter((clinic) => {
                    return clinic.name.toLowerCase().startsWith(this.name.toLowerCase());
                })
            } else {
                return this.clinics;
            }
        },
        filterByAddress () {
            if (this.address) {
                return this.filterByName.filter((clinic) => {
                    return clinic.address.toLowerCase().startsWith(this.address.toLowerCase());
                })
            } else {
                return this.filterByName;
            }
        }
    }

}
</script>

<style scoped>
/* .clinic-card p{
	color: #343a40 !important;
}
.clinic-card h3{
	color: #007BFF !important;
}
.clinic-card:hover{
	background-color: #cce1f7;
	
} */
.clinic-card:hover h3, .clinic-card:hover p{
	color: #007BFF !important;
}
.card:hover {
	border-color: #007BFF;
}
</style>