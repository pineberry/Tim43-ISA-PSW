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
                </div>
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputDate">Date:</label>
                        <input id="inputDate" type="date" class="form-control" v-model="dateOfProcedure">
                    </div>
                    <div class="form-group">
                        <label for="inputStart">Start time:</label>
                        <input id="inputStart" type="time" class="form-control" v-model="startTime">
                    </div>
                    <div class="form-group">
                        <label for="inputEnd">End time:</label>
                        <input id="inputEnd" type="time" class="form-control" v-model="endTime">
                    </div>
                </div>
                <p class="float-right"><button type="submit" class="btn btn-primary">Add</button></p>
            </div>
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
                startTime: undefined,
                endTime: undefined,
                procedureTypes: [],
                medicalRooms: [],
                doctors: []
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/procedure/type/search/clinic/" + localStorage.getItem("user_id"))
                .then(response => {this.procedureTypes = response.data})
                .catch(error => {alert(error.response.data)})
            
            this.axios.get("http://localhost:8080/doctor/clinic/" + localStorage.getItem("user_id"))
                .then(response => {this.doctors = response.data})
                .catch(error => {alert(error.response.data)})
            
            this.axios.get("http://localhost:8080/medical/room/clinic/" + localStorage.getItem("user_id"))
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
                    "startTime": this.startTime,
                    "endTime": this.endTime
                }

                if(this.procedureType === undefined || this.dateOfProcedure === undefined || this.dateOfProcedure === ''
                        || this.medicalRoom === undefined || this.doctor === undefined
                        || this.startTime === undefined || this.startTime === ''
                        || this.endTime === undefined || this.endTime === ''){
                    alert('All fields should be filled!');
                    return;
                }

                if (this.doctor.specialized.typeName != this.procedureType.typeName) {
                    alert('Chosen doctor is not specialized for chosen procedure type!');
                    return;
                }

                if (!this.checkTime(this.startTime, this.endTime)) {
                    alert('Times are in incorrect order or out of 7-23h boundaries!');
                    return;
                }

                let date = new Date();
                let requestedDate = new Date(this.dateOfProcedure)
                if (requestedDate < date) {
                    alert('Date cannot be in the past!');
                    return;
                }

                    this.axios.post("http://localhost:8080/medical/procedure/add", medicalProcedure)
                        .then(response => {
                            this.dateOfProcedure= undefined;
                            this.procedureType = undefined;
                            this.medicalRoom = undefined;
                            this.doctor = undefined;
                            this.startTime = undefined;
                            this.endTime = undefined;
							alert("Medical procedure has been added")
						})
						.catch(error => {
							alert(error.response.data)
						})
            },

            checkTime: function(tStart, tEnd) {
                if (tStart != undefined && tEnd != undefined) {
                    let start = tStart.split(':');
                    let end = tEnd.split(':');

                    let hStart = parseInt(start[0]);
                    let mStart = parseInt(start[1]);
                    let hEnd = parseInt(end[0]);
                    let mEnd = parseInt(end[1]);
                    if (hStart < 7 || hEnd >= 23) {
                        return false;
                    } else if (hEnd < hStart) {
                        return false;
                    } else if ((hEnd === hStart) && (mEnd <= mStart)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }
</script>

<style scoped>
</style>