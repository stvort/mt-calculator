import Vue from 'vue'
import Vuex from 'vuex'
import {requestToken, sendTrainingResults, loadToken, requestData} from './backend'
import router from '../router'

Vue.use(Vuex)

const urlAuth = 'https://91i.ru/api/v1/token';

/*
const urlDashboardAll = 'https://mt-calculator-dashboard.herokuapp.com/api/v1/dashboard/all'
const urlDashboardSelf = 'https://mt-calculator-dashboard.herokuapp.com/api/v1/dashboard/self'
const urlTrainingStart = 'https://91i.ru/api/v1/start'
const urlTrainingEnd = 'https://91i.ru/api/v1/end'
*/

const urlDashboardAll = 'http://localhost:8080/api/v1/dashboard/all'
const urlDashboardSelf = 'http://localhost:8080/api/v1/dashboard/self'
const urlTrainingStart = 'http://localhost:8080/api/v1/start'
const urlTrainingEnd = 'http://localhost:8080/api/v1/end'

export default new Vuex.Store({
    state: {
        isLoggedIn: loadToken(),
        isLogging: false,
        isLoading: false,
        isChecking: false,

        currentLogin: '',
        commonResults: [],
        selfResults: [],
        trainingSessionInfo: undefined,
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
            console.log('setCommonResults')
            state.isLoading = true
            requestData(urlDashboardAll).then(data => {
                state.commonResults = data
                state.isLoading = false
            })
        },

        setSelfResults(state) {
            console.log('setSelfResults')
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
            
            //console.log("login: " + loginInfo.loginText + ", password: " + loginInfo.passwordText)
            if (!loginInfo || !loginInfo.loginText || loginInfo.passwordText || loginInfo.loginText === '' || loginInfo.passwordText === '') {
                return;
            }

            state.currentLogin = undefined;
            state.isLogging=true
            requestToken(urlAuth, loginInfo).then(tokenData => {
                console.log(tokenData)
                state.isLogging = false
                state.isLoggedIn = true
                state.currentLogin = loginInfo.loginText;
                localStorage.setItem('mtcalctoken', JSON.stringify(tokenData))
            }).catch(() => {
                state.isLogging = false
            })
        },

        logout(state) {
            state.isLoggedIn=false
            localStorage.removeItem('mtcalctoken')
            router.push('/')
        }
    }
})