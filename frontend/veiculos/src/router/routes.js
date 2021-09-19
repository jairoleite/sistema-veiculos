
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        name: 'home',
        component: () => import('pages/Index.vue')
      },
      {
        path: '/cadastro',
        name: 'veiculo',
        component: () => import('src/pages/cadastro/index.vue')
      },
      {
        path: '/estatisticas',
        name: 'estatistica',
        component: () => import('src/pages/Estatisticas.vue')
      }
    ]
  },
  {
    path: "/auth",
    name: "auth",
    component: () => import("layouts/LoginLayout.vue"),
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
