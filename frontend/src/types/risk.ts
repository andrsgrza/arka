export interface RiskResponse {
  score: number;
  level: "LOW" | "MEDIUM" | "HIGH";
  factors: string[];
}
