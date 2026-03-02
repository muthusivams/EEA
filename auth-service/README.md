# Auth Service

Responsibilities:
- User authentication and token issuance
- Refresh token rotation and revocation
- Account lock policy hooks

## API
- `POST /auth/login`

## Security
- BCrypt passwords
- JWT (RS256 in production; sample provider placeholder)
- HSTS header and authenticated routes by default
