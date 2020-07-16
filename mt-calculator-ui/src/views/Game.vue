<template>
  <div class="game-page">
    <Menu />
    <v-card class="page-card" >
      <h3 class="greeting">MT-Calculator: Тренировка</h3>
      <v-list v-if="trainingSessionInfo" dense  :style="'background-size: contain; background-image:url(' + (trainingSessionInfo && trainingSessionInfo.background) + ')'">
        <GameItem  v-for="(e, index) in trainingSessionInfo.equations" v-bind:key = "e.id" v-bind:item="e" v-bind:index="index" @on-change = "onItemChange" />
        <v-list-item>
          <v-btn color="success" dark :loading="isChecking" @click="checkButtonClick">
            <v-icon>mdi-feather</v-icon>
            Отправить на проверку
          </v-btn>
        </v-list-item>
      </v-list>
    </v-card>
  </div>
</template>

<script>
import Menu from '@/components/Menu.vue'
import GameItem from '@/components/GameItem.vue'

import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'game',
  components: {
    Menu,
    GameItem,
  },

  data () {
      return {
        rules: {
          required: value => !!value || 'Нужно ввести хоть что-то.',
        },
      }
  }, 

  created() {
    this.setTrainingSessionInfo()
  },

  computed: {
    isLoading: {
      get () {
        return this.getIsLoading()
      }
    },

    isChecking: {
      get () {
        return false
      }
    },

    trainingSessionInfo: {
      get () {
        return this.getTrainingSessionInfo()
      }
    }
  },

  methods: {
    ...mapGetters(['getIsLoading', 'getTrainingSessionInfo']),
    ...mapMutations(['setTrainingSessionInfo', 'setTrainingAnswer', 'checkTrainingResults']),

    checkButtonClick () {
      this.checkTrainingResults()
    },

    onItemChange (value, index) {
      this.setTrainingAnswer({value: value, index: index})
    }

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