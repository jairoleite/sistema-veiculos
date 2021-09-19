<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title> Gerenciamento de veículos </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list>
        <q-item-label header> </q-item-label>

        <q-item clickable exact to="/">
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>

          <q-item-section>
            <q-item-label>Home</q-item-label>
            <q-item-label caption> listagem </q-item-label>
          </q-item-section>
        </q-item>

        <q-item clickable exact to="/cadastro">
          <q-item-section avatar>
            <q-icon name="toys" />
          </q-item-section>

          <q-item-section>
            <q-item-label>Cadastro de veículos</q-item-label>
            <q-item-label caption> adiciona veículo </q-item-label>
          </q-item-section>
        </q-item>

        <q-item clickable exact to="/estatisticas">
          <q-item-section avatar>
            <q-icon name="analytics" />
          </q-item-section>

          <q-item-section>
            <q-item-label>Estatísticas veículos</q-item-label>
            <q-item-label caption> visão elaborada </q-item-label>
          </q-item-section>
        </q-item>

        <q-item
          class="absolute-bottom"
          style="color: red"
          clickable
          v-ripple
          @click="clickSair"
        >
          <q-item-section avatar style="margin-left: 10px">
            <q-icon size="25px" name="logout" />
          </q-item-section>

          <q-item-section>Sair da Plataforma</q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { SessionStorage } from "quasar";

export default {
  name: "MainLayout",
  data() {
    return {
      leftDrawerOpen: false,
    };
  },
  methods: {
    toggleLeftDrawer() {
      this.leftDrawerOpen = !leftDrawerOpen;
    },
    //faz logout sistema
    clickSair() {
      SessionStorage.clear();
      this.$router.push({ name: "auth" });
    },
  },
};
</script>
