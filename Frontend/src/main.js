import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router.js'
import VueAwesomePaginate from "vue-awesome-paginate";
import "vue-awesome-paginate/dist/style.css";
const app = createApp(App)
app.use(router).use(VueAwesomePaginate)
app.mount('#app')


