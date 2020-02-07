<template>
  <div>
    <div class="card">
      <div class="card-header">
        Event details
      </div>
      <div class="card-body">
        <b>Title: </b>{{title}}<br />
        <b>Start: </b>{{start}}<br />
        <b>End: </b>{{end}}<br />
        <div v-if="procedure.patient">
          <b>Patient: </b>{{procedure.patient.firstName}} {{procedure.patient.lastName}} <br/>
          <router-link v-if="procedure.medicalRoom" class="btn btn-primary btn-sm" :to="{path: '/report', query: {id: procedure.patient.id, proc: procedure.id}}">
              Exam
          </router-link>
        </div>
        <div v-else>
          <p>Predefined exam not booket yet</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    title: "",
    start: {},
    end: {},
    procedure: Object
  }),
  props: {
    event: Object
  },
  mounted() {
    this.axios
      .get("http://localhost:8080/medical/procedure/" + this.event.id)
      .then(response => {
        this.procedure = response.data;
      });
    this.title = this.event.title;
    this.start = formatDate(this.event.start);
    this.end = formatDate(this.event.end);
  }
};

function formatDate(date) {
  if (date != null) {
    var options = { year: "numeric", month: "2-digit", day: "2-digit" };
    let string = date.toLocaleDateString("sv-SE", options);
    return string;
  }
  return "";
}
</script>

<style>
</style>