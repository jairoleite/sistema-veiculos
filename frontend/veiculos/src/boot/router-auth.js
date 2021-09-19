import { SessionStorage } from "quasar";

export default ({ router }) => {
  router.beforeEach((to, from, next) => {
    let loggedIn = SessionStorage.getItem("@loggedIn");
    if (!loggedIn && to.path !== "/auth") {
      next("/auth");
    } else {
      next();
    }
  });
};
