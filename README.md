# Train Ticket Booking System

A modern, full-stack train ticket booking system built with Spring Boot and JavaScript.

## Table of Contents
1. [Features](#features)
2. [Technology Stack](#technology-stack)
3. [System Architecture](#system-architecture)
4. [Database Schema](#database-schema)
5. [API Documentation](#api-documentation)
6. [Setup and Installation](#setup-and-installation)
7. [Security](#security)
8. [Frontend Components](#frontend-components)
9. [Testing](#testing)
10. [Deployment](#deployment)

## Features

### User Management
- User registration with email verification
- Secure login with JWT authentication
- Role-based access control (User, Admin)

### Train Search and Booking
- Search trains by source, destination, and date
- Real-time seat availability
- Multiple coach types with different pricing
- Instant PNR generation
- Fare calculation based on coach type

### Ticket Management
- View booking history
- Check PNR status
- Cancel tickets
- Automated refund processing

### Coach Types and Pricing
1. AC First Class (AC1)
   - Premium comfort
   - ₹2,500 - ₹3,500
   - 40-60 seats per train

2. AC 2 Tier (AC2)
   - Mid-range AC coach
   - ₹1,800 - ₹2,500
   - 80-120 seats per train

3. AC 3 Tier (AC3)
   - Economic AC coach
   - ₹1,200 - ₹1,800
   - 120-180 seats per train

4. Sleeper Class (SL)
   - Non-AC coach
   - ₹600 - ₹1,000
   - 160-240 seats per train

## Technology Stack

### Backend
- Java 21
- Spring Boot 3.4.0
- Spring Security with JWT
- Spring Data JPA
- H2 Database
- Maven

### Frontend
- HTML5
- CSS3
- JavaScript (Vanilla)
- Font Awesome Icons

### Development Tools
- Git
- Maven
- JUnit
- Postman (API Testing)

## System Architecture

### Model Layer
1. **User**
   - Manages user information and authentication
   - Fields: id, username, password, email, role

2. **Train**
   - Handles train information and scheduling
   - Fields: id, trainNumber, name, totalSeats, availableSeats

3. **Coach**
   - Manages different types of coaches and their availability
   - Fields: id, coachType, totalSeats, availableSeats, fare

4. **Ticket**
   - Handles booking information and status
   - Fields: id, pnr, seatNumber, travelDate, status

5. **Station**
   - Manages station information
   - Fields: code, name, city, state

6. **Schedule**
   - Manages train schedules
   - Fields: id, daysOfWeek, departureTime, arrivalTime

### Repository Layer
- UserRepository
- TrainRepository
- CoachRepository
- TicketRepository
- StationRepository
- ScheduleRepository

### Service Layer
- UserService
- TrainService
- TicketService
- AuthService
- CustomUserDetailsService

### Controller Layer
- AuthController
- UserController
- TrainController
- TicketController
- StationController

## API Documentation

### Authentication Endpoints
```
POST /api/auth/register
POST /api/auth/login
```

### Train Endpoints
```
GET /api/trains/search
GET /api/trains/{trainId}/coaches
POST /api/trains
```

### Ticket Endpoints
```
POST /api/tickets
GET /api/tickets/user
GET /api/tickets/pnr/{pnr}
DELETE /api/tickets/{pnr}
```

### Station Endpoints
```
GET /api/stations
```

## Setup and Installation

### Prerequisites
- Java 21
- Maven
- Git

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to project directory:
   ```bash
   cd train-ticket
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
   ```
   http://localhost:8080
   ```

## Security

### JWT Authentication
- Token-based authentication
- Token expiration: 24 hours
- Secure password hashing with BCrypt

### Authorization
- Role-based access control
- Protected endpoints
- CORS configuration

## Frontend Components

### User Interface
1. **Navigation**
   - Registration
   - Login
   - Search Trains
   - Book Tickets
   - PNR Status
   - My Bookings
   - Logout

2. **Search Section**
   - Source station selection
   - Destination station selection
   - Date picker
   - Dynamic results display

3. **Booking Section**
   - Train selection
   - Coach type selection
   - Seat availability
   - Fare display
   - Booking confirmation

4. **My Bookings Section**
   - Upcoming bookings
   - Cancelled bookings
   - Booking details
   - Cancel ticket option

### Responsive Design
- Mobile-first approach
- Flexible layouts
- Optimized for all screen sizes

## Testing

### Unit Tests
- Service layer tests
- Repository layer tests
- Controller layer tests

### Integration Tests
- API endpoint tests
- Database integration tests
- Authentication flow tests

## Deployment

### Production Setup
1. Configure production database
2. Set up environment variables
3. Configure CORS for production domain
4. Set up SSL certificate
5. Configure logging

### Monitoring
- Application health checks
- Performance monitoring
- Error logging
- User activity tracking

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

## License
This project is licensed under the MIT License. 