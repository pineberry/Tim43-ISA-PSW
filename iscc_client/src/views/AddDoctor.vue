<template>
	<div class="container">
		<h4>New Medic</h4>
        <form @submit.prevent="addDoctor" accept-charset="UTF-8" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
						<label for="inputName">Name:</label>
						<input id="inputName" type="text" class="form-control" placeholder="Enter name" v-model="firstName">
					</div>
                    <div class="form-group">
						<label for="inputSurname">Surname:</label>
						<input id="inputSurname" type="text" class="form-control" placeholder="Enter surname" v-model="lastName">
					</div>
                    <div class="form-group">
						<label for="inputAddress">Address:</label>
						<input id="inputAddress" type="text" class="form-control" placeholder="Enter address" v-model="address">
					</div>
                    <div class="form-group">
						<label for="inputCity">City:</label>
						<input id="inputCity" type="text" class="form-control" placeholder="Enter city" v-model="city">
					</div>
                    <div class="form-group">
						<label for="inputState">State:</label>
						<input id="inputState" type="text" class="form-control" placeholder="Enter state" v-model="state">
					</div>
                </div>
                <div class="col-6">
                    <div class="form-group">
						<label for="inputEmail">E-mail:</label>
						<input id="inputEmail" type="email" class="form-control" placeholder="Enter e-mail" v-model="email">
					</div>
                    <div class="form-group">
						<label for="inputPhone">Phone number:</label>
						<input id="inputPhone" type="text" class="form-control" placeholder="Enter phone number" v-model="phoneNumber">
					</div>
					<div class="form-group">
						<label for="selectType">Procedure type</label>
						<select id="selectType" class="form-control" v-model="specialized">
							<option v-for="pType in procedureTypes" :key="pType.id" :value="pType">{{pType.typeName}}</option>
						</select>
					</div>
                    <div class="form-group">
						<label for="inputStart">Workingtime Start:</label>
						<input id="inputStart" type="time" class="form-control" v-model="workingtimeStart">
					</div>
                    <div class="form-group">
						<label for="inputEnd">Workingtime End:</label>
						<input id="inputEnd" type="time" class="form-control" v-model="workingtimeEnd">
					</div>
                </div>
            </div>
                <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</template>

