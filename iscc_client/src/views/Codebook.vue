<template>
    <div class="container">
        <div class="row">
            <form class="col-6" v-on:submit.prevent="medicineSubmit" accept-charset="UTF-8">
                <div class="form-group">
                    <label for="inputMedicineName">Medicine name</label>
                    <input type="text" id="inputMedicineName" class="form-control" placeholder="Enter medicine name" v-model="medicineName">
                </div>
                <div class="form-group">
                    <label for="inputMedicineCode">Medicine code</label>
                    <input type="text" id="inputMedicineCode" class="form-control" placeholder="Enter medicine code" v-model="medicineCode">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <form class="col-6" v-on:submit.prevent="diagnosisSubmit" accept-charset="UTF-8">
                <div class="form-group">
                    <label for="inputDiagnosisName">Diagnosis name</label>
                    <input type="text" id="inputDiagnosisName" class="form-control" placeholder="Enter diagnosis name" v-model="diagnosisName">
                </div>
                <div class="form-group">
                    <label for="inputDiagnosisLName">Diagnosis latin name</label>
                    <input type="text" id="inputDiagnosisLName" class="form-control" placeholder="Enter diagnosis latin name" v-model="diagnosisLName">
                </div>
                <div class="form-group">
                    <label for="inputDiagnosisCode">Diagnosis code</label>
                    <input type="text" id="inputDiagnosisCode" class="form-control" placeholder="Enter diagnosis code" v-model="diagnosisCode">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name : "Codebook",
        data : function () {
            return{
                medicineName : undefined,
                medicineCode : undefined,
                diagnosisName : undefined,
                diagnosisLName : undefined,
                diagnosisCode : undefined
            }
        },
        methods : {
            medicineSubmit : function () {
                var medicine = {
                    "name" : this.medicineName,
                    "code" : this.medicineCode
                }

                this.axios.post("http://localhost:8080/codebook/medicine", medicine)
                    .then(response => {
                        alert(response.data.name + ' lijek uspjesno dodat u sifarnik')
                    })
                    .catch(error => {
                        alert(error.response.data)
                    })
            },
            diagnosisSubmit : function () {
                var diagnosis = {
                    "name" : this.diagnosisName,
                    "latinName" : this.diagnosisLName,
                    "code" : this.diagnosisCode
                }

                this.axios.post("http://localhost:8080/codebook/diagnosis", diagnosis)
                    .then(response => {
                        alert(response.data.name + ' dijagnoza uspjesno dodata u sifarnik')
                    })
                    .catch(error => {
                        alert(error.response.data)
                    })
            }
        }
    }
</script>