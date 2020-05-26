/* eslint-disable */
<template>
  <div name="Light">
<div>

<b-tabs content-class="mt-3" justified>
    <b-tab  v-if="this.label" title="Gestion Feux de signalisation">
        <form id="demo" @submit.prevent="submit" novalidate>
        <div>

            <input
              type="text"
              id="password"
              class="col-lg-3"
              name="Latitude"
              placeholder="Latitude"
              v-model="form.x"
              style="margin-left: 1%; margin-right: 1%"
            />
                        <input
              type="text"
              id="password"
              class="col-lg-3"
              name="Longitude"
              placeholder="Longitude"
              v-model="form.y"
              style="margin-left: 1%; margin-right: 1%"
            />
                        <input
              type="text"
              id="login"
              class="col-lg-3"
              name="axe_z"
              placeholder="Axe Z"
              v-model="form.z"
              style="margin-left: 1%; margin-right: 1%"
            />
            <input type="submit" class="btn btn-outline-primary" value="Ajouter un utilisateur" />
                        <input
              type="text"
              id="password"
              class="col-lg-3"
              name="Ville"
              placeholder="Ville"
              v-model="form.ville"
              style="margin-left: 1%;"
            />

            
            </div>
      </form>
      <vuetable ref="vuetable"
    :fields="fields"
    :api-mode="false"
    :data="this.label"
  >
        <template slot="actions" slot-scope="props"> 
          <div>             
              <iframe :src="props['rowData'].gmap" width="150" height="150" frameborder="0" style="border:0"></iframe>
          </div>
    </template>
            <template slot="eraser-slot" slot-scope="props"> 
              <div>
                <b-button :id="props['rowData'].id" v-on:click="eraseLight(props, props['rowData'].id)" variant="danger">Supprimer</b-button>
              </div>
            </template>
  </vuetable>
    </b-tab>
    <b-tab v-else title="Gestion Feux de signalisation"><div class="loader"></div></b-tab>
</b-tabs>

</div>
  </div>
</template>

<style scoped>
.gmap {
width: 150px; height: 150px
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
import Vuetable from 'vuetable-2'
import {gmapApi} from 'vue2-google-maps'
import axios from 'axios';

export default {
  name: "Light",
  components: {
    Vuetable
  },
  props: {
      total: Array
    },
  data() {
    return {
      form: {
        x: null,
        y: null,
        z: null,
        ville: null
      },
      label: null,
      fields: [
        {
          name: 'city',
          title: 'Ville',
          titleClass: 'center aligned',
          dataClass: 'center aligned',
          sortField: 'city',
        },
        {
          name: 'x',
          title: 'x',
          titleClass: 'center aligned',
          dataClass: 'center aligned',
        },
        {
          name: 'y',
          title: 'y',
          titleClass: 'center aligned',
          dataClass: 'center aligned',
        },
        {
          name: 'z',
          title: 'z',
          titleClass: 'center aligned',
    dataClass: 'center aligned',
        },
        {
          name: 'actions',
          title: 'Carte',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        },
        {
          name: 'eraser-slot',
          title: '',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        }

      ]
    };
  },
  watch: { 
      	total: function(newVal, oldVal) { // watch it
        console.log('Prop changed: ', newVal, ' | was: ', oldVal);
        if (this.total) {
              this.label = this.total;
                this.label.forEach((value, index) => {
                  this.label[index]["gmap"] = "https://maps.google.com/maps?q="+ value.x + ", " + value.y + "&z=15&output=embed"
            });
            }
        }
    },
  methods: {
    log(message) {
      console.log(message)
    },
    assignCenter() {

    },
    eraseLight(props, val) {
      console.log(val);
      console.log(props);
      this.label.splice(props['rowIndex'], 1);
    },
    submit() {
      axios.post('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/feu?x=' + this.form.x + '&y=' + this.form.y + '&z=' + this.form.z + '&city=' + this.form.ville + '&token=' + localStorage.getItem('token'))
        .then((response) => {
           if (response.status === 200) {
             this.$forceUpdate();
           }
        });
    }
  }
};

</script>
