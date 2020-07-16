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

import { mapGetters, mapMutations } from 'vuex'
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

  created() {
    this.setCommonResults()
  },

  computed: {
    isLoading: {
      get () {
        return this.getIsLoading()
      }
    },

    commonResults: {
      get () {
        return this.getCommonResults()
      }
    }
  },

  methods: {
    ...mapGetters(['getIsLoading', 'getCommonResults']),
    ...mapMutations(['setCommonResults']),

  }
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