<script>
    import axios from "axios"

    export default {
        name: "AddDoctor",
        data: function() {
            return {
                firstName: undefined,
                lastName: undefined,
                email: undefined,
                phoneNumber: undefined,
                address: undefined,
                city: undefined,
                state: undefined,
                workingtimeStart: undefined,
                workingtimeEnd: undefined,
								clinic: null,
								specialized: null,
								admin: Object,
								procedureTypes: null
            }
        },
		mounted: function(){
			this.axios.get("http://localhost:8080/procedure/type/all")
					.then(response => {this.procedureTypes = response.data})
					.catch(error => {alert(error.response.data)})
			if(localStorage.getItem("typeOfUser") == "clinicAdministrator") {
				this.axios.get("http://localhost:8080/clinic/admin/" + localStorage.getItem("user_id"))
					.then(response => { 
						this.admin = response.data; 
						this.clinic = this.admin.clinic;
					})
					.catch(error => {console.log(error);})
			}
		},
        computed: {
            valName: function(){
				if(this.firstName != undefined && this.firstName.length > 0){
					let tempName = this.firstName.match('[A-Za-z ]*');
		            if(tempName != this.firstName)
						return 'Name must contain alphabates only!';
		            else if(this.firstName[0].match('[A-Z]') === null)
						return 'First letter must be capital!'; 
					else return null;
				}
		        else if(this.firstName === '') return 'This field is required!';
		        else return null;
			},
            valSurname: function(){
				if(this.lastName != undefined && this.lastName.length > 0){
					let tempSurname = this.lastName.match('[A-Za-z ]*');
		            if(tempSurname != this.lastName)
						return 'Surname must contain alphabates only!';
		            else if(this.lastName[0].match('[A-Z]') === null)
						return 'First letter must be capital!';
					else return null;
				} 
		        else if(this.lastName === '') return 'This field is required!';
		        else return null;
			},
            valCity: function(){
				if(this.city != undefined && this.city.length > 0){
					let tempCity = this.city.match('[A-Za-z ]*');
		            if(tempCity != this.city) 
						return 'City must contain alphabates only!';
		            else if(this.city[0].match('[A-Z]') === null) 
						return 'First letter must be capital!';
					else return null;
				} 
		        else if(this.city === '') return 'This field is required!';
		        else return null;
			},
            valState: function(){
				if(this.state != undefined && this.state.length > 0){
					let tempState = this.state.match('[A-Za-z ]*');
		            if(tempState != this.state) 
						return 'State must contain alphabates only!';
		            else if(this.state[0].match('[A-Z]') === null) 
						return 'First letter must be capital!';
					else return null;
				}
		        else if(this.state === '') return 'This field is required!';
		        else return null;
			},
            valAddress: function(){
				if(this.address === '')
	                return 'This field is required!!';
	            else 
	                return null;
			},
            valEmail: function(){
				if(this.email === '') 
					return 'This field is required!';
	            else
					 return null;
			},
            valPhone: function(){
				if(this.phoneNumber === '')
	                return 'This field is required!';
	            else 
	                return null;
			}
            
        },

        methods: {
					addDoctor: function(){
						var doctor = {
								"email": this.email,
								"firstName": this.firstName,
								"lastName": this.lastName,
								"address": this.address,
								"city": this.city,
								"state": this.state,
								"phoneNumber": this.phoneNumber,
								"workingtimeStart": this.workingtimeStart,
								"workingtimeEnd": this.workingtimeEnd,
								"specialized": this.specialized,
								"clinic": this.clinic
						}

				var valid = true;
				var tempName = '';
				var tempSurname = '';
				var tempCity = '';
				var tempState = '';
				if (this.firstName != undefined){
					this.firstName.trim();
					tempName = this.firstName.match('[A-Za-z]*');
				}else {
					this.firstName = '';
				}
				if (this.lastName != undefined){
					this.lastName.trim();
					tempSurname = this.lastName.match('[A-Za-z]*');
				}else {
					this.lastName = '';
				}
				if (this.city != undefined){
					this.city.trim();
					tempCity = this.city.match('[A-Za-z]*');
				}else {
					this.city = '';
				}
				if (this.state != undefined){
					this.city.trim();
					tempState = this.state.match('[A-Za-z]*');
				}else {
					this.state = '';
				}
				if (this.address != undefined)
					this.address.trim();
				else
					this.address = '';
				if (this.email != undefined)
					this.email.trim();
				else
					this.email = '';
				if (this.phoneNumber != undefined)
					this.phoneNumber.trim();
				else
					this.phoneNumber = '';
				if (this.workingtimeStart != undefined)
					this.workingtimeStart.trim();
				else
					this.workingtimeStart = '';
				if (this.workingtimeEnd != undefined)
					this.workingtimeEnd.trim();
				else
					this.workingtimeEnd = '';

				if (this.firstName === undefined || this.firstName === '' || this.lastName === undefined || this.lastName === ''
						|| this.city === undefined || this.city === '' || this.state === undefined || this.state === ''
						|| this.address === undefined || this.address === '' || this.email === undefined || this.email === ''
						|| this.phoneNumber === undefined || this.phoneNumber === '' || this.workingtimeStart === undefined
						|| this.workingtimeStart === '' || this.workingtimeEnd === undefined || this.workingtimeEnd === ''){

					valid = false;
				}
				else if ((tempName != this.firstName) || (tempSurname != this.lastName) || (tempCity != this.city) || (tempCity != this.city)
						|| (tempState != this.state) || (this.firstName[0].match('[A-Z]') === null) || (this.lastName[0].match('[A-Z]') === null)
						|| (this.city[0].match('[A-Z]') === null) || (this.state[0].match('[A-Z]') === null)){
					valid = false;
				}
				else {
					valid = true;
				}

				if(!this.checkWorkingTime()){
					valid = false
				}

				if (valid){
					this.axios.post("http://localhost:8080/doctor/add", doctor)
						.then(response => {
							alert(response.data.firstName);
						})
						.catch(errorr => {
							alert('Error');
						})
				}
			},
			checkWorkingTime: function(){
            	var retVal = true;
				var startTimeParts = this.workingtimeStart.split(":");
				var endTimeParts = this.workingtimeEnd.split(":");
				var startHour = parseInt(startTimeParts[0]);
				var startMinute = parseInt(startTimeParts[1]);
				var endHour = parseInt(endTimeParts[0]);
				var endMinute = parseInt(endTimeParts[1]);

				if (startHour > endHour) {
					retVal = false
				} else if ((startHour == endHour) && (startMinute >= endMinute)){
					retVal = false
				}

				return retVal;
			}
        }
    }

</script>

<style scoped>

</style>