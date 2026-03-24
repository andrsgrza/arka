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
    <form onSubmit={handleSubmit} style={styles.form}>
      <h2>Daily Check-In</h2>

      <label style={styles.label}>
        Craving
        <input
          type="number"
          name="craving"
          min="0"
          max="10"
          value={form.craving}
          onChange={handleChange}
        />
      </label>

      <label style={styles.label}>
        Mood
        <input
          type="number"
          name="mood"
          min="0"
          max="10"
          value={form.mood}
          onChange={handleChange}
        />
      </label>

      <label style={styles.label}>
        Energy
        <input
          type="number"
          name="energy"
          min="0"
          max="10"
          value={form.energy}
          onChange={handleChange}
        />
      </label>

      <label style={styles.label}>
        Sleep Quality
        <input
          type="number"
          name="sleepQuality"
          min="0"
          max="10"
          value={form.sleepQuality}
          onChange={handleChange}
        />
      </label>

      <label style={styles.label}>
        Context
        <input
          type="text"
          name="context"
          value={form.context}
          onChange={handleChange}
          placeholder="e.g. alone at night"
        />
      </label>

      <label style={styles.checkboxLabel}>
        <input
          type="checkbox"
          name="wantsToConsume"
          checked={form.wantsToConsume}
          onChange={handleChange}
        />
        Wants to consume
      </label>

      <button type="submit" disabled={isSubmitting}>
        {isSubmitting ? "Saving..." : "Save Check-In"}
      </button>

      {successMessage ? <p style={styles.success}>{successMessage}</p> : null}
      {errorMessage ? <p style={styles.error}>{errorMessage}</p> : null}
    </form>
  );
}

const styles: Record<string, React.CSSProperties> = {
  form: {
    display: "flex",
    flexDirection: "column",
    gap: "12px",
    maxWidth: "420px",
    margin: "40px auto",
    padding: "24px",
    border: "1px solid #ddd",
    borderRadius: "8px",
  },
  label: {
    display: "flex",
    flexDirection: "column",
    gap: "4px",
  },
  checkboxLabel: {
    display: "flex",
    alignItems: "center",
    gap: "8px",
  },
  success: {
    color: "green",
  },
  error: {
    color: "red",
  },
};
