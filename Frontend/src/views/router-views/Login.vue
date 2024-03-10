<template>
  <h2>Login</h2>
  <div v-if="showLogin">
    <!-- 登入頁面 -->
    <form @submit.prevent="login()">
      <div class="form-floating mb-3">
        <input type="text" class="form-control" 
        id="floatingPhoneNumber" placeholder="PhoneNumber" v-model="loginPhone">
        <label for="floatingPhoneNumber">PhoneNumber</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" 
        id="floatingPassword" placeholder="Password" v-model="password">
        <label for="floatingPassword">Password</label>
      </div>
      <!-- 按鈕 -->
      <div class=" justify-content-between d-flex mt-3">
        <div class="col-2">
          <button class="btn btn-secondary">登入</button>
        </div>
        <div class="col-2">
          <div class="btn btn-secondary" @click="showRegisterPage()">點此註冊</div>
        </div>
      </div>
      <!-- /按鈕 -->
    </form>
    <!-- /登入頁面 -->
  </div>
  <div v-else>
    <!-- 註冊頁面 -->
    <form @submit.prevent="register()">
      <div class="form-floating mb-3">
        <input type="text" class="form-control" 
        id="floatingPhoneNumber" placeholder="PhoneNumber" v-model="resgisterPhone">
        <label for="floatingPhoneNumber">PhoneNumber</label>
      </div>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" 
        id="floatingUserName" placeholder="UserName" v-model="userName">
        <label for="floatingUserName">UserName</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" 
        id="floatingPassword" placeholder="Password" v-model="registerPassword">
        <label for="floatingPassword">Password</label>
      </div>
      <!-- 按鈕 -->
      <div class=" justify-content-between d-flex mt-3">
        <div class="col-2">
          <button class="btn btn-secondary">註冊</button>
        </div>
        <div class="col-2">
          <div class="btn btn-secondary" @click="showLoginPage()">返回登入</div>
        </div>
      </div>
      <!-- /按鈕 -->
    </form>
    <!-- /註冊頁面 -->
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue'
import { successMessage,errorMessage } from '@/script/alert';
import { setCookie } from '../../util/cookieUtils.js';
import router from '@/router/router.js';
const showLogin = ref(true)

function showRegisterPage() {
  showLogin.value = false
}

function showLoginPage() {
  showLogin.value = true
}

// TODO 雙向綁定表單欄位(共5個)
const resgisterPhone = ref('');
const password = ref('');
const userName = ref('');
const loginPhone = ref('');
const registerPassword = ref('');

async function login() {
  try {
    if (!loginPhone.value.trim() || !password.value.trim()) {
      errorMessage('手機號碼或密碼不能為空');
      return;
    } else if (loginPhone.value.trim().length !==10) {
      errorMessage('手機號碼長度不能小於或超過 10 個字符');
      return;
    }
    const response = await axios.post(`${import.meta.env.VITE_API_JAVAURL}/user/login`, {
      phoneNumber: loginPhone.value,
      password: password.value,
    });
    console.log(response);
    if (response.status === 200) {
      successMessage('登入成功');
      setCookie('userId', response.data.userId, 10);
      setCookie('userName', response.data.userName, 10);
      router.push({ path: '/' });
      setTimeout(() => {
        window.location.reload();
      }, 1200);
    } else if (response.status === 400){
      errorMessage('登入失敗，請檢查手機號碼或密碼是否正確');
    }
  } catch (error) {
    console.error('An error occurred:', error);
    errorMessage('登入失敗，請檢查手機號碼或密碼是否正確');
  }
}

async function register() {
  try {
    if (!userName.value.trim() || !resgisterPhone.value.trim() || !registerPassword.value.trim()) {
      errorMessage('用戶名、手機號碼或密碼不能為空');
      return;
    } else if (resgisterPhone.value.trim().length !== 10) {
      errorMessage('手機號碼長度不能小於或超過 10 個字符');
      return;
    }
    const response = await axios.post(`${import.meta.env.VITE_API_JAVAURL}/user/register`, {
      userName: userName.value,
      phoneNumber: resgisterPhone.value,
      password: registerPassword.value,
    });
    if (response.status === 201) {
      successMessage('註冊成功');
      router.push({ path: '/' });
      setTimeout(() => {
        window.location.reload();
      }, 1200);
    } else if (response.status === 400) {
      errorMessage('註冊失敗，請檢查手機號碼是否已被註冊');
    }
  } catch (error) {
    console.error('An error occurred:', error);
    errorMessage('註冊失敗，請檢查手機號碼是否已被註冊');
  }
}
</script>