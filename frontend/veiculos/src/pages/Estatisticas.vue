<template>
  <div class="container">
    <header class="q-mb-md">
      <q-icon size="25px" name="analytics" /> Estatísticas
    </header>

    <main>
      <table class="customers" style="margin-bottom: 30px">
        <tr>
          <th>Vendas</th>
          <th style="width: 100px; text-align: center">Veículos</th>
        </tr>
        <tr>
          <td>Quantidade de veículos não vendidos</td>
          <td style="width: 100px; text-align: center">{{ qtdNaoVendido }}</td>
        </tr>
      </table>

      <table class="customers" style="margin-bottom: 30px">
        <tr>
          <th>Distribuição de veículos por década de fabricação</th>
          <th style="width: 100px; text-align: center">Veículos</th>
        </tr>
        <tr v-for="(decada, index) in veiculoDecadas" :key="index">
          <td>{{ decada.decada }}</td>
          <td style="width: 100px; text-align: center">
            {{ decada.qtdVeiculo }}
          </td>
        </tr>
      </table>

      <table class="customers">
        <tr>
          <th>Distribuição de veículos por fabricante</th>
          <th style="width: 100px; text-align: center">Veículos</th>
        </tr>
        <tr v-for="(fabricante, index) in veiculoFabricantes" :key="index">
          <td>{{ fabricante.marca }}</td>
          <td style="width: 100px; text-align: center">
            {{ fabricante.qtdVeiculo }}
          </td>
        </tr>
      </table>
    </main>
  </div>
</template>

<script>
//requisição padrão
import { requisicao } from "src/services/RequisicaoPadraoProxy";
//quasar
import { Loading } from "quasar";

export default {
  name: "Estatisticas",
  data() {
    return {
      veiculoDecadas: [],
      veiculoFabricantes: [],
      qtdNaoVendido: 0,
    };
  },
  methods: {
    async carregaTodos() {
      Loading.show();
      await this.carregaVeiculoNaoVendidos();
      await this.carregaVeiculoDecadas();
      await this.carregaVeiculoFabricantes();
      Loading.hide();
    },

    async carregaVeiculoNaoVendidos() {
      await requisicao({ method: "GET", url: "/veiculos/nao-vendidos" }).then(
        (resp) => {
          this.qtdNaoVendido = resp.data;
        }
      );
    },

    async carregaVeiculoDecadas() {
      await requisicao({ method: "GET", url: "/veiculos/decadas" }).then(
        (resp) => {
          this.veiculoDecadas = resp.data;
        }
      );
    },

    async carregaVeiculoFabricantes() {
      await requisicao({ method: "GET", url: "/veiculos/fabricantes" }).then(
        (resp) => {
          this.veiculoFabricantes = resp.data;
        }
      );
    },
  },
  created() {
    this.carregaTodos();
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