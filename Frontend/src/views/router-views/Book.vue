<template>
  <div>
    <h3>Book</h3>
    <div class="input-group mb-3">
      <input type="text" class="form-control" placeholder="Search book name" v-model="data.bookName">
      <button class="btn btn-outline-secondary" type="button" @click="loadBooks()">Search</button>
    </div>
    <div class="row" >
      <div class="card col-lg-3 col-md-6" v-for="book in books.content" :key="book.isbn" style="width:27rem ;">
        <img src="/images/book_image.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="bookName">書名:{{ book.bookName }}</h5>
          <p class="author">作者:{{ book.author }}</p>
          <p class="isbn">國際書號:{{ book.isbn }}</p>
          <p class="introduction">簡介:{{ book.introduction }}</p>
        </div>
      </div>
    </div>
  </div>
  <vue-awesome-paginate class="mt-2" :total-items="records" :items-per-page="10" :max-pages-shown="5" v-model="page"
                :on-click="onClickHandler"
                paginate-buttons-class="btn paginate-buttons"
                active-page-class="btn-info"
                back-button-class="back-btn"
                next-button-class="next-btn" />
</template>


<script setup>
import axios from 'axios';
import { onMounted, reactive, ref } from 'vue';

const books = reactive({});

const data = ref({
  bookName: '',
  page: 1,
  size: 3,
});

const loadBooks = async () => {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_JAVAURL}/book/books`, {
      params: {
        bookName: data.value.bookName,
        page: data.value.page - 1,
        size: data.value.size,
      },
    });

    books.content = response.data.content;
  } catch (error) {
    console.error('Error fetching books:', error);
  }
};

onMounted(() => loadBooks()); 
</script>