<template>
	<div class="container">
		<h4>New Medic</h4>
        <form @submit.prevent="addDoctor" accept-charset="UTF-8" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
						<label for="inputName">Name:</label>
						<input id="inputName" type="text" class="form-control" placeholder="Enter name" v-model="firstName">
						<span class="val">{{valName}}</span>
					</div>
                    <div class="form-group">
						<label for="inputSurname">Surname:</label>
						<input id="inputSurname" type="text" class="form-control" placeholder="Enter surname" v-model="lastName">
						<span class="val">{{valSurname}}</span>
					</div>
                    <div class="form-group">
						<label for="inputAddress">Address:</label>
						<input id="inputAddress" type="text" class="form-control" placeholder="Enter address" v-model="address">
					</div>
                    <div class="form-group">
						<label for="inputCity">City:</label>
						<input id="inputCity" type="text" class="form-control" placeholder="Enter city" v-model="city">
						<span class="val">{{valCity}}</span>
					</div>
                    <div class="form-group">
						<label for="inputState">State:</label>
						<input id="inputState" type="text" class="form-control" placeholder="Enter state" v-model="state">
						<span class="val">{{valState}}</span>
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
				if(this.firstName != undefined && this.firstName.length > 0){;
		            if(this.firstName[0].match('[A-Z]') === null)
						return 'First letter must be capital!'; 
					else return null;
				}
		        else return null;
			},
            valSurname: function(){
				if(this.lastName != undefined && this.lastName.length > 0){
					if(this.lastName[0].match('[A-Z]') === null)
						return 'First letter must be capital!';
					else return null;
				}
		        else return null;
			},
            valCity: function(){
				if(this.city != undefined && this.city.length > 0){
					if(this.city[0].match('[A-Z]') === null)
						return 'First letter must be capital!';
					else return null;
				}
		        else return null;
			},
            valState: function(){
				if(this.state != undefined && this.state.length > 0){
					if(this.state[0].match('[A-Z]') === null)
						return 'First letter must be capital!';
					else return null;
				}
		        else return null;
			}
            
        },

        methods: {
				addDoctor: function(){

					if(localStorage.getItem("typeOfUser") != "clinicAdministrator") {
                    	alert('Only clinic admin has permission for this action!');
                    	return;
                	}

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
					};

				if (this.firstName != undefined) {
					this.firstName.trim();
				} else {
					this.firstName = '';
				}
				if (this.lastName != undefined){
					this.lastName.trim();
				} else {
					this.lastName = '';
				}

				if (this.city != undefined) {
					this.city.trim();
				} else {
					this.city = '';
				}

				if (this.state != undefined) {
					this.city.trim();
				} else {
					this.state = '';
				}

				if (this.address != undefined) {
					this.address.trim();
				} else {
					this.address = '';
				}

				if (this.email != undefined) {
					this.email.trim();
				} else {
					this.email = '';
				}

				if (this.phoneNumber != undefined) {
					this.phoneNumber.trim();
				} else {
					this.phoneNumber = '';
				}

				if (this.workingtimeStart != undefined) {
					this.workingtimeStart.trim();
				} else {
					this.workingtimeStart = '';
				}

				if (this.workingtimeEnd != undefined) {
					this.workingtimeEnd.trim();
				} else {
					this.workingtimeEnd = '';
				}

				if (this.firstName === '' || this.lastName === ''  ||  this.city === '' || this.state === ''
						|| this.address === '' || this.email === '' || this.phoneNumber === ''
						|| this.workingtimeStart === '' || this.workingtimeEnd === ''){

					alert('All fields should be filled!');
					return;
				} else if (this.firstName[0].match('[A-Z]') === null || this.lastName[0].match('[A-Z]') === null
						|| this.city[0].match('[A-Z]') === null || this.state[0].match('[A-Z]') === null) {
					return;
				}

				if(!this.checkWorkingTime()){
					alert('Start of working time has to be before ending!');
					return;
				}

				this.axios.post("http://localhost:8080/doctor/add", doctor)
						.then(response => {
							this.firstName = undefined;
							this.lastName =  undefined;
							this.email = undefined;
							this.phoneNumber = undefined;
							this.address = undefined;
							this.city = undefined;
							this.state = undefined;
							this.workingtimeStart = undefined;
							this.workingtimeEnd = undefined;
							this.clinic = null;
							this.specialized = null;
							alert('Doctor ' + response.data.firstName + ' ' + response.data.lastName + ' has been added.');
						})
						.catch(erorr => {
							alert('There was a problem in adding doctor, check if all date are filled and are in correct format');
							alert('There was a problem in adding doctor, check if all date are filled and are in correct format');
						})

			},
			checkWorkingTime: function(){
            	var retVal = true;
				var startTimeParts = this.workingtimeStart.split(":");
				var endTimeParts = this.workingtimeEnd.split(":");
				var startHour = parseInt(startTimeParts[0]);
				var startMinute = parseInt(startTimeParts[1]);
				var endHour = parseInt(endTimeParts[0]);
				var endMinute = parseInt(endTimeParts[1]);

				if (startHour < 7 || endHour >= 23) {
					retVal = false;
				}
				else if (startHour > endHour) {
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
	.val {
		color: darkred;
	}
</style>