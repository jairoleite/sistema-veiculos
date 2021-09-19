<template>
  <form @submit.prevent="submitForm">
    <div class="row q-mb-md">
      <q-banner class="bg-primary text-white" style="width: 100%; height: 80px">
        <span style="font-size: 30px; color: white"
          >Gerenciamento de Veículos
        </span>
      </q-banner>
    </div>
    <img
      class="icon-login"
      src="~/assets/car.png"
      style="margin-top: -98px"
      alt="porteiro"
    />
    <div class="row q-mb-md flex flex-center" style="margin-top: -120px">
      <q-input
        style="max-width: 350px"
        v-model="login"
        ref="login"
        lazy-rules
        outlined
        :autofocus="true"
        class="col"
        label="Login"
        stack-label
      />
    </div>
    <div class="row q-mb-md flex-center">
      <q-input
        v-model="password"
        ref="password"
        lazy-rules
        type="password"
        outlined
        class="col"
        label="Senha"
        stack-label
        style="max-width: 350px"
      />
    </div>
    <div class="row flex-center">
      <q-btn
        style="
          width: 350px;
          background: linear-gradient(
            98deg,
            rgba(7, 82, 99, 1) 100%,
            rgba(132, 216, 235, 1) 100%
          ) !important;
        "
        size="md"
        color="primary"
        type="submit"
        label="Entrar"
      />
    </div>
  </form>
</template>

<script>
import { SessionStorage } from "quasar";

export default {
  data() {
    return {
      login: "",
      password: "",
    };
  },
  methods: {
    async submitForm() {
      if (this.login == "") {
        this.notificationError("O login é obrigatório");
        return;
      }

      if (this.password == "") {
        this.notificationError("A senha é obrigatória");
        return;
      }

      if (this.login == "admin" && this.password == "123") {
        SessionStorage.set("@loggedIn", true);
        SessionStorage.set("@login", this.login);
        this.$router.push({ name: "home" });
      } else {
        SessionStorage.set("@loggedIn", false);
        this.$router.replace("/auth");
        this.notificationError("Usuário ou senha incorretos!");
      }
    },
    notificationError(text) {
      this.$q.notify({
        message: text,
        color: "red-8",
        icon: "info",
        actions: [{ icon: "close", color: "white" }],
      });
    },
  },
};
</script>

<style scoped>
.icon-login {
  margin-left: 100px;
  width: 300px;
}
</style>
