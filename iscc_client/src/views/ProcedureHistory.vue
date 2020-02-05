<template>
    <div class="container row justify-content-center m-0">
        <div v-if="appointments.length>0" class="col-10 card mt-5 ml-1 mr-1 p-5">
            <table class="table">
                <thead>
                    <tr>
                        <th v-on:click="dateSort()" class="pointer" scope="col">Date and time</th>
                        <th v-on:click="doctorSort()" class="pointer" scope="col">Performed by</th>
                        <th v-on:click="clinicSort()" class="pointer" scope="col">Done in clinic</th>
                        <th v-on:click="procedureSort()" class="pointer" scope="col">Type of procedure</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="appointment in appointments" :key="appointment.id">
                        <td>{{appointment.dateOfProcedure | formatDate}} at {{appointment.startTime}}h</td>
                        <td>
                            <b>
                                <router-link class="special-link" :to="'doctor_'+appointment.doctor.id">
                                    dr. {{appointment.doctor.firstName}} {{appointment.doctor.lastName}}
                                </router-link>
                                <small><a href="" class="btn btn-sm btn-outline-secondary pt-0 pb-0 pl-3 pr-3">Rate</a></small>
                            </b>
                        </td>
                        <td>
                            <b>
                                <router-link class="special-link" :to="'clinic_'+appointment.doctor.clinic.id">
                                    {{appointment.doctor.clinic.name}}
                                </router-link>
                            </b>
                            <p class="text-muted mb-0"><small>{{appointment.doctor.clinic.address}}</small></p>
                            <small><a href="" class="btn btn-sm btn-outline-secondary pt-0 pb-0 pl-3 pr-3">Rate</a></small>
                        </td>
                        <td>
                            {{appointment.procedureType.typeName}}
                            <p class="text-muted"><small>{{appointment.procedureType.typeDescription}}</small></p>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
export default {
    name: "procedureHistory",
    data: function() {
        return{
            reports : [],
            appointments : []
        }
    }, 
    methods: {
        dateSort: function() {
            this.appointments.sort((a,b) => (a.dateOfProcedure > b.dateOfProcedure) ? 1 : -1);
        },
        doctorSort: function() {
            this.appointments.sort((a,b) => (a.doctor.firstName > b.doctor.firstName) ? 1 : -1);
        },
        clinicSort: function() {
            this.appointments.sort((a,b) => (a.doctor.clinic.name > b.doctor.clinic.name) ? 1 : -1);
        },
        procedureSort: function() {
            this.appointments.sort((a,b) => (a.procedureType.typeName > b.procedureType.typeName) ? 1 : -1);
        }
    },
    filters : {
        formatDate: function(date){
                if (!date) return '';
                let formatedDate = new Date(date);
                var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
                var day = ('0' + formatedDate.getDate()).slice(-2);
                var year = formatedDate.getFullYear();
                return day + '.' + month + '.' + year + '.';
            }
    },
    mounted : function() {
		this.axios.get("http://localhost:8080/record/" + localStorage.getItem('user_id'))
		.then(response => {
            this.reports = response.data.reports;
        });

        this.axios.get("http://localhost:8080/medical/procedure/patient/past", {
            params: {
                patient : localStorage.getItem('user_id')}
        })
        .then(response => {
            this.appointments = response.data;
            console.log(this.appointments)
        });
	}
}
</script>

<style>
.pointer {
    cursor: pointer;
}
.pointer:hover {
    color: #b4d8ff;
}

</style>