# CI/CD Notes

Pipeline stages:
1. Compile + unit/integration tests
2. JaCoCo threshold enforcement
3. SonarQube quality gate
4. Image vulnerability scanning
5. Build/push immutable Docker images
6. Progressive delivery to Kubernetes (rolling/canary/blue-green)
