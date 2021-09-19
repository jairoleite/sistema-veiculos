<template>
  <div class="container">
    <header class="q-mb-md">
      <q-icon size="25px" name="list" /> Listagem dos veículos
    </header>
    <div class="btn-new">
      <q-btn
        class="q-mb-sm"
        dense
        color="primary"
        icon="add_circle"
        label="adicionar veículo"
        @click="clickNovoCadastro"
      />
    </div>

    <table class="customers">
      <tr>
        <th>Veículo</th>
        <th>Marca</th>
        <th>Ano</th>
        <th style="width: 100px; text-align: center">Vendido</th>
        <th>Descrição</th>
        <th></th>
      </tr>
      <tr v-for="(veiculo, index) in veiculos" :key="index">
        <td>{{ veiculo.veiculo }}</td>
        <td>{{ veiculo.marca }}</td>
        <td>{{ veiculo.ano }}</td>
        <td style="width: 100px; text-align: center">
          {{ veiculo.vendido ? "SIM" : "NÃO" }}
        </td>
        <td>{{ veiculo.descricao }}</td>
        <td style="width: 260px; text-align: center">
          <q-btn
            style="width: 110px"
            class="q-mr-sm"
            dense
            color="green"
            icon="edit"
            label="editar"
            @click="clickEditar(veiculo)"
          />
          <q-btn
            style="width: 110px"
            dense
            color="red"
            icon="delete"
            label="excluir"
            @click="clickExcluir(veiculo)"
          />
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
//requisição padrão
import { requisicao } from "src/services/RequisicaoPadraoProxy";
//notificação
import notificacao from "src/utils/notificacao";
//quasar
import { Loading, SessionStorage } from "quasar";

export default {
  name: "PageIndex",
  data() {
    return {
      veiculos: [],
    };
  },
  methods: {
    async carregaVeiculos() {
      Loading.show();
      await requisicao({ method: "GET", url: "/veiculos" }).then((resp) => {
        this.veiculos = resp.data;
      });

      Loading.hide();
    },
    clickEditar(veiculo) {
      SessionStorage.set("@veiculo", JSON.stringify(veiculo));
      Loading.show();
      setTimeout(() => {
        this.$router.push({ name: "veiculo" });
        Loading.hide();
      }, 300);
    },
    async clickExcluir(veiculo) {
      Loading.show({ message: "excluindo veículo..." });

      await requisicao({
        method: "DELETE",
        url: `/veiculos/${veiculo.id}`,
      })
        .then(() => {
          notificacao("ok", "Veículo excluído com sucesso!");
          this.carregaVeiculos();
        })
        .catch(() => Loading.hide());

      Loading.hide();
    },
    clickNovoCadastro() {
      SessionStorage.remove("@veiculo");
      this.$router.push({ name: "veiculo" });
    },
  },

  created() {
    this.carregaVeiculos();
  },
};
</script>

<style scoped>
.container {
  margin: 25px;
}

.container > header {
  font-size: 18px;
}

.btn-new {
  display: flex;
  flex-direction: row;
  justify-content: end;
}

.customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

.customers td,
.customers th {
  border: 1px solid #ddd;
  padding: 6px;
  color: #2b2a42;
  font-size: 14px;
  font-weight: 600;
}

.customers tr {
  font-size: 13px;
}

.customers tr:nth-child(even) {
  background-color: #f2f2f2;
}

.customers tr:hover {
  background-color: #ddd;
}

.customers th {
  padding-top: 8px;
  padding-bottom: 8px;
  text-align: left;
  background-color: #1976d2;
  color: white;
}
</style>
