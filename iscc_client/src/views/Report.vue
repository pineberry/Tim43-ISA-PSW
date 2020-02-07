<template>
    <div class="container">
        <div class="row">
            <div class="col-6">
                <h2>Examination Report</h2>
                <form v-on:submit.prevent="submitReport">
                    <div class="form-group">
                        <label for="inputNotes">Notes</label>
                        <textarea class="form-control" id="inputNotes" rows="4" v-model="notes"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="selectDiagnosis">Diagnosis</label>
                        <select class="form-control" v-model="diagnosis" id="selectDiagnosis">
                            <option v-for="diagnosis in diagnoses" :key="diagnosis.id" :value="diagnosis.code">{{diagnosis.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="selectMedicines">Medicines</label>
                        <select class="custom-select" v-model="medications" id="selectMedicines" multiple>
                            <option v-for="medicine in medicines" :key="medicine.id" :value="medicine.code">{{medicine.name}}</option>
                        </select>
                    </div>
                    <button class="btn btn-primary" type="submit">Submit</button>
                </form>
            </div>
            <div class="col-6" v-if="medicalRecord">
                <h2>Medical Record</h2>
                <EditMeidcalRecord :medicalRecord="medicalRecord" />
            </div>
        </div>
        
    </div>
</template>

<script>
    import EditMeidcalRecord from "../views/EditMeidcalRecord";
    export default {
        components : { EditMeidcalRecord },
        name: "Report",
        data: function () {
            return{
                notes : undefined,
                diagnosis : undefined,
                medications : [],
                diagnoses : undefined,
                medicines : undefined,
                medicalRecord: undefined,
                doctor: undefined,
            }
        },
        mounted : function () {
            this.axios.get("http://localhost:8080/codebook/diagnoses")
                .then(response => { this.diagnoses = response.data; })
                .catch(error => { console.log(error.response.data); })

            this.axios.get("http://localhost:8080/codebook/medicines")
                .then(response => { this.medicines = response.data; })
                .catch(error => { console.log(error); });
           this.axios.get("http://localhost:8080/record/patient/" + this.$route.query.id)
                .then(response => {this.medicalRecord = response.data;})
                .catch(error => {console.log(error);})
            this.axios.get("http://localhost:8080/doctor/" + localStorage.getItem("user_id"))
                .then(response => {
                    this.doctor = response.data;
                })
                .catch(error => {console.log(error);})

        },
        methods : {
            submitReport : function () {
                var report = {
                    "notes" : this.notes,
                    "diagnosis" : this.diagnosis,
                    "doctor" : this.doctor,
                    "medicines" : this.medications,
                    "medicalRecord" : this.medicalRecord
                }

                this.axios.post("http://localhost:8080/doctor/report", report)
                    .then(response => { 
                        this.axios.put("http://localhost:8080/medical/procedure/finish/exam/" + this.$route.query.proc)
                            .then( response => { this.$router.push("/"); } )
                            .catch( error => { conosole.log(error);} )
                     })
                    .catch(error => { alert(error.response.data); })
            }
        }
    }

</script>
