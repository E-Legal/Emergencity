/* eslint-disable */
<template>
  <div name="User">
    <div>
      <Barracksmodal :idBarracks="idBarracks" msg="yolo" />
      <b-tabs content-class="mt-3" justified>
        <b-tab v-if="this.label" title="Gestion Utilisateurs">
          <form id="demo" @submit.prevent="submit" novalidate>
            <div>
              <input
                type="text"
                id="login"
                class="col-lg-3"
                name="login"
                placeholder="Identifiant"
                v-model="email"
              />
              <input
                type="password"
                id="password"
                class="col-lg-3"
                name="login"
                placeholder="Mot de passe"
                v-model="password"
                style="margin-left: 1%; margin-right: 1%"
              />
              <input
                type="submit"
                class="btn btn-outline-primary"
                value="Ajouter un utilisateur"
              />
            </div>
          </form>
          <vuetable
            ref="vuetable"
            :fields="fields"
            :api-mode="false"
            :data="this.label"
          >
            <template slot="rank_actions" slot-scope="props">
              <div>
                {{ assignUserRole(props["rowIndex"], props["rowData"]) }}
                <b-form-select
                  style="width: 200px"
                  v-model="selected[props['rowIndex']]"
                  :options="options"
                ></b-form-select>
              </div>
            </template>
            <template slot="caserne_actions" slot-scope="props">
              <div>
                <b-button
                  :id="props['rowData'].id"
                  v-on:click="showCaserneDialog(props, props['rowData'].id)"
                  variant="outline-primary"
                  >Voir les Casernes liées à l'utilisateur</b-button
                >
              </div>
            </template>
          </vuetable>
        </b-tab>
        <b-tab v-else title="Gestion utilisateurs"
          ><div class="loader"></div
        ></b-tab>
      </b-tabs>
    </div>
  </div>
</template>

<style scoped>
.gmap {
  width: 1920px;
  height: calc(100vh - 66px);
}
/* Safari */
* .loader {
  border: 10px solid #f3f3f3;
  border-radius: 50%;
  border-top: 10px solid #3498db;
  width: 30px;
  margin: 0 auto;
  height: 30px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% {
    -webkit-transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
  }
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>

<script>
import Vuetable from "vuetable-2";
import axios from "axios";
import Barracksmodal from "../../../components/BarracksModal.vue";
import Display from "../../../components/MyModal.vue";

export default {
  name: "User",
  components: {
    Vuetable,
    Barracksmodal,
    Display
  },
  props: {
    total: Array
  },
  data() {
    return {
      idBarracks: null,
      email: null,
      password: null,
      selected: [],
      options: [
        { value: null, text: "Please select an option", disabled: true },
        { value: "Admin", text: "Admin" },
        { value: "Member", text: "Member" },
        { value: "Algorithm", text: "Algorithm", disabled: true }
      ],
      fields: [
        {
          name: "username",
          title: "Utilisateur"
        },
        {
          name: "rank_actions",
          title: "Rang",
          titleClass: "center aligned",
          dataClass: "center aligned"
        },
        {
          name: "caserne_actions",
          title: "Caserne",
          titleClass: "center aligned",
          dataClass: "center aligned"
        },
      ],
      label: null,
      boxOne: ""
    };
  },
  watch: {
    total: function(newVal, oldVal) {
      // watch it
      console.log("Prop changed: ", newVal, " | was: ", oldVal);
      if (this.total) {
        this.total.forEach((value, index) => {
          this.label = this.total;
          console.log(this.label);
        });
      }
    }
  },
  methods: {
    //...
    updateUserList() {
      console.log("UPDATING ???");
       this.$store.dispatch("User/handleListUsers", {token: this.$store.getters["User/token"]}).then((response) => {
         ////console.log("ALL VEHICLE", this.all_vehicle)
         this.label = this.$store.getters['User/listUsers']
      })
      axios
        // .get(
        //   "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/SU/user/list?token=" +
        //     localStorage.getItem("token")
        // )
        // .then(response => {
        //   console.log(response.data);
        //   this.label = response.data["content"];
        // });
    },
    showMsgBoxOne(props, val) {
      this.boxOne = "";
      this.$bvModal
        .msgBoxConfirm("Etes-vous sûr de vouloir supprimer cet utilisateur?")
        .then(value => {
          //this.boxOne = value
          if (value === true) {
            console.log("erase");
            this.eraseLight(props, val);
          }
        })
        .catch(err => {
          // An error occurred
        });
    },
    showCaserneDialog(props, val) {
      this.idBarracks = val;
      this.$bvModal.show("barracks_modal");
      //this.$bvModal.show("my-modal");
    },
    onAction(action, data, index) {
      console.log("slot) action: " + action, data.name, index);
    },
    eraseLight(props, val) {
      console.log(val);
      console.log(props);
      
      
      axios
        .delete(
          "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/SU/user/" +
            val +
            "?token=" +
            localStorage.getItem("token")
        )
        .then(response => {});


      this.label.splice(props["rowIndex"], 1);
    },
    assignUserRole(rowIndex, rowData) {
      console.log(rowData, "ROWDATA");
      if (rowData.admin) {
        this.selected[rowIndex] = "Admin";
        return;
      } else if (rowData.algorithm) {
        this.selected[rowIndex] = "Algorithm";
        return;
      } else {
        this.selected[rowIndex] = "Member";
        return;
      }
    },
    submit() {
      
      this.$store.dispatch("User/createUser", {token: this.$store.getters["User/token"], user: this.email, password: this.password}).then((response) => {
         this.updateUserList();
      })
      // axios
      //   .post(
      //     "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/register?name=" +
      //       this.email +
      //       "&password=" +
      //       this.password
      //   )
      //   .then(response => {
      //     if (response.status === 200) {
      //       this.updateUserList();
      //     }
      //   });
    }
  }
};
</script>
