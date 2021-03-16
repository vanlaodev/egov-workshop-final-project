import Vue from "vue";
import VueRouter from "vue-router";
import store from "../store";
import mainMenu from "../domain/main-menu";
const Dashboard = () => import("../views/Dashboard.vue");
const TakeInventory = () => import("../views/TakeInventory.vue");
const TakeInventoryDetail = () => import("../views/TakeInventoryDetail.vue");
const CreateInventoryForm = () => import("../views/CreateInventoryForm.vue");
const InventoryFormManagement = () =>
  import("../views/InventoryFormManagement.vue");
const PageNotFound = () => import("../views/PageNotFound.vue");
const PermissionDenied = () => import("../views/PermissionDenied.vue");
const EditInventoryForm = () => import("../views/EditInventoryForm.vue");
const ViewInventoryForm = () => import("../views/ViewInventoryForm.vue");
const Logs = () => import("../views/Logs.vue");

Vue.use(VueRouter);
const routes = [{
    path: "/dashboard",
    name: "Dashboard",
    alias: ["/"],
    component: Dashboard,
  },
  {
    path: "/takeInventory",
    name: "TakeInventory",
    component: TakeInventory,
  },
  {
    path: "/takeInventoryDetail",
    name: "TakeInventoryDetail",
    component: TakeInventoryDetail,
  },
  {
    path: "/createInventoryForm",
    name: "CreateInventoryForm",
    component: CreateInventoryForm,
  },
  {
    path: "/editInventoryForm/:id",
    name: "EditInventoryForm",
    component: EditInventoryForm,
  }, {
    path: "/viewInventoryForm/:id",
    name: "ViewInventoryForm",
    component: ViewInventoryForm,
  },
  {
    path: "/inventoryFormManagement",
    name: "InventoryFormManagement",
    component: InventoryFormManagement,
  },
  {
    path: "/logs",
    name: "Logs",
    component: Logs,
  },
  {
    path: "/permissionDenied",
    name: "PermissionDenied",
    component: PermissionDenied,
  },
  {
    path: "*",
    name: "PageNotFound",
    component: PageNotFound,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => {
    if (err.name !== "NavigationDuplicated") throw err;
  });
};

router.beforeEach((to, from, next) => {
  if (store.state.appInitialized) {
    const mainMenuItem = mainMenu.items.filter((i) => to.name === i.routeName);
    if (
      mainMenuItem.length > 0 &&
      !mainMenuItem[0].allowNavigate(
        store.state.loggedInUser == null ? [] : store.state.loggedInUser.roles
      )
    ) {
      next({
        name: "PermissionDenied",
        replace: true,
        query: {
          originalPath: from == null ? null : from.path,
        },
      });
      return;
    }
  }
  next();
});

export default router;