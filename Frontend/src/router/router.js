import { createRouter, createWebHistory } from 'vue-router'

import Home from '@/views/router-views/Home.vue'
import NotFound from '@/views/router-views/NotFound.vue'
import Login from '@/views/router-views/Login.vue'
import Book from '@/views/router-views/Book.vue'
import Loan from '@/views/router-views/Loan.vue'
import Record from '@/views/router-views/Record.vue'
// 

const routes = [
  { name: "home", path: "/", component: Home },
  { name: "notfound", path: "/:pathMatch(.*)*", component: NotFound },
  { name: "login", path: "/login", component: Login },
  { name: "book", path: "/book", component: Book },
  { name: "loan", path: "/loan", component: Loan }
  // TODO need delete
  , { name: "record", path: "/record", component: Record }
  // 
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router