import {Line, Bar} from 'vue-chartjs'

export default {
    extends: Bar,
    props: {
      title: String,
      intervention: Array,
      vehicule: Array,
      moyenne_intervention: Array,
      total_feux: Array

    },
    data () {
      return {
        gradient: null,
        gradient2: null,
        label: [],
        donnee: [],
        options: {responsive: true, maintainAspectRatio: false}
      }
    },
    mounted () {
      if (this.total_feux) {
        this.total_feux.forEach((value, index) => {
          if (index === 0) {
            return;
          }
          this.label.push(value["date"]);
          this.donnee.push(value["total_feux_modifies"]);
       });
      }
      if (this.moyenne_intervention) {
        this.options = {  scales: {
          yAxes: [{
              ticks: {
                  suggestedMin: 0,
                  suggestedMax: 100
              }
          }]
      }, responsive: true, maintainAspectRatio: false}
        this.moyenne_intervention.forEach((value, index) => {
          if (index === 0) {
            return;
          }
          this.label.push(value["date"]);
          this.donnee.push(value["moyenne_tps_intervention"]);
       });
      }
      if (this.vehicule) {
        this.vehicule.forEach((value, index) => {
          if (index === 0) {
            return;
          }
          this.label.push(value["date"]);
          this.donnee.push(value["nb_vehicules_actifs"]);
       });
      }
    if (this.intervention) {
      this.intervention.forEach((value, index) => {
        if (index === 0) {
          return;
        }
        this.label.push(value["date"]);
        this.donnee.push(value["nb_interventions"]);
     });
    }
    console.log(this.donnee)
      this.gradient = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450)
      this.gradient2 = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450)
  
      this.gradient.addColorStop(0, 'rgba(255, 0,0, 0.5)')
      this.gradient.addColorStop(0.5, 'rgba(255, 0, 0, 0.25)');
      this.gradient.addColorStop(1, 'rgba(255, 0, 0, 0)');
  
      this.gradient2.addColorStop(0, 'rgba(0, 231, 255, 0.9)')
      this.gradient2.addColorStop(0.5, 'rgba(0, 231, 255, 0.25)');
      this.gradient2.addColorStop(1, 'rgba(0, 231, 255, 0)');
  
  
      this.renderChart({
        labels: this.label,
        datasets: [
          {
            label: this.title,
            labelColor: '#FFFFFF',
            borderColor: '#FFFFFF',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: 'white',
            backgroundColor: this.gradient2,
            data: this.donnee,

          }
        ],
      }, this.options)
  
    }
  }