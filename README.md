# Naebank Spring boot server
Spring boot BE that serves HTTP requests/response to Naebank client. Basically it does CRUD for an ordinary bank operations

# Features
- Register user
- Login user
- Get current user
- Get all users

- Add new card
- Delete card
- Get card by user id
- Get card's transactions by card id
- Update card balance (withdraw/top-up)

- Get all transactions
- Get transactions by card id

# Architecture
- data layer:
  - Entities
  - Repositories

- domain layer
  - Services

- controllers/gateway-to-presentation
  - Controllers
  - Mappers
  - DTOs

# Persistence
- As DB serves Postgresql, but it can be substituted to any relation DB as repositories will abstract specific DB from Business logic

# Security
- WebSecurity with BCryptPasswordEncoder and JWT token

# Overall
- Springboot as a context/beans container. It works via restful HTTP requests/response fo no web frontend/html forms provided 


