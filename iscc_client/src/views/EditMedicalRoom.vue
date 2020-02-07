<template>
    <div class="container">
        <h4>Edit Medical Room</h4>
        <form v-on:submit.prevent="editRoom" accept-charset="UTF-8" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputName">Room name:</label>
                        <input id="inputName" type="text" class="form-control" placeholder="Enter room name" v-model="roomName">
                    </div>
                    <div class="form-group">
                        <label for="inputNumber">Room number:</label>
                        <input id="inputNumber" type="number" class="form-control" placeholder="Enter room number" v-model="roomNumber">
                    </div>
                    <div class="form-group">
                        <label for="selectClinic">Clinic</label>
                        <select id="selectClinic" class="form-control" v-model="clinic">
                            <option v-for="c in clinics" :key="c.id" :value="c">{{c.name}}</option>
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
        name: "editMedicalRoom",
        data: function () {
            return {
                roomName: undefined,
                roomNumber: undefined,
                room: null,
                clinic: null,
                clinics: []
            }
        },
        mounted: function () {
            this.axios.get("http://localhost:8080/medical/room/" + this.$route.query.roomId)
                .then(response => {
                    this.room = response.data;
                    this.roomName = this.room.roomName;
                    this.roomNumber = this.room.roomNumber;
                    this.clinic = this.room.clinic;
                })
            this.axios.get("http://localhost:8080/clinic/clinics")
                .then(response => {this.clinics = response.data})
                .catch(error => {alert(error.response.data)})
        },
        methods: {
            editRoom: function () {

                this.room.roomName = this.roomName;
                this.room.roomNumber = this.roomNumber;
                this.room.clinic = this.clinic;

                if (this.roomName != undefined)
                    this.roomName.trim();
                else
                    this.roomName = '';

                if (this.roomName === '' || this.roomNumber === undefined) {
                    alert('All fields should be filled!');
                    return;
                }

                this.axios.put("http://localhost:8080/medical/room/update", this.room)
                    .then(response => {
                        this.$router.go(-1);
                    })
            }
        }
    }

</script>

<style scoped>

</style>