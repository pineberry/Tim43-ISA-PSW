<template>
  <div class="container">
        <div class="col-10 card m-5 p-5">
            <div class="row justify-content-center">
                <p><small>Blood type: </small>{{medicalRecord.bloodType}}</p>
            </div>
            <div class="row justify-content-center">
                <p><small>Height: </small>{{medicalRecord.height}}cm</p>
            </div>
            <div class="row justify-content-center">
                <p><small>Weight: </small>{{medicalRecord.weight}}kg</p>
            </div>
            <div class="row justify-content-center">
                <p><small>Date of birth: </small>{{medicalRecord.dateOfBirth | formatDate}}</p>
            </div>
        </div>
  </div>
</template>

<script>
export default {
    name: "medicalRecord",
    data: function() {
        return {
            medicalRecord : undefined
        }
    }, 
    filters : {
        formatDate: function(date){
                if (!date) return '';
                let formatedDate = new Date(date);
                var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
                var day = ('0' + formatedDate.getDate()).slice(-2);
                var year = formatedDate.getFullYear();
                return day + '.' + month + '.' + year;
            }
    },
    mounted : function() {
		this.axios.get("http://localhost:8080/record/" + localStorage.getItem('user_id'))
		.then(response => {
            this.medicalRecord = response.data;
            console.log(this.medicalRecord);
		})
	}

}
</script>

<style>

</style>