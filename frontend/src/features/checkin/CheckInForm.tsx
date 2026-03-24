import React, { ChangeEvent, FormEvent, useState } from "react";
import { createCheckIn } from "./checkInService";
import { CreateCheckInRequest } from "../../types/checkIn";

const initialForm: CreateCheckInRequest = {
  craving: 5,
  mood: 5,
  energy: 5,
  sleepQuality: 5,
  context: "",
  wantsToConsume: false,
};

export default function CheckInForm() {
  const [form, setForm] = useState<CreateCheckInRequest>(initialForm);
  const [isSubmitting, setIsSubmitting] = useState<boolean>(false);
  const [successMessage, setSuccessMessage] = useState<string>("");
  const [errorMessage, setErrorMessage] = useState<string>("");

  function handleChange(event: ChangeEvent<HTMLInputElement>) {
    const { name, value, type, checked } = event.target;

    setForm((prev) => ({
      ...prev,
      [name]:
        type === "checkbox"
          ? checked
          : type === "number"
            ? Number(value)
            : value,
    }));
  }

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsSubmitting(true);
    setSuccessMessage("");
    setErrorMessage("");

    try {
      const created = await createCheckIn(form);
      setSuccessMessage(`Check-in saved with id ${created.id}`);
      setForm(initialForm);
      console.log("Created check-in:", created);
    } catch (error) {
      console.error(error);
      setErrorMessage("Failed to save check-in.");
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <form onSubmit={handleSubmit} className="form-card">
      <h2 className="form-title">Daily Check-In</h2>

      <label className="input-group">
        <span className="input-label">Craving</span>
        <input
          className="number-input"
          type="number"
          name="craving"
          min="0"
          max="10"
          value={form.craving}
          onChange={handleChange}
        />
      </label>

      <label className="input-group">
        <span className="input-label">Mood</span>
        <input
          className="number-input"
          type="number"
          name="mood"
          min="0"
          max="10"
          value={form.mood}
          onChange={handleChange}
        />
      </label>

      <label className="input-group">
        <span className="input-label">Energy</span>
        <input
          className="number-input"
          type="number"
          name="energy"
          min="0"
          max="10"
          value={form.energy}
          onChange={handleChange}
        />
      </label>

      <label className="input-group">
        <span className="input-label">Sleep Quality</span>
        <input
          className="number-input"
          type="number"
          name="sleepQuality"
          min="0"
          max="10"
          value={form.sleepQuality}
          onChange={handleChange}
        />
      </label>

      <label className="input-group">
        <span className="input-label">Context</span>
        <input
          className="text-input"
          type="text"
          name="context"
          value={form.context}
          onChange={handleChange}
          placeholder="e.g. alone at night"
        />
      </label>

      <label className="checkbox-row">
        <input
          type="checkbox"
          name="wantsToConsume"
          checked={form.wantsToConsume}
          onChange={handleChange}
        />
        <span>Wants to consume</span>
      </label>

      <button className="primary-button" type="submit" disabled={isSubmitting}>
        {isSubmitting ? "Saving..." : "Save Check-In"}
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
