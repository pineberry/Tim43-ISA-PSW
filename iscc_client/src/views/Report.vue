<template>
    <div class="container">
        <div class="row">
            <form v-on:submit.prevent="submitReport" class="col-6">
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
    </div>
</template>

<script>
    export default {
        name: "Report",
        data: function () {
            return{
                notes : undefined,
                diagnosis : undefined,
                medications : [],
                diagnoses : undefined,
                medicines : undefined
            }
        },
        mounted : function () {
            this.axios.get("http://localhost:8080/codebook/diagnoses")
                .then(response => { this.diagnoses = response.data; })
                .catch(error => { alert(error.response.data); })

            this.axios.get("http://localhost:8080/codebook/medicines")
                .then(response => { this.medicines = response.data; })
                .catch(error => { alert(error.response.data); });
        },
        methods : {
            submitReport : function () {
                var report = {
                    "notes" : this.notes,
                    "diagnosis" : this.diagnosis,
                    "doctor" : 1,
                    "medicines" : this.medications
                }

                this.axios.post("http://localhost:8080/doctor/report", report)
                    .then(response => { alert(response.data.notes); })
                    .catch(error => { alert(error.response.data); })
            }
        }
    }
</script>
