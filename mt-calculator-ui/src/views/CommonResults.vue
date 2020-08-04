<template>
  <div class="common-results-page">
    <Menu />
    <v-card class="page-card" >
      <h3 class="greeting">MT-Calculator: Таблица результатов</h3>
      <CommonResultsTable />
    </v-card>
  </div>
</template>

<script>
import Menu from '@/components/Menu.vue'
import CommonResultsTable from '@/components/CommonResultsTable.vue'

export default {
  name: 'common-results',
  components: {
    CommonResultsTable,
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