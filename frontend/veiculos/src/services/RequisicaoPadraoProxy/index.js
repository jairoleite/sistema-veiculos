import axios from 'axios';

//URl padrão
const BASE_URL = process.env.DEV ? `http://localhost:8080` : `http://localhost:8080`;

// interceptação da requisição das requisições
axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});


// Requisição padrão
export const requisicao = ({ method = 'GET', url, data, params, headers }) => {
    return axios({
        method,
        url: `${BASE_URL}${url}`,
        data,
        params,
        headers
    });
}
