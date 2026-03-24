# ARKA — Contributing Guide

This document defines the engineering standards, conventions, and practices for contributing to ARKA.

The goal is to maintain:
- scalability
- consistency
- maintainability
- clarity of architecture

---

# 1. Repository Structure

arka/
├── frontend/
├── backend/
├── docs/
├── CONTRIBUTING.md

Each layer must remain independently maintainable.

---

# 2. Backend Standards (Spring Boot)

## Package Structure
All backend code must live under:
com.arka.core

Standard structure:
controller/
service/
repository/
model/
dto/
  ├── request/
  └── response/
mapper/
config/

## Controllers
- Must NOT return JPA entities
- Must ONLY return response DTOs

## DTOs
- Separate request and response DTOs
- Never expose internal entities

## Mapping
- All mapping must be in mapper/
- No mapping logic in controllers or entities

## Services
- Contain business logic
- Controllers must remain thin

## Entities
- Persistence model only
- No business logic

## Database
- H2 for development only
- Must remain portable

## CORS
- Configured globally in config/

---

# 3. Frontend Standards (React + TypeScript)

## Language
- TypeScript is mandatory
- No new .js/.jsx files

## Structure
src/
├── App.tsx
├── index.tsx
├── features/
├── services/
├── types/
├── styles/

## Types
- All types in src/types/
- No usage of any

## API Layer
- All calls go through services/apiClient.ts
- No fetch inside components

## Components
- Functional components only
- Small and focused

---

# 4. Styling System

## Principles
- scalable
- consistent
- modifiable
- token-based

## Structure
styles/
├── variables.css
├── base.css
├── forms.css

## Rules
DO:
- Use CSS variables
- Use reusable classes

DO NOT:
- Inline styles
- Hardcoded values
- Duplicated styles

---

# 5. API Contracts

- Backend defines contract
- Frontend consumes typed interfaces

Naming:
- CreateXRequest
- XResponse

---

# 6. User Model Strategy

Current:
- Single dev user

Future:
- Authentication system
- Multi-user support

---

# 7. Git & Commits

feat: feature
refactor: refactor
fix: bug fix
build: tooling
chore: misc

---

# 8. Engineering Principles

- Keep it simple
- Avoid premature abstraction
- Build incrementally
- Protect architecture integrity

---

# 9. What NOT to Do

- Do not return entities from controllers
- Do not use any in TypeScript
- Do not mix styling approaches
- Do not over-engineer early

---

# 10. Evolution

This document will evolve as ARKA grows.
