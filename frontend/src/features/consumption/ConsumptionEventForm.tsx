import React, { ChangeEvent, FormEvent, useState } from "react";
import { createConsumptionEvent } from "./consumptionEventService";
import { CreateConsumptionEventRequest } from "../../types/consumptionEvent";

const initialForm: CreateConsumptionEventRequest = {
  amount: 1,
  method: "smoke",
  context: "",
  expectedPleasure: 5,
  expectedRelief: 5,
};

export default function ConsumptionEventForm() {
  const [form, setForm] = useState<CreateConsumptionEventRequest>(initialForm);
  const [isSubmitting, setIsSubmitting] = useState<boolean>(false);
  const [successMessage, setSuccessMessage] = useState<string>("");
  const [errorMessage, setErrorMessage] = useState<string>("");

  function handleChange(
    event: ChangeEvent<HTMLInputElement | HTMLSelectElement>,
  ) {
    const { name, value, type } = event.target;

    setForm((prev) => ({
      ...prev,
      [name]: type === "number" ? Number(value) : value,
    }));
  }

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsSubmitting(true);
    setSuccessMessage("");
    setErrorMessage("");

    try {
      const created = await createConsumptionEvent(form);
      setSuccessMessage(`Consumption event saved with id ${created.id}`);
      setForm(initialForm);
      console.log("Created consumption event:", created);
    } catch (error) {
      console.error(error);
      setErrorMessage("Failed to save consumption event.");
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <form onSubmit={handleSubmit} className="form-card">
      <h2 className="form-title">Log Consumption</h2>

      <label className="input-group">
        <span className="input-label">Amount</span>
        <input
          className="number-input"
          type="number"
          name="amount"
          min="1"
          value={form.amount}
          onChange={handleChange}
        />
      </label>

      <label className="input-group">
        <span className="input-label">Method</span>
        <select
          className="text-input"
          name="method"
          value={form.method}
          onChange={handleChange}
        >
          <option value="smoke">Smoke</option>
          <option value="vape">Vape</option>
          <option value="edible">Edible</option>
        </select>
      </label>

      <label className="input-group">
        <span className="input-label">Context</span>
        <input
          className="text-input"
          type="text"
          name="context"
          value={form.context}
          onChange={handleChange}
          placeholder="e.g. after work at home"
        />
      </label>

      <label className="input-group">
        <span className="input-label">Expected Pleasure</span>
        <input
          className="number-input"
          type="number"
          name="expectedPleasure"
          min="0"
          max="10"
          value={form.expectedPleasure}
          onChange={handleChange}
        />
      </label>

      <label className="input-group">
        <span className="input-label">Expected Relief</span>
        <input
          className="number-input"
          type="number"
          name="expectedRelief"
          min="0"
          max="10"
          value={form.expectedRelief}
          onChange={handleChange}
        />
      </label>

      <button className="primary-button" type="submit" disabled={isSubmitting}>
        {isSubmitting ? "Saving..." : "Save Consumption Event"}
      </button>

      {successMessage ? (
        <p className="form-message text-success">{successMessage}</p>
      ) : null}

      {errorMessage ? (
        <p className="form-message text-error">{errorMessage}</p>
      ) : null}
    </form>
  );
}
