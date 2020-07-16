import Vue from 'vue'
import Vuex from 'vuex'
import {requestToken, sendTrainingResults, loadToken, requestData} from './backend'
import router from '../router'

Vue.use(Vuex)

const urlDashboardAll = 'https://mt-calculator-dashboard.herokuapp.com/api/v1/dashboard/all'
const urlDashboardSelf = 'https://mt-calculator-dashboard.herokuapp.com/api/v1/dashboard/self'
const urlTrainingStart = 'https://91i.ru/api/v1/start'
////const urlTrainingEnd = 'http://localhost:8080/api/v1/end'
const urlTrainingEnd = 'https://91i.ru/api/v1/end'
//const urlDashboardSelf = 'http://localhost:8080/api/v1/dashboard/self'


/*
{
    "session_id": "d78ba523-c6c7-481b-baf3-df3d81e0f8a1",
    "user_id": 2,
    "background": "https://picsum.photos/1000/400",
    "equations": [
        {
            "id": 33465,
            "first_number": 8,
            "second_number": 11,
            "type": "m"
        }
    }
}    


https://91i.ru/api/v1/end

// Пост
{
  "session_id": "39958f17-80b3-47d5-9ecd-9bac65cb47c6",
  "equation_results": [
    {
      "id": 33168,
      "result": 40
    }
  ]
}

// Ответ
{
  "session_id": "39958f17-80b3-47d5-9ecd-9bac65cb47c6",
  "client_time": 24,
  "client_score": 3,
  "client_result_answers": [
    {
      "id": 33168,
      "result": 40,
      "success": true
    }
}    

*/

//const sessionInfoStub = {"session_id":"7a4f5771-772f-4d25-8a59-50fc42442d0f","user_id":2,"background":"https://picsum.photos/1000/400","equations":[{"id":32771,"first_number":1,"second_number":3,"type":"m"},{"id":43452,"first_number":11,"second_number":1,"type":"d"},{"id":33073,"first_number":4,"second_number":11,"type":"m"},{"id":33071,"first_number":4,"second_number":9,"type":"m"},{"id":42766,"first_number":4,"second_number":1,"type":"d"},{"id":42963,"first_number":6,"second_number":2,"type":"d"},{"id":33659,"first_number":10,"second_number":8,"type":"m"},{"id":43060,"first_number":7,"second_number":1,"type":"d"},{"id":33266,"first_number":6,"second_number":8,"type":"m"},{"id":32974,"first_number":3,"second_number":10,"type":"m"}]}



export default new Vuex.Store({
    state: {
        isLoggedIn: loadToken(),
        isLogging: false,
        isLoading: false,
        isChecking: false,

        currentLogin: '',
        commonResults: [],
        selfResults: [],
        trainingSessionInfo: undefined//sessionInfoStub,
    },
    getters: {
        getIsLoggedIn (store) {
            return store.isLoggedIn
        },

        getIsLogging (store) {
            return store.isLogging
        },

        getIsLoading(store) {
            return store.isLoading
        },       
        
        getIsChecking(store) {
            return store.isChecking
        },
        
        getCommonResults (store) {
            return store.commonResults
        },
        
        getSelfResults (store) {
            return store.selfResults
        },
        
        getTrainingSessionInfo (store) {
            return store.trainingSessionInfo
        },
    },
    mutations: {
        setCommonResults(state) {
            state.isLoading = true
            requestData(urlDashboardAll).then(data => {
                state.commonResults = data
                state.isLoading = false
            })
        },

        setSelfResults(state) {
            state.isLoading = true
            requestData(urlDashboardSelf).then(data => {
                state.selfResults = data
                state.isLoading = false
            }).catch(() => router.push('/'))
        },

        setTrainingSessionInfo(state) {
            if (state.trainingSessionInfo && state.trainingSessionInfo.equations && state.trainingSessionInfo.equations.length > 0) {
                return
            }

            state.isLoading = true
            requestData(urlTrainingStart).then(data => {
                state.trainingSessionInfo = data
                state.isLoading = false
            }).catch(() => router.push('/'))
        },

        setTrainingAnswer(state, answerData) {
            console.log("equation: " + state.trainingSessionInfo.equations[answerData.index] + ', value: ' + answerData.value)
            state.trainingSessionInfo.equations[answerData.index].result = answerData.value
        },

        checkTrainingResults(state) {
            state.isChecking = true

            const si = state.trainingSessionInfo
            const results = {session_id: si.session_id, equation_results:[]}
            si.equations.forEach(e => results.equation_results.push({id: e.id, result: ('result' in e? parseInt(e.result): -1)}))
            sendTrainingResults(urlTrainingEnd, results).then(data => {
                console.log(data)
                state.isChecking = false
                state.trainingSessionInfo = undefined
                router.push('/self-results')
            })
        },

        login(state, loginInfo) {
            if (loginInfo.loginText !== state.currentLogin){
                state.trainingSessionInfo = undefined
            }

            state.currentLogin = undefined;
            state.isLogging=true
            requestToken('https://91i.ru/api/v1/token', loginInfo).then(tokenData => {
                state.isLogging = false
                state.isLoggedIn = true
                state.currentLogin = loginInfo.loginText;
                localStorage.setItem('mtcalctoken', JSON.stringify(tokenData))
            })
        },

        logout(state) {
            state.isLoggedIn=false
            localStorage.removeItem('mtcalctoken')
            router.push('/')
        }
    }
})