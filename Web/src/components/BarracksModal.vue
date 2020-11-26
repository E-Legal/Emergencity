<template>
  <div class="barracksmodal">
    <b-modal
      centered
      title="BootstrapVue"
      hide-header
      no-close-on-backdrop="true"
      hide-footer
      ref="barracks_modal"
      id="barracks_modal"
    >
      <form id="demo" @submit.prevent="submit" novalidate>
        <div>
          <b-form-select
            style="width: 200px"
            v-model="selected"
            :options="options"
          ></b-form-select>

          <input
            type="submit"
            class="btn btn-outline-primary"
            value="Lier la caserne"
          />
        </div>
      </form>
      <div v-if="this.idBarracks">
        <div v-if="this.barracks">
          <vuetable
            ref="vuetable"
            :fields="fields"
            :api-mode="false"
            :data="this.barracks"
          >
            <template slot="actions" slot-scope="props">
              <div>
                <b-button
                  :id="props['rowData'].id"
                  v-on:click="eraseLink(props, props['rowData'].id)"
                  variant="danger"
                  >Supprimer</b-button
                >
              </div>
            </template>
          </vuetable>
        </div>
        <div v-else></div>
      </div>
      <div v-else>
        Loading
      </div>
      <b-button class="mt-3" variant="outline-danger" block @click="hideModal"
        >Ok</b-button
      >
    </b-modal>
  </div>
</template>

<style scoped></style>

<script>
import Vuetable from "vuetable-2";
import axios from "axios";

export default {
  name: "barracksmodal",
  components: {
    Vuetable
  },
  props: {
    msg: String,
    idBarracks: String,
    reload: Boolean
  },
  watch: {
    idBarracks: function(newVal, oldVal) {
      // watch it
      console.log("Prop changed: ", newVal, " | was: ", oldVal);
      console.log("barracks");
      this.linkedBarracks();
    }
  },
  data() {
    return {
      all_barracks: null,
      selected: null,
      options: [],
      fields: [
        {
          name: "city",
          title: "Ville"
        },
        {
          name: "name",
          title: "Caserne"
        },
        {
          name: "actions",
          title: "",
          titleClass: "center aligned",
          dataClass: "center aligned"
        }
      ],
      barracks: null
    };
  },
  mounted() {
    this.listBarracks();
  },
  methods: {
    listBarracks() {
      this.$store.dispatch("Barrack/getBarracks", this.$store.getters["User/token"]).then((response) => {
        this.all_barracks = this.$store.getters['Barrack/barracks']
        console.log(this.all_barracks, "yoloswag")
        this.all_barracks.forEach((value, index) => {
            var obj = {};
            obj["value"] = value.id;
            obj["text"] = value.name + " | " + value.city;
            this.options.push(obj);
        });
      })
    },
    submit() {
      this.$store.dispatch("Barrack/addUserBarracks", {token: this.$store.getters["User/token"], userId: this.idBarracks, barrackId: this.selected}).then((response) => {
        this.linkedBarracks();
      })
    },
    eraseLink(props, val) {
      console.log(this.idBarracks);
      this.$store.dispatch("Barrack/deleteUserBarracks", {token: this.$store.getters["User/token"], userId: this.idBarracks, barrackId: val}).then((response) => {
        this.linkedBarracks()
      })
    },
    linkedBarracks() {
      this.$store.dispatch("Barrack/getUserBaracks", {token: this.$store.getters["User/token"], userId: this.idBarracks}).then((response) => {
        this.barracks = this.$store.getters['Barrack/userBarracks']
      })
    },
    showModal() {
      this.$refs["barracks_modal"].show();
    },
    hideModal() {
      this.$refs["barracks_modal"].hide();
    },
    toggleModal() {
      this.$refs["barracks_modal"].toggle("#toggle-btn");
    },
    toggleInfoWindow: function(marker, idx) {
      this.infoWindowPos = marker.position;
      this.infoOptions.content = marker.infoText;
      if (this.currentMidx == idx) {
        this.infoWinOpen = !this.infoWinOpen;
      } else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    }
  }
};
</script>
