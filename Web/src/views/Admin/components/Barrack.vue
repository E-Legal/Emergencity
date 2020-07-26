/* eslint-disable */
<template>
  <div name="Barrack">
      <div>
      <VehiculeModal :idBarracks="idBarracks"/>
<b-tabs content-class="mt-3" justified>
    <b-tab  v-if="this.label" title="Gestion Caserne">
    
        <form id="demo" @submit.prevent="submit" novalidate>
            <input
              type="text"
              id="login"
              class="col-lg-3"
              name="city"
              placeholder="Ville"
              v-model="city"
            />
            <input
              type="text"
              id="password"
              class="col-lg-3"
              name="caserne"
              placeholder="Nom de caserne"
              v-model="nom"
              style="margin-left: 1%; margin-right: 1%"
            />
            <input type="submit" class="btn btn-outline-primary" value="Ajouter une caserne" />
      </form>
      <vuetable ref="vuetable"
    :fields="fields"
    :api-mode="false"
    :data="this.label"
  >
  <template slot="vehicule_actions" slot-scope="props"> 
      <div>
          <b-button :id="props['rowData'].id" v-on:click="showVehiculeDialog(props, props['rowData'].id)" variant="outline-primary">Voir les Vehicules liées à la caserne</b-button>
      </div>
    </template>
  <template slot="erase_actions" slot-scope="props"> 
      <div>
         <b-button :id="props['rowData'].id" v-on:click="showMsgBoxOne(props, props['rowData'].id)" variant="danger">Supprimer</b-button>
      </div>
    </template>

    </vuetable></b-tab>
    <b-tab v-else title="Gestion Caserne"><div class="loader"></div></b-tab>
</b-tabs>
</div>
  </div>
</template>

<style scoped>
.gmap {
width: 1920px; height: calc(100vh - 66px)
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
import Vuetable from 'vuetable-2';
import axios from 'axios';
import VehiculeModal from "../../../components/VehiculeModal.vue";
import BarracksModal from "../../../components/BarracksModal.vue";
import UserModal from "../../../components/UserModal.vue";
export default {
  name: "Barrack",
  components: {
    Vuetable,
    VehiculeModal,
    BarracksModal
  },
  props: {
      total: Array
    },
  data() {
    return {
        idBarracks: null,
        city: null,
        nom: null,
        label: null,
        fields: [
        {
          name: 'name',
          title: 'Nom des Caserne'
        },
                {
          name: 'city',
          title: 'Ville'
        },
      {
          name: 'vehicule_actions',
          title: 'Vehicules',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        },

        {
          name: 'erase_actions',
          title: 'Actions',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        }]
    };
  },
  watch: { 
    // Watch Total Props and put it into Label State
      	total: function(newVal, oldVal) { 
          console.log('Prop changed: ', newVal, ' | was: ', oldVal);
          console.log(this.total);
          if (this.total) {
            this.total.forEach((value, index) => {
                  this.label = this.total;
                  console.log('label=')
                  console.log(this.label)
            });
        }
    }
  },
  methods: {
    updateBarracksList() {
          axios.get('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks?token=' + localStorage.getItem('token')).then(response => {
              console.log("Barracks=")

              this.label = response.data['content']
              console.log(this.barracks);
          });
    },
    showMsgBoxOne(props, val) {
        this.boxOne = ''
        this.$bvModal.msgBoxConfirm('Etes-vous sûr de vouloir supprimer cet caserne?')
          .then(value => {
            //this.boxOne = value
            if (value === true) {
              console.log("erase")
              this.eraseLight(props, val)
            }
          })
          .catch(err => {
            // An error occurred
          })
      },
      eraseLight(props, val) {
        console.log(val);
        console.log(props);
        axios.delete("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks/" + val + '?token=' + localStorage.getItem("token")).then((response) => {

        })
        this.label.splice(props['rowIndex'], 1);

      },
      showVehiculeDialog(props, val) {
        console.log("oui")
        console.log(val);
        this.idBarracks = val;
        this.$bvModal.show("vehicule_modal");
        //this.$bvModal.show("barracks_modal");
        //this.$bvModal.show("my-modal");

    },
    submit() {
      axios.post('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks/new?city=' + this.city+ '&name=' + this.nom + '&token=' + localStorage.getItem('token'))
        .then((response) => {
           if (response.status === 200) {
             this.updateBarracksList();
           }
        });
    }
  }   
};

</script>
