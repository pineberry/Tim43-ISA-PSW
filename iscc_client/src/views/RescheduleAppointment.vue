<template>
    <div class="container">
        <h4>New appoinment</h4>
        <div class="row">
            <div class="col-6">
                <div class="form-group">
                    <label for="selectAppointment">Appointment for</label>
                    <select id="selectAppointment" class="form-control" v-model="appointment">
                        <option value="exam">Examination</option>
                        <option value="surgery">Surgery</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectType">Procedure type</label>
                    <select id="selectType" class="form-control" v-model="procedureType">
                        <option v-for="pType in procedureTypes" :key="pType.id" :value="pType">{{pType.typeName}}</option>
                    </select>
                </div>
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
                <button v-on:click="makeAppointment()" class="btn btn-primary">Schedule</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "RescheduleAppointment",
        data: function() {
            return {
                dateOfProcedure: undefined,
                startTime: undefined,
                endTime: undefined,
                appointment: 'exam',
                procedureType: null,
                procedureTypes: null,
                doctor: null,
                patient: null
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/procedure/type/all")
                .then(response => {
                    this.procedureTypes = response.data
                })
                .catch(error => {
                    alert(error.response.data)
                })

            this.axios.get("http://localhost:8080/doctor/" + localStorage.getItem('user_id'))
                .then(response => {this.doctor = response.data;})

             this.axios.get("http://localhost:8080/patient/" + this.$route.params.id)
                .then(response => {this.patient = response.data})
        },
        methods: {
            makeAppointment: function() {
                var medicalProcedure = {
                    "procedureType": this.procedureType,
                    "dateOfProcedure": this.dateOfProcedure,
                    "doctor": this.doctor,
                    "patient": this.patient,
                    "price" : 0,
                    "startTime": this.startTime,
                    "endTime": this.endTime
                }

                var valid = true;

                if(this.procedureType === null || this.dateOfProcedure === undefined || this.doctor === null
                    || this.startTime === undefined || this.endTime === undefined){
                    valid = false;
                }

                if (valid) {
                    if (this.appointment === 'exam') {
                        this.axios.post("http://localhost:8080/medical/procedure/exam", medicalProcedure)
                            .then(response => {alert('Request has been sent.')})
                    } else {
                        this.axios.post("http://localhost:8080/medical/procedure/surgery", medicalProcedure)
                            .then(response => {alert('Request has been sent.')})
                    }
                }

            }
        }
    }
</script>

<style scoped>

</style>