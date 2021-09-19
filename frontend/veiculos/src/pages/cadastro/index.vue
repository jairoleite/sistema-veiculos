<template>
  <div class="container">
    <header><q-icon size="25px" name="toys" /> Cadastro de veículo</header>

    <main>
      <div class="q-mt-md row">
        <div class="q-mr-sm col">
          <q-input outlined v-model="veiculo.veiculo" label="Veículo" />
        </div>
        <div class="q-mr-sm col">
          <q-input outlined v-model="veiculo.marca" label="Marca" />
        </div>
        <div class="col-2">
          <q-input type="number" outlined v-model="veiculo.ano" label="Ano" />
        </div>
      </div>

      <div class="q-mt-sm row">
        <div class="q-mr-sm col">
          <q-input
            type="textarea"
            outlined
            v-model="veiculo.descricao"
            label="Descrição"
          />
        </div>
        <div class="q-mr-sm col-4">
          <q-toggle v-model="veiculo.vendido" label="Vendido" />
        </div>
      </div>

      <div class="q-mt-md row">
        <div class="q-mr-sm col">
          <q-btn
            color="primary"
            icon="save"
            label="Salvar cadastro"
            @click="clickSalvar"
          />
        </div>
      </div>
    </main>
  </div>
</template>

<script>
//requisição padrão
import { requisicao } from "src/services/RequisicaoPadraoProxy";
//quasar
import { SessionStorage } from "quasar";
//notificação
import notificacao from "src/utils/notificacao";
//validação
import validacao from "./validacao";

export default {
  name: "Cadastro",
  data() {
    return {
      veiculo: {
        id: null,
        veiculo: "",
        marca: "",
        ano: null,
        descricao: "",
        vendido: false,
      },
    };
  },
  methods: {
    async clickSalvar() {
      let veiculo = Object.assign({}, this.veiculo);

      if (!validacao(veiculo)) return;

      // console.log(veiculo)

      if (veiculo.id) {
        await this.altera(veiculo);
      } else {
        await this.insere(veiculo);
      }
    },

    async insere(veiculo) {
      await requisicao({
        method: "POST",
        url: "/veiculos",
        data: veiculo,
      }).then(() => {
        notificacao("ok", "Veículo salvo com sucesso!");
        this.$router.push({ name: "home" });
      });
    },

    async altera(veiculo) {
      await requisicao({
        method: "PUT",
        url: `/veiculos/${veiculo.id}`,
        data: veiculo,
      }).then(() => {
        notificacao("ok", "Veículo alterado com sucesso!");
        this.$router.push({ name: "home" });
      });
    },

    carregaVeiculo() {
      let veiculo = JSON.parse(SessionStorage.getItem("@veiculo") || null);
      if (veiculo) {
        this.veiculo = veiculo;
      }
    },
  },
  created() {
    this.carregaVeiculo();
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
</style>