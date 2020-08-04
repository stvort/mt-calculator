<template>
  <div class="self-results-page">
    <Menu />
    <v-card class="page-card" >
      <h3 class="greeting">MT-Calculator: Мои результаты</h3>
      <SelfResultsTable />
    </v-card>
  </div>
</template>

<script>
import Menu from '@/components/Menu.vue'
import SelfResultsTable from '@/components/SelfResultsTable.vue'

export default {
  name: 'self-results',
  components: {
    SelfResultsTable,
    Menu,
  },

  data () {
      return {
      }
  }, 
}
</script>

<style scoped>
  h3 {
    padding-top: 10px;
    color: salmon;
  }
  .page-card {
    margin-left: 300px;
  }
</style>