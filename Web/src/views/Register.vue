<template>
  <div class="register">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" />
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <header class="card-header">
              <a href class="float-right btn btn-outline-primary mt-1">Log in</a>
              <h4 class="card-title mt-2">Sign up</h4>
            </header>
            <article class="card-body">
              <form id="demo">
                <div class="form-row">
                  <div class="col form-group">
                    <label>Prénom</label>
                    <input type="text" class="form-control" placeholder />
                  </div>
                  <!-- form-group end.// -->
                  <div class="col form-group">
                    <label>Nom</label>
                    <input type="text" class="form-control" placeholder=" " />
                  </div>
                  <!-- form-group end.// -->
                </div>
                <!-- form-row end.// -->
                <div class="form-group">
                  <label>Email</label>
                  <input type="email" class="form-control" placeholder />
                </div>
                <!-- form-group end.// -->
                <div class="form-group">
                  <label class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="option1" />
                    <span class="form-check-label">Homme</span>
                  </label>
                  <label class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="option2" />
                    <span class="form-check-label">Femme</span>
                  </label>
                </div>
                <!-- form-group end.// -->
                <div class="form-row">
                  <div class="form-group col-md-6">
                    <label>Ville</label>
                    <input type="text" class="form-control" />
                  </div>
                  <!-- form-group end.// -->
                  <div class="form-group col-md-6">
                    <label>Country</label>
                    <select id="inputState" class="form-control">
                      <option>Choose...</option>
                      <option>France</option>
                    </select>
                  </div>
                  <!-- form-group end.// -->
                </div>
                <!-- form-row.// -->
                <div class="form-group">
                  <label>Create password</label>
                  <input class="form-control" type="password" />
                </div>
                <!-- form-group end.// -->
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-block">Register</button>
                </div>
                <!-- form-group// -->
              </form>
            </article>
            <!-- card-body end .// -->
            <div class="border-top card-body text-center">
              Have an account?
              <router-link to="/login">Log In</router-link>
            </div>
          </div>
          <!-- card.// -->
        </div>
        <!-- col.//-->
      </div>
      <!-- row.//-->
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import LayoutDefault from "../layouts/LayoutDefault.vue";

export default {
  name: "register",
  components: {
    HelloWorld,
    LayoutDefault
  },
  created() {
    this.$emit("update:layout", LayoutDefault);
  }
};

</script>
