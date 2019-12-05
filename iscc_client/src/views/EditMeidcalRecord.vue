<template>
    <div class="container">
        <form class="col-6" v-on:submit.prevent="editData">
            <div class="form-group">
                <label for="inputHeight">Height</label>
                <input type="number" v-model="height" class="form-control" id="inputHeight">
            </div>
            <div class="form-group">
                <label for="inputWeight">Weight</label>
                <input type="number" v-model="weight" class="form-control" id="inputWeight">
            </div>
            <div class="form-group">
                <label for="inputBloodType">Blood Type</label>
                <select class="form-control" v-model="bloodType" id="inputBloodType">
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                </select>
            </div>
            <div class="form-group">
                <label for="inpuDateOfBirth">Birth day</label>
                <input type="date" v-model="dateOfBirth" id="inpuDateOfBirth" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary btn-sm">Submit</button>
        </form>
    </div>
</template>
<script>
    export default {
        name : "EditMedicalRecord",
        data : function () {
            return {
                height : null,
                weight : null,
                dateOfBirth : null,
                bloodType : undefined

            }
        },
        mounted : function () {
            this.axios.get("http://localhost:8080/record/" + this.$route.params.id)
                .then(response => {
                    this.height = response.data.height;
                    this.weight = response.data.weight;
                    this.bloodType = response.data.bloodType;
                    this.dateOfBirth = new Date(response.data.dateOfBirth);

                })
                .catch(error => {
                    alert(error.respoonse.data);
                })
        },
        methods : {
            editData : function () {
                var record = {
                    "id" : this.$route.params.id,
                    "height" : this.height,
                    "weight" : this.weight,
                    "dateOfBirth" : new Date(this.dateOfBirth),
                    "bloodType" : this.bloodType
                }

                this.axios.put("http://localhost:8080/record/edit", record)
                    .then(response => {
                        alert(response.data.patientsEmail);
                    })
                    .catch(error => {alert(error.resopnse)})
            }
        }

    }
</script>