# Key Functionalities Used in This Microservices Project

## Platform and Architecture
- Microservices architecture using Spring Boot with domain-driven and clean architecture style.
- API-first communication through REST APIs and OpenAPI/Swagger endpoints.
- Event-driven workflows through Kafka for asynchronous communication.
- Database-per-service model with MySQL and schema migration through Flyway.
- Cross-cutting caching/rate-limit support using Redis.

## Core Infrastructure Services
- API Gateway for centralized routing and edge concerns.
- Discovery Server for service discovery (Eureka-based foundation).
- Config Server for centralized configuration management.
- Docker Compose orchestration for local multi-service startup.

## Business Microservices and Responsibilities

### 1) Auth Service
- User authentication endpoint (`POST /auth/login`).
- JWT/OAuth2 token issuing and refresh token rotation support.
- Token revocation support and security-oriented configuration.

### 2) User Service
- User profile creation endpoint (`POST /users`).
- User profile retrieval endpoint (`GET /users/{externalAuthId}`).
- RBAC-protected API pattern and validation/error handling baseline.

### 3) Product Service
- Product catalog creation endpoint (`POST /products`).
- Product lookup by SKU endpoint (`GET /products/{sku}`).
- Cache-ready read model pattern and metrics integration.

### 4) Cart Service
- Add item to user cart endpoint (`POST /carts/{userId}/items`).
- Get active cart for a user endpoint (`GET /carts/{userId}`).
- Cart domain validation and standardized exception handling.

### 5) Order Service
- Create order endpoint (`POST /orders`).
- Query order by order number endpoint (`GET /orders/{orderNumber}`).
- Outbox persistence pattern to support reliable event publishing.

### 6) Payment Service
- Process payment endpoint (`POST /payments`).
- Query payment by reference endpoint (`GET /payments/{paymentReference}`).
- Payment status persistence and event-compatible flow.

### 7) Notification Service
- Send notification endpoint (`POST /notifications`).
- Query notification by message ID endpoint (`GET /notifications/{messageId}`).
- Channel-level notification metrics and asynchronous-ready design.

### 8) Inventory Service
- Upsert stock endpoint (`POST /inventory`).
- Get inventory by SKU endpoint (`GET /inventory/{sku}`).
- SKU/warehouse stock management model.

## Security, Observability, and Runtime Practices
- JWT RS256/OAuth2 resource-server style security baseline.
- Spring Actuator metrics/health exposure for operations.
- Unified global exception handlers across services.
- Swagger UI and OpenAPI docs exposed for each service.
- Containerized build/run model via per-service Dockerfiles.

## Typical End-to-End Functional Flow
1. Client calls API gateway with JWT.
2. Request is routed to target microservice.
3. Service executes domain logic and persists state in its own MySQL database.
4. Relevant services publish or consume Kafka events for async propagation.
5. Monitoring/metrics endpoints provide runtime visibility.

---
Prepared for: EEA (Enterprise eCommerce Architecture) project.
