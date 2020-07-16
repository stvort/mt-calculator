
import Vue from 'vue'
import Router from 'vue-router'
import CommonResults from './views/CommonResults.vue'
import SelfResults from './views/SelfResults.vue'
import Game from './views/Game.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'common-results',
      component: CommonResults
    },
    {
      path: '/self-results',
      name: 'self-results',
      component: SelfResults
    },
    {
      path: '/game',
      name: 'game',
      component: Game
    }
  ]
})