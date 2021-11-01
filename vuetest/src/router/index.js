import { createRouter, createWebHistory } from 'vue-router'
import Login from "../pages/Login";
import About from "../pages/About";
import Collections from "../pages/Collections";
import Registration from "../pages/Registration";
import UserCollections from "../pages/UserCollections";

const routes = [
  {
    path: '/',
    component: Collections
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/about',
    component: About
  },
  {
    path: '/registration',
    component: Registration
  },
  {
    path: '/:login',
    component: UserCollections
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router;
