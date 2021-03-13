<template>
  <div class="book-container">
      
      <book-card v-bind:book="book" v-for="book in $store.state.books" v-bind:key="book.title" />
  </div>
</template>

<script>
import BookCard from '../components/BookCard'
import docsService from '../services/DocsService'
export default {
    name: 'reading-list',
    components: {
        BookCard
    },
    data(){
        return{
           
        }
    },

   beforeMount(){
       this.list()
 
    
    },
    methods: {
        list() {
             docsService.list().then(response => {
             if (response.status == 200){  this.$store.commit('LIST_EVERYTHING', response.data )}}
             )
              
    }
   
    }
   

}
</script>

<style>
.book-container {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>