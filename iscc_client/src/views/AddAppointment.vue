<template>
    <div class="container">
        <h4>New procedure</h4>
        <form @submit.prevent="addMedicalProcedure" accept-charset="UTF-8" method = "post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="selectType">Procedure type</label>
                        <select id="selectType" class="form-control" v-model="procedureType">
                            <option v-for="pType in procedureTypes" :key="pType.id" :value="pType">{{pType.typeName}}</option>
                        </select>
                     </div>
                     <div class="form-group">
                        <label for="selectRoom">Medical room</label>
                        <select id="selectRoom" class="form-control" v-model="medicalRoom">
                            <option v-for="mRoom in medicalRooms" :key="mRoom.id" :value="mRoom">{{mRoom.roomName}}</option>
                        </select>
                     </div>
                     <div class="form-group">
                        <label for="selectDoctor">Doctor</label>
                        <select id="selectDoctor" class="form-control" v-model="doctor">
                            <option v-for="doc in doctors" :key="doc.id" :value="doc">{{doc.firstName}}</option>
                        </select>
                     </div>
                     <div class="form-group">
                        <label for="inputPrice">Price:</label>
                        <input id="inputPrice" type="number" class="form-control" v-model="price">
                    </div>
                    <div class="form-group">
                        <label for="inputDate">Date:</label>
                        <input id="inputDate" type="date" class="form-control" v-model="dateOfProcedure">
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
        name: "AddApointment",
        data: function(){
            return {
                dateOfProcedure: undefined,
                procedureType: undefined,
                medicalRoom: undefined,
                doctor: undefined,
                price: undefined,
                procedureTypes: [],
                medicalRooms: [],
                doctors: []
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/procedure/type/all")
                .then(response => {this.procedureTypes = response.data})
                .catch(error => {alert(error.response.data)})
            
            this.axios.get("http://localhost:8080/doctor/all")
                .then(response => {this.doctors = response.data})
                .catch(error => {alert(error.response.data)})
            
            this.axios.get("http://localhost:8080/medical/room/all")
                .then(response => {this.medicalRooms = response.data})
                .catch(error => {alert(error.response.data)})
        },
        methods: {
            addMedicalProcedure: function(){
                var medicalProcedure = {
                    "procedureType": this.procedureType,
                    "dateOfProcedure": this.dateOfProcedure,
                    "medicalRoom": this.medicalRoom,
                    "doctor": this.doctor,
                    "price": this.price
                }

                var valid = true;

                if(this.procedureType === undefined || this.dateOfProcedure === undefined 
                        || this.medicalRoom === undefined || this.doctor === undefined
                        || this.price === undefined){
                    valid = false;
                }

                if(valid){
                    this.axios.post("http://localhost:8080/medical/procedure/add", medicalProcedure)
                        .then(response => {
							alert("Medical procedure has been added")
						})
						.catch(errorr => {
							alert(error.response.data)
						})
                }
            }
        }
    }
</script>

<style scoped>
</style>