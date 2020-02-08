<template>
    <div class="container">
        <form v-on:submit.prevent="editData">
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
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</template>
<script>
    export default {
        name : "EditMedicalRecord",
        props: {
            medicalRecord : Object
        },
        data : function () {
            return {
                height : null,
                weight : null,
                dateOfBirth : null,
                bloodType : undefined

            }
        },
        mounted : function () {
            this.height = this.medicalRecord.height;
            this.weight = this.medicalRecord.weight;
            this.bloodType = this.medicalRecord.bloodType;
            this.dateOfBirth = formatDate(this.medicalRecord.dateOfBirth);
        },
        methods : {
            editData : function () {
                var record = {
                    "id" : this.medicalRecord.id,
                    "height" : this.height,
                    "weight" : this.weight,
                    "dateOfBirth" : new Date(this.dateOfBirth),
                    "bloodType" : this.bloodType
                }

                this.axios.put("http://localhost:8080/record/edit", record)
                    .then(response => {
                        this.$router.go();
                    })
                    .catch(error => {console.log(error);})
            }
        }

    }

    
    function formatDate(dat) {
        if (dat != null) {
            let date = new Date(dat);
            var options = { year: "numeric", month: "2-digit", day: "2-digit" };
            let string = date.toLocaleDateString("sv-SE", options);
            return string;
        }
        return "";
    }
</script>