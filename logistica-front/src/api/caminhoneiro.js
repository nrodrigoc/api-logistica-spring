import axios from 'axios'

const caminhoneiro = axios.create({
    baseURL: "http://localhost:8080/caminhoneiro"
});

export default caminhoneiro;