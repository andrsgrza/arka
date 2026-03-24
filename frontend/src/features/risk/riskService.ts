import { apiRequest } from "../../services/apiClient";
import { RiskResponse } from "../../types/risk";

export async function getRisk(): Promise<RiskResponse | null> {
  const response = await fetch("http://localhost:8080/risk");

  if (response.status === 204) {
    return null;
  }

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(
      errorText || `Request failed with status ${response.status}`,
    );
  }

  return response.json() as Promise<RiskResponse>;
}
