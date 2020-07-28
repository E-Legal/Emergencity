<template>
  <div class="agenda">
   <div class="container">
      <div class="Chart">
        <h2>Agenda</h2>
            
      </div>
    </div>
    <div class="container">
      <div class="Chart">
       <b-card no-body>
        <!--- 0 -->
        <FullCalendar :options="calendarOptions" />
     </b-card>
      </div>
    </div>
  </div>
      
</template>

<style scoped lang="scss">

.loader {
  border: 10px solid #f3f3f3;
  border-radius: 50%;
  border-top: 10px solid #3498db;
  width: 30px;
  margin: 0 auto;
  height: 30px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

.Chart {
  background: #212733;
  border-radius: 15px;
  box-shadow: 0px 2px 15px rgba(25, 25, 25, 0.27);
  margin:  25px 0;
}

.Chart h2 {
  margin-top: 0;
  padding: 15px 0;
  color:  #ababab;
  text-align: center;
  border-bottom: 1px solid #323d54;
}
.container {
  max-width: 1500px;
}
/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
<script>
  import LayoutDefault from "../../layouts/LayoutDefault.vue";
  import axios from 'axios';
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'

  export default {
    name: "agenda",
    components: {
       FullCalendar
    },
    data() {
    return {
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin ],
        initialView: 'dayGridMonth',
        eventClick: this.handleEventClick,
        dateClick: this.handleDateClick,
        events: [
          { title: 'Remise en Route', date: '2020-07-05', id:"test" },
          { title: 'Intervention Feu', date: '2020-07-03' },
        ]
      }
    }
    },
  methods: {
    handleDateClick: function(arg) {
      alert('date click! ' + arg.dateStr)
    },
      handleEventClick: function(event, value) {
        console.log(event);
        console.log(value);
      alert('Event click! ' + event)
    }
  },
    mounted() {

      this.$emit("update:layout", LayoutDefault);
    },
    created() {
      this.log = localStorage.login;
      this.$emit("update:layout", LayoutDefault);
    }
  };
</script>
