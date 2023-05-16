import axios from 'axios'

const request = axios.create({
  // http://localhost:8000
  baseURL: process.env.VUE_APP_BASE_URL
})

export default request