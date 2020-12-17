import React from "react";
import "./styles/App.css";
import Home from "./Home";
import Form from "./Form"

function App() {
  const bomdia = "Bom dia.";

  return (
    <>
      <h1>{bomdia}</h1>
      <Home />
      <Form />
    </>
  );
}

export default App;
