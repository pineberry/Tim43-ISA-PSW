<template>
    <div>
        <FullCalendar
                ref="fullCalendar"
                defaultView="dayGridMonth"
                :plugins="calendarPlugins"
                :header="{
        left: 'title',
        center: 'dayGridMonth, timeGridWeek, timeGridDay, listWeek',
        right: 'prev today next'
        }"
                :events="calendarEvents"
                :minTime="'07:00:00'"
                :maxTime="'23:00:00'"
                @eventClick="eventClickHandler"
        />
    </div>
</template>

<script>
    require("@fullcalendar/core/main.min.css");
    require("@fullcalendar/daygrid/main.min.css");
    require("@fullcalendar/timegrid/main.min.css");

    import FullCalendar from "@fullcalendar/vue";
    import dayGridPlugin from "@fullcalendar/daygrid";
    import timeGridPlugin from "@fullcalendar/timegrid";
    import interactionPlugin from "@fullcalendar/interaction";

    export default {
        name: "RoomCalendar",
        components: {
            FullCalendar // make the <FullCalendar> tag available
        },
        data: () => ({
            calendarPlugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
            calendarEvents: [],
            response: undefined
        }),
        mounted: function() {
            this.axios
                .get(
                    "http://localhost:8080/medical/procedure/room/" + this.$route.query.id)
                .then(response => {
                    this.response = response;
                    let procedures = this.response.data;
                    procedures.forEach(element => {
                        let start = new Date(element.dateOfProcedure);
                        let end = new Date(element.dateOfProcedure);
                        let startTime = element.startTime.split(":");
                        let endTime = element.endTime.split(":");
                        start.setHours(parseInt(startTime[0]));
                        start.setMinutes(parseInt(startTime[1]));
                        start.setSeconds(0);
                        end.setHours(parseInt(endTime[0]));
                        end.setMinutes(parseInt(endTime[1]));
                        end.setSeconds(0);
                        this.calendarEvents.push({
                            title: "Pregled",
                            start: start,
                            end: end,
                            allDay: false
                        });
                    });
                })
        }
    }
</script>

<style scoped>

</style>