<template>
  <div v-if="doctor">
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

    <modals-container />
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

import ModalEvent from "./ModalEvent";

export default {
  name: "Calendar.vue",
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  props: {
    doctor: Object
  },
  data: () => ({
    calendarPlugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    calendarEvents: []
  }),
  mounted: function() {
    this.populateCalendar();
  },
  methods: {
    eventClickHandler(arg) {
      this.$modal.show(ModalEvent, {
        event: arg.event
      });
    },
    populateCalendar() {
      if (localStorage.getItem("typeOfUser") == "doctor") {
        let procedures = this.doctor.medicalProcedures;
        let attending = this.doctor.attendingProcedures;
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
            id: element.id,
            title: "Pregled",
            start: start,
            end: end,
            allDay: false
          });
        });
        attending.forEach(element => {
          let exist = false;
          this.calendarEvents.forEach(ele => {
            if (element.id === ele.id) {
              exist = true;
            }
          });
          if (!exist) {
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
              id: element.id,
              title: "Pregled",
              start: start,
              end: end,
              allDay: false
            });
          }
        });
      }
    }
  }
};
</script>

<style scoped>
</style>