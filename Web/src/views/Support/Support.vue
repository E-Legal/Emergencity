<template>
  <div
    style="background: url('/assets/white3.png') repeat center center fixed; background-position: center; overflow: auto;"
  >
    <div class="ticket-form">
      <form>
        <input
          type="text"
          class="todo-input"
          placeholder="Titre du ticket"
          v-model="newTitleTodo"
          @keyup.enter="addTodo"
        />
        <input
          type="text"
          class="todo-input"
          placeholder="Description du ticket"
          v-model="newContentTodo"
          @keyup.enter="addTodo"
        />
        <div class="ticket-service">
          <p>Service à contacté :</p>
          <input
            type="radio"
            id="api"
            value="quentin.heraud@epitech.eu"
            v-model="checkedNames"
            @keyup.enter="addTodo"
          />
          <label for="api">Api</label>
          <input
            type="radio"
            id="web"
            value="eliott.legal@epitech.eu"
            v-model="checkedNames"
            @keyup.enter="addTodo"
          />
          <label for="web">Web</label>
          <input
            type="radio"
            id="mobile"
            value="yanis.abba@epitech.eu"
            v-model="checkedNames"
            @keyup.enter="addTodo"
          />
          <label for="mobile">Mobile</label>
        </div>
      </form>
    </div>
    <div class="support-ticket">
      <h2>Vos ticket en cours :</h2>
      <b-card>
        <transition-group
          name="fade"
          enter-active-class="animated fadeInUp"
          leave-active-class="animated fadeOutDown"
        >
          <div
            v-for="(todo, index) in todosFiltered"
            :key="todo.id"
            class="todo-item"
          >
            <div class="todo-item-left">
              <b-card-title>
                <div>Titre: {{ todo.title }}</div>
              </b-card-title>
              <b-card-text>
                <div
                  v-if="!todo.editing"
                  @dblclick="editContentTodo(todo)"
                  class="todo-item-label"
                  :class="{ completed: todo.completed }"
                >
                  Description: {{ todo.content }}
                </div>
                <input
                  v-else
                  class="todo-item-edit"
                  type="text"
                  v-model="todo.content"
                  @blur="doneContentEdit(todo)"
                  @keyup.enter="doneContentEdit(todo)"
                  @keyup.esc="cancelContentEdit(todo)"
                  v-focus
                />
                <div class="todo-item-label">
                  Assigné à : {{ todo.assigned }}
                </div>
              </b-card-text>
            </div>
            <input type="checkbox" v-model="todo.completed" />
            <div class="remove-item" @click="removeTodo(index)">
              &times;
            </div>
          </div>
        </transition-group>
      </b-card>
    </div>
    <div>{{ remaining }} Ticket en cours</div>
  </div>
</template>

<script>
import LayoutDefault from "../../layouts/LayoutDefault.vue";
export default {
  name: "todo-list",
  data() {
    return {
      newTitleTodo: "",
      newContentTodo: "",
      idForTodo: 3,
      checkedNames: "",
      beforeEditCache: "",
      filter: "all",
      todos: [
        {
          id: 1,
          title: "Problème Api",
          content: "Impossible d'ajouter un feu sur la map",
          assigned: "quentin.heraud@epitech.eu",
          completed: false,
          editing: false
        },
        {
          id: 2,
          title: "Problème Mobile",
          content: "Mon GPS m'indique de mauvaise information",
          assigned: "yanis.abba@epitech.eu",
          completed: false,
          editing: false
        }
      ]
    };
  },
  computed: {
    remaining() {
      return this.todos.filter(todo => !todo.completed).length;
    },
    todosFiltered() {
      if (this.filter === "all") {
        return this.todos;
      } else if (this.filter === "active") {
        return this.todos.filter(todo => !todo.completed);
      } else if (this.filter === "completed") {
        return this.todos.filter(todo => todo.completed);
      }
      return this.todos;
    }
  },
  directives: {
    focus: {
      inserted: function(el) {
        el.focus();
      }
    }
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
  },
  created() {
    this.$emit("update:layout", LayoutDefault);
  },
  methods: {
    addTodo() {
      if (
        this.newTitleTodo.trim().length === 0 &&
        this.newContentTodo.trim().length === 0
      ) {
        return;
      }
      this.todos.push({
        id: this.idForTodo,
        title: this.newTitleTodo,
        content: this.newContentTodo,
        assigned: this.checkedNames,
        completed: false,
        editing: false
      });
      window.open(
        "mailto:" +
          this.checkedNames +
          "?subject=" +
          this.newTitleTodo +
          "&body=" +
          this.newContentTodo
      );
      this.newTitleTodo = "";
      this.newContentTodo = "";
      this.checkedNames = "";
      this.idForTodo++;
    },
    editContentTodo(todo) {
      this.beforeEditCache = todo.content;
      todo.editing = true;
    },
    doneContentEdit(todo) {
      if (todo.content.trim() === "") {
        todo.content = this.beforeEditCache;
      }
      window.open(
        "mailto:" +
          todo.assigned +
          "?subject=" +
          todo.title +
          "&body=" +
          todo.content
      );
      todo.editing = false;
    },
    cancelContentEdit(todo) {
      todo.content = this.beforeEditCache;
      todo.editing = false;
    },
    removeTodo(index) {
      this.todos.splice(index, 1);
    }
  }
};
</script>

<style scoped lang="scss">
@import url("https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css");
.card {
  border: none;
  width: 300px;
  height: auto;
}
.ticket-form {
  padding: 60px 0;
}
.ticket-form input {
  text-align: center;
}
.ticket-form form {
  margin: 0 auto;
  max-width: 320px;
}
.support-ticket {
  padding: 60px;
}
.ticket-service input,
label {
  padding-right: 10px;
}
</style>
