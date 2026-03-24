import React, { useEffect, useState } from "react";
import { getInterventions } from "./interventionService";
import { InterventionResponse } from "../../types/intervention";

export default function InterventionCard() {
  const [interventions, setInterventions] = useState<InterventionResponse[]>(
    [],
  );
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [errorMessage, setErrorMessage] = useState<string>("");

  useEffect(() => {
    async function loadInterventions() {
      try {
        setIsLoading(true);
        setErrorMessage("");
        const data = await getInterventions();
        setInterventions(data);
      } catch (error) {
        console.error(error);
        setErrorMessage("Failed to load interventions.");
      } finally {
        setIsLoading(false);
      }
    }

    loadInterventions();
  }, []);

  return (
    <section className="info-card">
      <h2 className="info-card-title">Suggested Interventions</h2>

      {isLoading ? (
        <p className="form-message">Loading interventions...</p>
      ) : null}
      {errorMessage ? (
        <p className="form-message text-error">{errorMessage}</p>
      ) : null}

      {!isLoading && !errorMessage && interventions.length === 0 ? (
        <p className="form-message">No interventions available.</p>
      ) : null}

      {!isLoading && !errorMessage && interventions.length > 0 ? (
        <div className="intervention-list">
          {interventions.map((intervention) => (
            <article key={intervention.code} className="intervention-item">
              <div className="intervention-header">
                <h3 className="intervention-title">{intervention.title}</h3>
                <span className="intervention-duration">
                  {intervention.durationMinutes} min
                </span>
              </div>
              <p className="intervention-description">
                {intervention.description}
              </p>
            </article>
          ))}
        </div>
      ) : null}
    </section>
  );
}
