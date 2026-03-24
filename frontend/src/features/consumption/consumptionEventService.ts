import { apiRequest } from "../../services/apiClient";
import {
  ConsumptionEventResponse,
  CreateConsumptionEventRequest,
} from "../../types/consumptionEvent";

export async function createConsumptionEvent(
  payload: CreateConsumptionEventRequest,
): Promise<ConsumptionEventResponse> {
  return apiRequest<ConsumptionEventResponse>("/consumption-events", {
    method: "POST",
    body: JSON.stringify(payload),
  });
}
