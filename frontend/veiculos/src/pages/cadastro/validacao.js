import notificacao from "src/utils/notificacao";

const marcas = ["Volkswagen",
  "Toyota",
  "Renault",
  "Nissan",
  "Hyundai",
  "Kia",
  "General Motors",
  "Honda",
  "Ford",
  "Fiat",
  "Citroen",
  "Peugeot",
  "Chery",
  "Jeep",
  "Chevrolet",
  "Mercedes-Benz",
  "Suzuki",
  "Jaguar",
  "Ferrari",
  "Audi"]

export default (veiculo) => {
  if (!veiculo.veiculo || veiculo.veiculo.length == 0) {
    notificacao("erro", "Digite o veículo!");
    return false;
  }

  if (!veiculo.marca || veiculo.marca.length == 0) {
    notificacao("erro", "Digite a marca!");
    return false;
  }

  if (!veiculo.ano) {
    notificacao("erro", "Digite o ano!");
    return false;
  }

  if (!veiculo.descricao) {
    notificacao("erro", "Digite uma descrição!");
    return false;
  }

  if (!marcas.includes(veiculo.marca)) {
    notificacao("erro", "Digite o nome da marca corrtamente para poder salvar!");
    return false;
  }

  return true;
}