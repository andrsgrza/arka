import { apiRequest } from "../../services/apiClient";

export async function createCheckIn(payload) {
  return apiRequest("/check-ins", {
    method: "POST",
    body: JSON.stringify(payload),
  });
}
