<template>
  <div></div>
</template>

<script>
export default {
name:'dashboard'
}
</script>

<style>
.home{



display: grid;
grid-template-columns: 1fr 1fr 1fr 1fr;
gap: 15px;
grid-template-areas:

"hours-read hours-read current-read current-read"
"running-clock running-clock start-stop-button start-stop-button" 
"books-read books-read next-achieve next achieve"
"new-book new-book wish-list wish-list";

align-items: center;

}
</style>