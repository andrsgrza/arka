import React, { useEffect, useState } from "react";
import { getRisk } from "./riskService";
import { RiskResponse } from "../../types/risk";

export default function RiskCard() {
  const [risk, setRisk] = useState<RiskResponse | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [errorMessage, setErrorMessage] = useState<string>("");

  useEffect(() => {
    async function loadRisk() {
      try {
        setIsLoading(true);
        setErrorMessage("");
        const data = await getRisk();
        setRisk(data);
      } catch (error) {
        console.error(error);
        setErrorMessage("Failed to load current risk.");
      } finally {
        setIsLoading(false);
      }
    }

    loadRisk();
  }, []);

  if (isLoading) {
    return (
      <section className="info-card">
        <h2 className="info-card-title">Current Risk</h2>
        <p className="form-message">Loading current risk...</p>
      </section>
    );
  }

  if (errorMessage) {
    return (
      <section className="info-card">
        <h2 className="info-card-title">Current Risk</h2>
        <p className="form-message text-error">{errorMessage}</p>
      </section>
    );
  }

  if (!risk) {
    return (
      <section className="info-card">
        <h2 className="info-card-title">Current Risk</h2>
        <p className="form-message">No check-ins available yet.</p>
      </section>
    );
  }

  return (
    <section className="info-card">
      <h2 className="info-card-title">Current Risk</h2>

      <div className="risk-row">
        <span className="risk-label">Score</span>
        <span className="risk-value">{risk.score}</span>
      </div>

      <div className="risk-row">
        <span className="risk-label">Level</span>
        <span className={`risk-badge risk-badge-${risk.level.toLowerCase()}`}>
          {risk.level}
        </span>
      </div>

      <div className="risk-factors">
        <span className="risk-label">Factors</span>
        {risk.factors.length > 0 ? (
          <ul className="risk-factor-list">
            {risk.factors.map((factor) => (
              <li key={factor}>{factor}</li>
            ))}
          </ul>
        ) : (
          <p className="form-message">No factors detected.</p>
        )}
      </div>
    </section>
  );
}
