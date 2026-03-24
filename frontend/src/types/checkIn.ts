export interface CreateCheckInRequest {
  craving: number;
  mood: number;
  energy: number;
  sleepQuality: number;
  context: string;
  wantsToConsume: boolean;
}

export interface CheckInResponse {
  id: number;
  craving: number;
  mood: number;
  energy: number;
  sleepQuality: number;
  context: string;
  wantsToConsume: boolean;
  createdAt: string;
}
