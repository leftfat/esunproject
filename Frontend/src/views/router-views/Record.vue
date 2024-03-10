<template>
  <div>
    <h2 class="mb-3">我的借閱紀錄</h2>
    <table class="table table-striped">
      <thead>
    <tr>
      <th scope="col">編號</th>
      <th scope="col">國際書號</th>
      <th scope="col">書名</th>
      <th scope="col">借書日期</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
      <tbody>
        <tr v-if="!records">
          <td colspan="2">
            <h4>無書本借閱紀錄</h4>
          </td>
        </tr>
        <template v-else>
          <tr v-for="record in records" :key="record.id">
            <td>{{ record.inventoryId }}</td>
            <td>{{ record.isbn }}</td>
            <td>{{ record.bookName }}</td>
            <td>{{ record.borrowTime }}</td>
            <td><button @click="returnBook(record)">還書</button></td>
          </tr>
        </template>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { getCookie } from '../../util/cookieUtils'
import { successMessage, errorMessage } from '../../script/alert'

const userId = ref('')
const records = ref([])

onMounted(async () => {
  userId.value = getCookie('userId')
  await getRecords();
})

async function getRecords() {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_JAVAURL}/user/borrowingRecord`, {
      params: {
        userId: userId.value
      }
    });
    records.value = response.data;
    console.log(response.data);
  } catch (error) {
    console.error('Error fetching records:', error);
  }
}

async function returnBook(record) {
  try {
    const response = await axios.post(`${import.meta.env.VITE_API_JAVAURL}/user/return`, {
      userId: userId.value,
      isbn: record.isbn
    });
    if (response.status === 200) {
      successMessage('書本還書成功');
      getRecords();
    }
  } catch (error) {
    errorMessage('書本還書失敗');
  }
}
</script>