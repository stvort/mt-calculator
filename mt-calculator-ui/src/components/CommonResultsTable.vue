<template>
  <v-data-table
    :headers="commonResultsTableHeaders"
    :items="commonResults"
    :items-per-page="10"
    :loading="isLoading"
    class="common-results-tab"
    :footer-props="{
      disableItemsPerPage: true,
      showCurrentPage: false,
      itemsPerPageText: 'Строк на странице:',
      pageText: '{0}-{1} из {2}'
    }"
  ></v-data-table>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'common-results-table',
  data () {
      return {
        commonResultsTableHeaders: [
          { text: 'Пользователь', value: 'Пользователь' },
          { text: 'Последний счет', value: 'Счет последний' },
          { text: 'Максимальный счет', value: 'Счет максимальный' },
          { text: 'Счет средний за месяц', value: 'Счет средний за месяц' },
        ],
      }
  }, 

  created() {
    this.setCommonResults()
  },

  computed: {
    isLoggedIn: {
      get () {
        return this.getIsLoogedIn()
      }
    },   
    
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
    ...mapGetters(['getIsLoogedIn', 'getIsLoading', 'getCommonResults']),
    ...mapMutations(['setCommonResults'])
  }
}
</script>

<style scoped>
  .common-results-tab, .v-data-table {
    margin-top: 50px;
  }
</style>