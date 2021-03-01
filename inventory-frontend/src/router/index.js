import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import mainMenu from '../domain/main-menu'
import Dashboard from '../views/Dashboard.vue'
import Inquiry from '../views/Inquiry.vue'
import TakeInventory from '../views/TakeInventory.vue'
import TakeInventoryDetail from '../views/TakeInventoryDetail.vue'
import CreateInventoryForm from '../views/CreateInventoryForm.vue'
import InventoryFormManagement from '../views/InventoryFormManagement.vue'
import PageNotFound from '../views/PageNotFound.vue'
import PermissionDenied from '../views/PermissionDenied.vue'

Vue.use(VueRouter)
const routes = [{
  path: '/dashboard',
  name: 'Dashboard',
  alias: ['/'],
  component: Dashboard
}, {
  path: '/inquiry',
  name: 'Inquiry',
  component: Inquiry,
}, {
  path: '/takeInventory',
  name: 'TakeInventory',
  component: TakeInventory
}, {
  path: '/takeInventoryDetail',
  name: 'TakeInventoryDetail',
  component: TakeInventoryDetail
}, {
  path: '/createInventoryForm',
  name: 'CreateInventoryForm',
  component: CreateInventoryForm
}, {
  path: '/inventoryFormManagement',
  name: 'InventoryFormManagement',
  component: InventoryFormManagement
}, {
  path: '/permissionDenied',
  name: 'PermissionDenied',
  component: PermissionDenied
}, {
  path: '*',
  name: 'PageNotFound',
  component: PageNotFound
}]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => {
    if (err.name !== 'NavigationDuplicated') throw err
  });
}

router.beforeEach((to, from, next) => {
  if (store.state.appInitialized) {
    const mainMenuItem = mainMenu.items.filter(
      (i) => to.name === i.routeName
    );
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
          originalPath: from == null ? null : from.path
        }
      });
      return;
    }
  }
  next();
});

export default router