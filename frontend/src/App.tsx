import React from "react";
import CheckInForm from "./features/checkin/CheckInForm";

export default function App() {
  return (
    <main className="app-shell">
      <div className="page-container">
        <h1 className="page-title">ARKA</h1>
        <CheckInForm />
      </div>
    </main>
  );
}
