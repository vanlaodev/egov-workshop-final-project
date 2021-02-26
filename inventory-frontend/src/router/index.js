import Vue from 'vue'
import VueRouter from 'vue-router'
import Dashboard from '../views/Dashboard.vue'
import TakeInventory from '../views/TakeInventory.vue'
import CreateInventoryForm from '../views/CreateInventoryForm.vue'
import InventoryFormManagement from '../views/InventoryFormManagement.vue'
import PageNotFound from '../views/PageNotFound.vue'

Vue.use(VueRouter)

const routes = [{
  path: '/',
  name: 'Dashboard',
  component: Dashboard
}, {
  path: '/dashboard',
  name: 'Dashboard',
  component: Dashboard
}, {
  path: '/takeInventory',
  name: 'TakeInventory',
  component: TakeInventory
}, {
  path: '/createInventoryForm',
  name: 'CreateInventoryForm',
  component: CreateInventoryForm
}, {
  path: '/inventoryFormManagement',
  name: 'InventoryFormManagement',
  component: InventoryFormManagement
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

export default router