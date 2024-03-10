<template>
  <div>
    <h3>Book</h3>
    <div class="input-group mb-3">
      <input type="text" class="form-control" placeholder="Search book name" v-model="bookName">
      <div class="btn btn-outline-secondary" type="button" @click="loadBooks()" @keydown-enter="loadBooks()">Search
      </div>
    </div>

    <div class="row">
      <div class="card col-lg-3 col-md-6" v-for="book in books" :key="book.isbn" style="width: 27rem;">
        <img src="/images/book_image.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="bookName">書名:{{ book.bookName }}</h5>
          <p class="author">作者:{{ book.author }}</p>
          <p class="isbn">國際書號:{{ book.isbn }}</p>
          <p class="introduction">簡介:{{ book.introduction }}</p>
          <button class="btn btn-primary" @click="borrowBook(book.isbn)">借閱</button>
        </div>
      </div>
    </div>

    <vue-awesome-paginate class="mt-2" :total-items="totalPage" :items-per-page="3" :max-pages-shown="5" v-model="page"
      :on-click="onClickHandler" paginate-buttons-class="btn paginate-buttons" active-page-class="btn-info"
      back-button-class="back-btn" next-button-class="next-btn" />

  </div>

</template>

<script setup>
import axios from 'axios';
import { onMounted, ref, reactive } from 'vue';
import { getCookie } from "../../util/cookieUtils.js";
import { successMessage, errorMessage } from '../../script/alert.js';

const bookName = ref('')
const books = ref([]);
const userId = ref();
const page = ref(1);
const totalPage = ref(0);

const search = async () => {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_JAVAURL}/book/books`, {
      params: {
        bookName: bookName.value,
        page: 0,  // Assuming page is the current page number
        size: 3,          // Set the page size as needed
      },
    });
    console.log(response.data)
    books = response.data.content;
    totalPage.value = response.data.totalElements;
  } catch (error) {
    console.error('Error fetching books:', error);
  }
  page.value = 1
}

const loadBooks = async () => {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_JAVAURL}/book/books`, {
      params: {
        bookName: bookName.value,
        page: page.value-1,  // Assuming page is the current page number
        size: 3,          // Set the page size as needed
      },
    });

    books.value = response.data.content;
    totalPage.value = response.data.totalElements;
  } catch (error) {
    console.error('Error fetching books:', error);
  }
}

async function borrowBook(isbn) {
  try {
    const response = await axios.post(`${import.meta.env.VITE_API_JAVAURL}/user/borrow`, {
      isbn: isbn,
      userId: userId.value // 將用戶ID包含在請求中
    });
    if (response.data!== 0) {
      console.log(response.data);
      successMessage('書本借閱成功');
    } else {
      errorMessage('書本不得重複借閱，請稍後再試');
    }
  } catch (error) {
    console.error('Error borrowing book:', error);
    errorMessage('發生了一些錯誤，請稍後再試');
  }
}

onMounted(() => {
  userId.value = getCookie('userId');
  loadBooks();
});

const onClickHandler = (newPage) => {
  page.value = newPage;
  loadBooks();
};
</script>