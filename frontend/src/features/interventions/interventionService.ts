import { InterventionResponse } from "../../types/intervention";

export async function getInterventions(): Promise<InterventionResponse[]> {
  const response = await fetch("http://localhost:8080/interventions");

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(
      errorText || `Request failed with status ${response.status}`,
    );
  }

  return response.json() as Promise<InterventionResponse[]>;
}
