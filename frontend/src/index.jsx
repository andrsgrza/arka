import React, { useEffect } from "react";
import { createRoot } from "react-dom/client";

function App() {
  useEffect(() => {
    fetch("http://localhost:8080/health")
      .then((res) => res.text())
      .then((data) => console.log(data));
  }, []);

  return <h1>ARKA is running</h1>;
}

createRoot(document.getElementById("root")).render(<App />);
