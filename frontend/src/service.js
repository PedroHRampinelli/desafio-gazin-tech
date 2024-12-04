import axios from "axios";
import { toast } from "react-toastify";

export const Api = axios.create({

    baseURL: 'http://localhost:8080'
})

export const findAllNiveis = async () => {
    const page = 0;
    const size = 50;

    const response = await Api.get(`/api/niveis?page=${page}&size=${size}`)
        .then((response) => response.data)
        .catch((error) => {
            console.log(error);
            return ""
        });

    return response.content;
}

export const createNivel = async (nivel) => {
    await Api.post(`/api/niveis`, nivel)
        .then(() => toast.success("Nível Criado com Sucesso"))
        .catch((error) => {
            return ""
        })
}

export const deleteNivel = async (id) => {
    const response = await Api.delete(`/api/niveis/${id}`)
    .then(() => toast.success("Nvel deletado com sucesso"))
    .catch((error) => toast.error(error.response.data));

    return response.content;
}

export const updateNivel = async (nivel) => {
    const response = await Api.put(`/api/niveis/${nivel.id}`, nivel)
    .then(() => 
        toast.success("Nível atualizado com sucesso"))
    .catch((error) => toast.error(error.response.data));

    return response;
}

export const findAllDevs = async () => {
    const page = 0;
    const size = 50;

    const response = await Api.get(`/api/desenvolvedores?page=${page}&size=${size}`)
        .then((response) => response.data)
        .catch((error) => {
            return ""
        });

    return response.content;
}

export const deleteDesenvolvedor = async (id) => {
    const response = await Api.delete(`/api/desenvolvedores/${id}`)
        .then((response) => response.data)
        .catch((error) => {
            console.log(error);
            return ""
        });

    return response.content;
}

export const createDesenvolvedor = async (desenvolvedor) => {
    await Api.post(`/api/desenvolvedores`, desenvolvedor)
        .then(() => toast.success("Desenvolvedor Criado com Sucesso")).catch(error => {
        })

}

export const updateDesenvolvedor = async (desenvolvedor) => {
    await Api.put(`/api/desenvolvedores/${desenvolvedor.id}`, desenvolvedor)
        .then(() => {
            toast.success("Desenvolvedor Atualizado com Sucesso")
    })

}

export const getAllDevsByNivel = async (nivelId) => {
    const response = await Api.get(`/api/desenvolvedores/devByNivel/${nivelId}`)
    .then((response) => response.data)
    .catch((error) => {
        console.log(error);
        return ""
    });;
    return response;
}
