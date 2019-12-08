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
                room: null
            }
        },
        mounted: function () {
            this.axios.get("http://localhost:8080/medical/room/" + this.$route.query.roomId)
                .then(response => {
                    this.room = response.data;
                    this.roomName = this.room.roomName;
                    this.roomNumber = this.room.roomNumber;
                })
        },
        methods: {
            editRoom: function () {

                this.room.roomName = this.roomName;
                this.room.roomNumber = this.roomNumber;

                var valid = true;

                if (this.roomName != undefined)
                    this.roomName.trim();
                else
                    this.roomName = '';

                if (this.roomName === undefined || this.roomName === ''
                            || this.roomNumber === undefined) {
                    valid = false
                }

                if (valid) {
                    this.axios.put("http://localhost:8080/medical/room/update", this.room)
                        .then(response => {
                            this.$router.push('/searchRooms');
                        })
                }
            }
        }
    }

</script>

<style scoped>

</style>