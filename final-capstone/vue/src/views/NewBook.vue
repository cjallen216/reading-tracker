<template>
    <div 
        :style="{backgroundImage: `url(${addBookImg})`, 
            backgroundSize: 'contain', 
            backgroundRepeat: 'no-repeat',
            BackgroundPosition: 'center' 
        }"
    >
      <new-book-form />  
    </div>
  
</template>

<script>
import newBookForm from "../components/NewBookForm.vue";
import addBookImg from "@/assets/add-book.png";

export default {
    components: {
        newBookForm
    },
    data(){
        return {
            addBookImg
        }
    }
}
</script>
<style>
</style>