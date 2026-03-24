import React from "react";
import CheckInForm from "./features/checkin/CheckInForm";
import RiskCard from "./features/risk/RiskCard";

export default function App() {
  return (
    <main className="app-shell">
      <div className="page-container">
        <h1 className="page-title">ARKA</h1>
        <RiskCard />
        <CheckInForm />
      </div>
    </main>
  );
}
