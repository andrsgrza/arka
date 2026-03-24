import { apiRequest } from "../../services/apiClient";
import { CheckInResponse, CreateCheckInRequest } from "../../types/checkIn";

export async function createCheckIn(
  payload: CreateCheckInRequest,
): Promise<CheckInResponse> {
  return apiRequest<CheckInResponse>("/check-ins", {
    method: "POST",
    body: JSON.stringify(payload),
  });
}
