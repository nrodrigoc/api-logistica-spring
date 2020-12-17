import React, { useState, useEffect } from 'react'
import api from '../api/api'


function Form() {
    
    const [nome, setNome] = useState("");
    const [id, setId] = useState(-1)

    useEffect(() => {
        api.get("/produtos/1")
            .then((response) => {
                const data = response.data;
                setId(data.id);
                setNome(data.nome);
            });
        
    }, [])
    

    return (
        <>  
            <label>Id do produto: {id}</label> <br/>
            <label>Nome do produto: {nome}</label>
        </>
    )
}

export default Form;