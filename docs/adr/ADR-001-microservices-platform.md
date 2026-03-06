# ADR-001: eCommerce platform architecture baseline

## Status
Accepted

## Context
The platform must scale to 1M+ users with high availability, strict security and compliance controls, and independent service evolution.

## Decision
Adopt Spring Boot microservices with DDD + clean architecture, API Gateway, Eureka discovery, Config Server, Kafka events, PostgreSQL per service, and Redis for cross-cutting concerns.

## Consequences
- Pros: independent deployability, fault isolation, domain ownership, resilience patterns.
- Cons: operational complexity, eventual consistency, increased observability and governance needs.

## Related Decisions
- JWT RS256 with refresh token rotation.
- Outbox + orchestration Saga for order lifecycle reliability.
- GitOps-friendly Kubernetes manifests and GitHub Actions CI/CD.
