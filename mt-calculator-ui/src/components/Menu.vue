<template>
      <v-navigation-drawer absolute permanent dark width="300">
      <v-list-item>
        <v-list-item-avatar>
          <v-icon>mdi-menu</v-icon>
        </v-list-item-avatar>

        <v-list-item-title>Меню приложения</v-list-item-title>
      </v-list-item>

      <v-divider />

      <v-list dense>
        <v-list-item link to = "/">
          <v-list-item-icon><v-icon>mdi-badge-account-horizontal</v-icon></v-list-item-icon>
          <v-list-item-content><v-list-item-title>Общие результаты</v-list-item-title></v-list-item-content>
        </v-list-item>

        <v-list-item v-if="isLoggedIn" link to = "/self-results">
          <v-list-item-icon><v-icon>mdi-chart-bar</v-icon></v-list-item-icon>
          <v-list-item-content><v-list-item-title>Мои результаты</v-list-item-title></v-list-item-content>
        </v-list-item>

        <v-list-item v-if="isLoggedIn" link to="/game">
          <v-list-item-icon><v-icon>mdi-chess-queen</v-icon></v-list-item-icon>
          <v-list-item-content><v-list-item-title>Тренировка</v-list-item-title></v-list-item-content>
        </v-list-item>

        
        <v-list-item v-if="!isLoggedIn" class="login-list-item">
          <v-list-item-content>
            <v-list-item-title>Вход</v-list-item-title>
            <v-text-field 
              v-model="loginText" 
              :label="loginLabel" 
              :clearable=true 
            ></v-text-field>

            <v-text-field
              v-model="passwordText"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              :type="showPassword ? 'text' : 'password'"
              :label="passwordLabel"
              @click:append="showPassword = !showPassword"
          ></v-text-field>
          <v-btn color="success" dark :loading="isLogging" @click="loginButtonClick">
            <v-icon>mdi-login-variant</v-icon>
            Войти
          </v-btn>


          </v-list-item-content>
        </v-list-item>

        <v-list-item v-if="isLoggedIn" @click="logoutMenuItemlick">
          <v-list-item-icon><v-icon>mdi-logout-variant</v-icon></v-list-item-icon>
          <v-list-item-content><v-list-item-title>Выход</v-list-item-title></v-list-item-content>
        </v-list-item>

      </v-list>
    </v-navigation-drawer>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'app-menu',
  data () {
      return {
        loginLabel: 'Имя входа',
        passwordLabel: 'Пароль',
        
        loginText: '', 
        passwordText: '',

        showPassword: false,
        rules: {
          required: value => !!value || 'Нужно ввести хоть что-то.',
          min: v => v.length >= 8 || 'Минимум 8 символов'
        },
      }
  }, 

  computed: {
    isLoggedIn: {
      get () {
        return this.getIsLoggedIn()
      }
    },   
    
    isLogging: {
      get () {
        return this.getIsLogging()
      }
    },
  },

  methods: {
    ...mapGetters(['getIsLoggedIn', 'getIsLogging']),
    ...mapMutations(['login', 'logout']),
    
    loginButtonClick(){
      var info = {login: this.loginText, password: this.passwordText};
      this.login(info)
    },

    logoutMenuItemlick(){
      this.logout()
    }

    
  }
}
</script>

<style scoped>
  .login-list-item {
    margin-top: 50px;
  }
</style>