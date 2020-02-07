<template>
    <div class="container">
        <h4>Patients</h4>
        <form @submit.prevent="searchPatients" accept-charset="UTF-8" method="get">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="selectSearchBy">Seach by</label>
                        <select id="selectSearchBy" class="form-control" v-model="searchBy">
                            <option value="name">Name and Surname</option>
                            <option value="number">Health care number</option>
                        </select>
                    </div>
                    <div v-if="searchBy === 'name'" class="form-group">
                        <div class="form-group">
                            <label for="inputName">Name:</label>
                            <input id="inputName" type="text" class="form-control" placeholder="Enter name" v-model="patientName">
                        </div>
                        <div class="form-group">
                            <label for="inputLastName">Last name:</label>
                            <input id="inputLastName" type="text" class="form-control" placeholder="Enter last name" v-model="patientLastName">
                        </div>
                    </div>
                    <div v-else class="form-group">
                        <div class="form-group">
                            <label for="inputNumber">Health care number:</label>
                            <input id="inputNumber" type="text" class="form-control" v-model="patientHealthCareNumber">
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <span>Filter patients</span>
                    <p>
                        <input id="filterName" type="text" class="form-control" placeholder="Filter by name" v-model="filterName">
                    </p>
                    <p>
                        <input id="filterLastName" type="text" class="form-control" placeholder="Filter by name" v-model="filterLastName">
                    </p>
                    <p>
                        <input id="filterNumber" type="text" class="form-control" placeholder="Filter by number" v-model="filterNumber">
                    </p>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <div v-if="patients" class="container marginTop">
            <table class="table">
                <thead class="thead-dark">
                <th scope="col">#</th>
                <th v-on:click="firstNameSort()" scope="col">Name</th>
                <th v-on:click="lastNameSort()" scope="col">Last name</th>
                <th v-on:click="healthCareNumberSort()" scope="col">Health care number</th>
                <th scope="col">Profile</th>
                </thead>
                <tbody>
                    <tr v-for="patient in patients" :key="patient.id">
                        <td>{{patients.indexOf(patient) + 1}}</td>
                        <td>{{patient.firstName}}</td>
                        <td>{{patient.lastName}}</td>
                        <td>{{patient.healthCareNumber}}</td>
                        <td><router-link class="btn btn-primary" :to="{path: '/patientData', query: {id: patient.id}}">
                            Preview
                        </router-link></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SearchPatients",
        data: function() {
            return {
                searchBy: 'name',
                patientName: undefined,
                patientLastName: undefined,
                patientHealthCareNumber: undefined,
                patients: null,
                unfilteredPatients: null,
                filterName: undefined,
                filterLastName: undefined,
                filterNumber: undefined
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/patient/patients")
                .then(response => {
                    this.patients = response.data;
                    this.unfilteredPatients = this.patients.slice();
                })
        },
        methods: {
            firstNameSort: function() {
                this.patients.sort((a, b) => (a.firstName > b.firstName) ? 1 : -1);
            },

            lastNameSort: function() {
                this.patients.sort((a, b) => (a.lastName > b.lastName) ? 1 : -1);
            },

            healthCareNumberSort: function() {
                this.patients.sort((a, b) => (a.healthCareNumber > b.healthCareNumber) ? 1 : -1);
            },

            searchPatients: function() {
                if (this.searchBy === 'name') {
                    let name = 'empty';
                    let lastName = 'empty';

                    if (this.patientName != undefined && this.patientName.trim() != '') {
                       name = this.patientName.trim();
                    }

                    if (this.patientLastName != undefined && this.patientLastName.trim() != '') {
                        lastName = this.patientLastName.trim();
                    }
                    if (name != 'empty' || lastName != 'empty') {
                        this.axios.get("http://localhost:8080/patient/name/" + name + "/" + lastName)
                            .then(response => {
                                this.patients = response.data;
                                this.unfilteredPatients = this.patients.slice();
                            })
                    } else {
                        alert('At least one search parameter has to be entered!');
                    }
                } else {
                    if (this.patientHealthCareNumber != undefined && this.patientHealthCareNumber.trim() != '') {
                        this.axios.get("http://localhost:8080/patient/number/" + this.patientHealthCareNumber.trim())
                            .then(response => {
                                this.patients = response.data;
                                this.unfilteredPatients = this.patients.slice();
                            })
                    }
                }
            }
        },
        watch: {
            filterName: function() {
                    let name = this.filterName.trim().toLowerCase();

                    if ((this.filterLastName === undefined || this.filterLastName.trim() === '') &&
                                (this.filterNumber === undefined || this.filterNumber.trim() === '')) {
                        this.patients = this.unfilteredPatients.filter((patient) =>
                                                                        patient.firstName.toLowerCase().includes(name));
                    } else {
                        var firstFilterFlag = false;

                        if (this.filterLastName != undefined && this.filterLastName.trim() != '') {
                            let lastName = this.filterLastName.trim().toLowerCase();
                            this.patients = this.unfilteredPatients.filter((patient) =>
                                                                patient.lastName.toLowerCase().includes(lastName));
                            firstFilterFlag = true;
                        }

                        if (this.filterNumber != undefined && this.filterNumber.trim() != '') {
                            let hcNumber = this.filterNumber.trim().toLowerCase();

                            if (firstFilterFlag) {
                                this.patients = this.patients.filter((patient) =>
                                    patient.healthCareNumber.toLowerCase().includes(hcNumber));
                            } else {
                                this.patients = this.unfilteredPatients.filter((patient) =>
                                    patient.healthCareNumber.toLowerCase().includes(hcNumber));
                            }
                        }

                        this.patients = this.patients.filter((patient) =>
                                                                        patient.firstName.toLowerCase().includes(name));
                    }
            },

            filterLastName: function() {
                let lastName = this.filterLastName.trim().toLowerCase();

                if ((this.filterName === undefined || this.filterName.trim() === '') &&
                    (this.filterNumber === undefined || this.filterNumber.trim() === '')) {
                    this.patients = this.unfilteredPatients.filter((patient) =>
                        patient.lastName.toLowerCase().includes(lastName));
                } else {
                    let firstFilterFlag = false;

                    if (this.filterName != undefined && this.filterName.trim() != '') {
                        let name = this.filterName.trim().toLowerCase();
                        this.patients = this.unfilteredPatients.filter((patient) =>
                            patient.firstName.toLowerCase().includes(name));
                        firstFilterFlag = true;
                    }

                    if (this.filterNumber != undefined && this.filterNumber.trim() != '') {
                        let hcNumber = this.filterNumber.trim().toLowerCase();

                        if (firstFilterFlag) {
                            this.patients = this.patients.filter((patient) =>
                                patient.healthCareNumber.toLowerCase().includes(hcNumber));
                        } else {
                            this.patients = this.unfilteredPatients.filter((patient) =>
                                patient.healthCareNumber.toLowerCase().includes(hcNumber));
                        }
                    }

                    this.patients = this.patients.filter((patient) =>
                        patient.lastName.toLowerCase().includes(lastName));
                }
            },

            filterNumber: function () {
                let hcNumber = this.filterNumber.trim().toLowerCase();

                if ((this.filterLastName === undefined || this.filterLastName.trim() === '') &&
                    (this.filterName === undefined || this.filterName.trim() === '')) {
                    this.patients = this.unfilteredPatients.filter((patient) =>
                        patient.healthCareNumber.toLowerCase().includes(hcNumber));
                } else {
                    var firstFilterFlag = false;

                    if (this.filterLastName != undefined && this.filterLastName.trim() != '') {
                        let lastName = this.filterLastName.trim().toLowerCase();
                        this.patients = this.unfilteredPatients.filter((patient) =>
                            patient.lastName.toLowerCase().includes(lastName));
                        firstFilterFlag = true;
                    }

                    if (this.filterName != undefined && this.filterName.trim() != '') {
                        let name = this.filterName.trim().toLowerCase();

                        if (firstFilterFlag) {
                            this.patients = this.patients.filter((patient) =>
                                patient.firstName.toLowerCase().includes(name));
                        } else {
                            this.patients = this.unfilteredPatients.filter((patient) =>
                                patient.firstName.toLowerCase().includes(name));
                        }
                    }

                    this.patients = this.patients.filter((patient) =>
                        patient.healthCareNumber.toLowerCase().includes(hcNumber));
                }
            }
        }
    }
</script>

<style scoped>
    .marginTop {
        margin-top: 10px;
    }
</style>