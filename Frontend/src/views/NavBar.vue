<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary " data-bs-theme="dark">
    <div class="container-fluid">
      <RouterLink class="navbar-brand" :to="{ name: 'home' }">Vue</RouterLink>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
        aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarScroll">
        <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
          <li v-show=!userId class="nav-item">
            <RouterLink class="nav-link active" :to="{ name: 'login' }" >登入</RouterLink>
          </li>
          <li v-show=userId class="nav-item">
            <RouterLink class="nav-link active" :to="{ name: 'login' }"  @click = logout()>登出</RouterLink>
          </li>
          <li v-show=!userId class="nav-item">
            <RouterLink class="nav-link active" :to="{ name: 'book' }">書籍一覽</RouterLink>
          </li>
          <li v-show=userId class="nav-item">
            <RouterLink class="nav-link active" :to="{ name: 'loan' }">書籍借閱</RouterLink>
          </li>
          <!-- TODO need delete -->
          <li class="nav-item">
            <RouterLink class="nav-link active" :to="{ name: 'record' }">我的借書紀錄</RouterLink>
          </li>
          <!--  -->
        </ul>
      </div>
    </div>
  </nav>
</template>
<script setup>
import { ref,onMounted } from 'vue';
import { clearCookie, getCookie } from "../util/cookieUtils.js";
import {useRouter} from 'vue-router';
import { successMessage } from '@/script/alert.js'; 
const userId = ref();
const router = useRouter();
onMounted(() => {
  console.log(userId.value)
  userId.value = getCookie('userId');
})
function logout() {
  clearCookie('userId');
  clearCookie('userName');
  userId.value='';
  router.push('/');
  successMessage('登出成功');
  setTimeout(() => {
          window.location.reload();
        }, 1200);
}


</script>