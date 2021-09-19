import { Notify } from 'quasar'

// tipo => ok, erro, aviso

export default (tipo, mensagem) => {
  Notify.create({
    progress: true,
    message: mensagem,
    color: tipo === 'ok' ? 'positive' : tipo === 'erro' ? 'negative' : tipo === 'aviso' ? 'warning' : 'info',
    multiLine: true,
    icon: tipo === 'ok' ? 'sentiment_satisfied_alt' : tipo === 'erro' ? 'sentiment_very_dissatisfied' : tipo === 'aviso' ? 'warning' : 'info',
    actions: [
      { label: 'Fechar', color: 'white', handler: () => { /* ... */ } }
    ]
  })
}
