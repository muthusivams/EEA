# Enterprise eCommerce Architecture (EEA)

This repository contains a production-oriented blueprint for a Java/Spring microservices eCommerce platform designed for 1M+ users with zero-downtime delivery, observability, and enterprise security controls.

## High-Level Architecture

- **Architecture style**: Microservices + DDD + Clean Architecture (Hexagonal)
- **Communication**:
  - Synchronous: REST over HTTPS via API Gateway
  - Asynchronous: Kafka event bus using Outbox + Saga orchestration
- **Data strategy**:
  - Database-per-service (MySQL)
  - Redis for caching, token revocation, and rate limiting
- **Infrastructure**:
  - Discovery (Eureka)
  - Centralized configuration (Spring Cloud Config)
  - Gateway (Spring Cloud Gateway)
  - Observability (Prometheus, Grafana, ELK, tracing)
- **Security**:
  - JWT RS256, OAuth2 resource server, refresh rotation, RBAC, secure headers
- **Deployment**:
  - Docker + Kubernetes + GitHub Actions CI/CD

## Component Interaction Diagram (Textual)

1. Client -> API Gateway (`/api/v1/**`) with JWT.
2. Gateway validates token, rate-limits with Redis, injects correlation ID.
3. Gateway routes to business service via Eureka discovery.
4. Services load config from Config Server profile (`dev/qa/prod`).
5. Order flow emits events to Kafka and uses Saga orchestration.
6. Outbox relays domain events from local DB to Kafka reliably.
7. Metrics/logs/traces sent to Prometheus/ELK/tracing backend.

## Order Placement Sequence (Textual)

1. User submits checkout request via Gateway.
2. Cart Service validates cart and inventory reservation request.
3. Order Service creates `PENDING` order + outbox record.
4. Saga orchestrator sends payment command to Payment Service.
5. Payment Service emits success/failure event.
6. Order Service transitions order state (`CONFIRMED`/`CANCELLED`).
7. Notification Service sends email/SMS asynchronously.

## Root Structure

```text
api-gateway/
discovery-server/
config-server/
auth-service/
user-service/
product-service/
cart-service/
order-service/
payment-service/
notification-service/
ci/
k8s/
monitoring/
docker-compose.yml
docs/
```

> Implemented so far in batches: **Auth + User**, **Product + Cart**, and **Order + Payment + Notification** service patterns, plus platform-level infrastructure templates.

## ER Design (Initial Domains)

- **auth.users_credentials**: stores login identity (email, password hash, status, lock counters, roles).
- **auth.refresh_tokens**: refresh token family, rotation metadata, revocation timestamps.
- **users.user_profiles**: profile details and consent preferences.
- **users.user_addresses**: normalized addresses linked to profiles.

Indexes:
- `users_credentials(email)` unique
- `refresh_tokens(subject_id, revoked)` partial index
- `user_profiles(external_auth_id)` unique

## Compliance Readiness

- GDPR: consent flags, soft-delete hooks, subject export/deletion workflows.
- PCI-DSS: card data not persisted in platform; payment tokenization by Payment Service/PSP.
- Audit logging: correlation IDs + immutable audit events.
- Data masking: PII masking for logs and telemetry.

## Services in This Phase

- ✅ `auth-service` (JWT/OAuth2 token issuing, refresh rotation, revocation)
- ✅ `user-service` (profile management, RBAC-protected APIs)
- ✅ `product-service` (catalog create/read, cache-ready, metrics)
- ✅ `cart-service` (active cart + items, metrics, RBAC-protected APIs)
- ✅ `api-gateway`, `discovery-server`, `config-server` foundational configs
- ✅ `order-service` (order creation/query, outbox persistence, metrics)
- ✅ `payment-service` (payment processing/query, payment status persistence)
- ✅ `notification-service` (send/query notifications, channel metrics)
- ✅ `inventory-service` (stock upsert/query by SKU + warehouse)
- ✅ all requested business services are now scaffolded


## API Documentation

Each service exposes runtime Swagger docs at:
- `/swagger-ui.html`
- `/v3/api-docs`
