<template>
    <div class="container">
        <h4>Edit Clinic</h4>
        <form v-if="clinic != null" @submit.prevent="editClinic" accept-charset="UTF-8" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputName">Name:</label>
                        <input id="inputName" type="text" class="form-control" placeholder="Enter type name" v-model="clinicName">
                    </div>
                    <div class="form-group">
                        <label for="inputDescription">Description:</label>
                        <textarea id="inputDescription" rows="3" class="form-control" placeholder="Enter type description" v-model="clinicDescription"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="inputAddress">Address:</label>
                        <input id="inputAddress" type="text" class="form-control" placeholder="Enter type name" v-model="clinicAddress">
                    </div>
                    <div v-if="types" class="form-group">
                        <label for="selectType">Types</label>
                        <select class="custom-select" v-model="clinicTypes" id="selectType" multiple>
                            <option v-for="t in types" :key="t.id" :value="t">{{t.typeName}}</option>
                        </select>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
</template>

<script>
    export default {
        name: "EditClinic",
        data: function() {
            return {
                clinic: null,
                clinicName: undefined,
                clinicDescription: undefined,
                clinicAddress: undefined,
                clinicTypes: [],
                types: null
            }
        },
        mounted: function (){
            this.axios.get("http://localhost:8080/clinic/" + this.$route.query.name)
                .then(response => {
                    this.clinic = response.data;
                    this.clinicName = this.clinic.name;
                    this.clinicDescription = this.clinic.description;
                    this.clinicAddress = this.clinic.address;
                    this.clinicTypes = this.clinic.types
                })
            this.axios.get("http://localhost:8080/procedure/type/all")
                .then(response => {
                    this.types = response.data;
                })
        },
        methods: {
            editClinic: function() {

                this.clinic.name = this.clinicName;
                this.clinic.description = this.clinicDescription;
                this.clinic.address = this.clinicAddress;
                this.clinic.types = this.clinicTypes;

                if (this.clinicName != undefined)
                    this.clinicName.trim();
                else
                    this.clinicName = '';

                if (this.clinicDescription != undefined)
                    this.clinicDescription.trim();
                else
                    this.clinicDescription = '';

                if (this.clinicAddress != undefined)
                    this.clinicAddress.trim();
                else
                    this.clinicAddress = '';

                if (this.clinicName === '' || this.clinicAddress === '' || this.clinicDescription === '') {
                    alert('All fields should be filled!');
                    return;
                }

                this.axios.put("http://localhost:8080/clinic/edit", this.clinic)
                    .then(response => {
                        this.$router.go(-1);
                    })
            }
        }
    }
</script>

<style scoped>

</style>