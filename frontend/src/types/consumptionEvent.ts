export interface CreateConsumptionEventRequest {
  amount: number;
  method: string;
  context: string;
  expectedPleasure: number;
  expectedRelief: number;
}

export interface ConsumptionEventResponse {
  id: number;
  userId: number;
  amount: number;
  method: string;
  context: string;
  expectedPleasure: number;
  expectedRelief: number;
  createdAt: string;
}
