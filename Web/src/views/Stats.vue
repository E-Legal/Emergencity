<template>
  
  <div class="stats" style="background: url('/assets/white3.png') repeat center center fixed;  background-position: center; overflow: auto;">
    <div>
      <div class="container">
        <div class="Chart">
               <!-- <datepicker class="redsize" ></datepicker>   -->
               <input type="date"/>    <input type="date"/>     
             <select class="form-control redsize" id="exampleFormControlSelect1">
              <option>Paris</option>
              <option>Marseille</option>
              <option>Nice</option>
              <option>Lyon</option>
              <option>Montpellier</option>
            </select>
        </div>
       <div class="row">
          <div class="col-6">
            <div class="Chart">
              <h2>Nombre d'interventions</h2>
              <line-example v-if="this.intervention" title="Intervention" :intervention="this.intervention" :key="componentKey[0]"></line-example>
              <div v-else class="loader"></div>
            </div>
          </div>
          <div class="col-6">
            <div class="Chart">
              <h2>Moy. d'interventions</h2>
              <bar-example v-if="this.moyenne_intervention" title="Moy.Intervention" :moyenne_intervention="this.moyenne_intervention" :key="componentKey[1]"></bar-example>
              <div v-else class="loader"></div>
            </div>
          </div>
          <div class="col-6">
            <div class="Chart">
              <h2>Véhicule Actif</h2>
              <bar-example v-if="this.vehicule" title="Véhicule Actif" :vehicule="this.vehicule" :key="componentKey[2]"></bar-example>
              <div v-else class="loader"></div>
            </div>
          </div>
          <div class="col-6">
            <div class="Chart">
              <h2>Total feux modifiés</h2> 
              <line-example v-if="this.total_feux" title="Total Feux" :total_feux="this.total_feux" :key="componentKey[3]"></line-example>
              <div v-else class="loader"></div>
            </div>
          </div>
          </div>
        </div>
      </div>  
  </div>
      
</template>

<style lang="scss" scoped>
.redsize {
    max-width: 200px;
}
.custom_picker {
  max-height: 50px;
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
#stats {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
.container {
  max-width: 1500px;
}
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
import LayoutDefault from "../layouts/LayoutDefault.vue";
import LineExample from '../script_chart/linechart.js'
import BarExample from '../script_chart/barchart.js'
import axios from 'axios'
import Datepicker from 'vuejs-datepicker';

export default {
  name: "stats",
  props: {
    msg: String
  },
  components: {
    LineExample,
    BarExample,
     Datepicker
  },
  data() {
    return {
        baseUrl: process.env.VUE_APP_BASE_URL,
        vehicule: null,
        total_feux: null,
        moyenne_intervention: null,
        intervention: null,
        componentKey: [1, 101, 201, 301],
    }
  },
  created() {
    
    this.$emit("update:layout", LayoutDefault);
    this.initialize_graph()
    setInterval(this.getIntervention, 1000);
    setInterval(this.getMoyintervention, 1000);
    setInterval(this.getTotalfeux, 1000);
    setInterval(this.getVehicule, 1000);
  },
  methods: {
                initialize_graph: function() {
                    axios.get('/json/active.json?new=true').then(response => {
                        this.intervention = response.data
                    });
                    axios.get('/json/vehicule_actif.json?new=true').then(response => {
                        this.vehicule = response.data
                    });
                    axios.get('/json/total_feux_modifies.json?new=true').then(response => {
                        this.total_feux = response.data
                    });
                    axios.get('/json/moyenne_temps_intervention.json?new=true').then(response => {
                        this.moyenne_intervention = response.data
                    });
                },
                getIntervention: function() {
                    //console.log('/json/active.json')
                    axios.get('/json/active.json?new=false').then(response => {
                        //console.log(response.data);
                        this.intervention = response.data
                        //console.log(this.intervention)
                    })
                    if (this.intervention[0]["changed?"] == true) {
                      if (this.componentKey[0] >= 100) {
                        this.componentKey[0] = 1
                      } else {
                        this.forceRerender(0)
                        this.forceRerender(1)
                      }
                    }
                },
                getTotalfeux: function() {
                    axios.get('/json/total_feux_modifies.json?new=true').then(response => {
                        this.total_feux = response.data
                    });
                    if (this.total_feux[0]["changed?"] == true) {
                      if (this.componentKey[0] >= 300) {
                        this.componentKey[0] = 201
                      } else {
                        this.forceRerender(2)
                      }
                    }
                },
                getVehicule: function() {
                    //console.log('/json/active.json')
                    axios.get('/json/vehicule_actif.json?new=true').then(response => {
                        this.vehicule = response.data
                    });
                    if (this.vehicule[0]["changed?"] == true) {
                      if (this.componentKey[1] >= 200) {
                        this.componentKey[0] = 101
                      } else {
                        this.forceRerender(1)
                      }
                    }
                },
                getMoyintervention: function() {
                    axios.get('/json/moyenne_temps_intervention.json?new=true').then(response => {
                        this.moyenne_intervention = response.data
                    });
                    if (this.moyenne_intervention[0]["changed?"] == true) {
                      if (this.componentKey[0] >= 400) {
                        this.componentKey[0] = 301
                      } else {
                        this.forceRerender(3)
                      }
                    }
                },
                forceRerender(component) {
                  this.componentKey[component] = this.componentKey[component] + 1
                }
            }
};
</script>