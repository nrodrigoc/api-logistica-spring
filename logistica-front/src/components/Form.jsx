import React, { useState, useEffect } from "react";
import api from "../api/api";
import caminhoneiro from "../api/caminhoneiro";

function Form() {
    //Produto
    const [nomeProduto, setNomeProduto] = useState("");
    const [id, setId] = useState(-1);

    //Caminhoneiro
    const [nomeCaminhoneiro, setNomeCaminhoneiro] = useState("");

    // GET-Method
    useEffect(() => {
        api.get("/produtos/1").then((response) => {
            const data = response.data;
            setId(data.id);
            setNomeProduto(data.nomeProduto);
        });
    }, []);

    // POST-Method
    function addCaminhoneiro() {
        caminhoneiro
            .post("http://localhost:8080/caminhoneiro/", {
                nome: nomeCaminhoneiro,
            })
            .then((response) => console.log(response))
            .catch((error) => console.log(error));
    }

    function handleNomeCaminhoneiro(c_nome) {
        setNomeCaminhoneiro(c_nome);
    }

    return (
        <>
            <h3>Adicionar caminhoneiro</h3>
            <br />
            <input
                type="text"
                onChange={(e) => handleNomeCaminhoneiro(e.target.value)}
            />
            <button type="button" onClick={() => addCaminhoneiro()}>
                Enviar
            </button>
            <br />
            <label>Id do produto: {id}</label> <br />
            <label>nomeProduto do produto: {nomeProduto}</label>
        </>
    );
}

export default Form;
