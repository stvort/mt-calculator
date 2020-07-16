<template>
  <v-data-table
    :headers="selfResultsTableHeaders"
    :items="selfResults"
    :items-per-page="10"
    :loading="isLoading"
    class="self-results-tab"
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
  name: 'self-results-table',
  data () {
      return {
        selfResultsTableHeaders: [
          { text: 'Дата', value: 'Дата' },
          { text: 'Длительность прохождения', value: 'Длительность прохождения' },
          { text: 'Всего вопросов', value: 'Всего вопросов' },
          { text: 'Количество правильных ответов', value: 'Количество правильных ответов' },
          { text: 'Счет', value: 'Счет' },
        ],
      }
  }, 

  created() {
    this.setSelfResults()
  },

  computed: {
    isLoading: {
      get () {
        return this.getIsLoading()
      }
    },
    selfResults: {
      get () {
        return this.getSelfResults()
      }
    }
  },
  methods: {
    ...mapGetters(['getIsLoading', 'getSelfResults']),
    ...mapMutations(['setSelfResults'])
  }
}
</script>

<style scoped>
  .self-results-tab, .v-data-table {
    margin-top: 50px;
  }
</style>