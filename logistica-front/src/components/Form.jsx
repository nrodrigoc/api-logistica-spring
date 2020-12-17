import React, { useState, useEffect } from "react";
import api from "../api/api";
import caminhoneiro from "../api/caminhoneiro"

function Form() {
  const [nome, setNome] = useState("");
  const [id, setId] = useState(-1);

  useEffect(() => {
    api.get("/produtos/1").then((response) => {
      const data = response.data;
      setId(data.id);
      setNome(data.nome);
    });
  }, []);

  return (
    <>
      <h3>Adicionar caminhoneiro</h3>
      <br />
      <form>
        <label for="fname">Nome: </label>
        <input type="text" id="fname" name="fname"/><br/>
      </form>
      <label>Id do produto: {id}</label> <br />
      <label>Nome do produto: {nome}</label>
    </>
  );
}

export default Form;
