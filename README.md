# Event Management System

#Technologies: 
Database: MongoDB<br>
Backend: Spring Boot <br>
Frontend: ReactJS <br>

#Features:
1. User Registration and Login: Attendees can register for a new account and log in using secure credentials. Provide secure access to ticketing features. The system supports different user roles,
including attendees and administrators. <br>
2. Event Listing: A comprehensive list of upcoming events is available for users to browse. Event details include date, time, venue, and available ticket options. <br>
3. Seat Selection: Users can interactively select and reserve specific seats for events. The system provides an intuitive seat map for venue visualization. <br>
4. Payment Processing: Secure payment transactions are handled seamlessly within the platform. Various payment methods are supported for user convenience. <br>
5. Digital Ticket Delivery: Digital tickets containing event details are generated for users. Tickets are delivered electronically, offering ease of access via email or mobile apps. <br>

#To Run
Create a .env file under src/main/resources/.env with the structure as shown in .env.example. This will setup the necessary environment variables and the project can be run. 

#REST APIs: 
  #User Management: 
  1. Create new user <br>
  API: POST /api/signup <br>
  ![image](https://github.com/Anagha0202/EventManagementSystem/assets/53923590/8cc903fc-7ee6-4521-af04-76e7084e350b)<br>
 (Note 1: 2 types of users- user, admin. Signup only allows creation of new user type.
  Note 2: Password has been encrypted using BCryptPasswordEncoder)<br>
 2. Login <br>
 API: POST /api/login <br>
 ![image](https://github.com/Anagha0202/EventManagementSystem/assets/53923590/2c5751a9-8667-4709-9d81-646577d442df) <br>
 (Note: There is no separate login for user and admin. API returns a field identifying admin)

#Event Management:
1. Display all events details <br>
API: GET /api/events <br>
2. Display one event details <br>
API: GET /api/events/{eventId} <br>
3. Create new event <br>
API: POST /api/events/createEvent <br>
![image](https://github.com/Anagha0202/EventManagementSystem/assets/53923590/cface6ea-aa9c-412d-86f8-750805cb89ac) <br>
(Note: create a new event creates predefined number of seats for the event.)<br>
4. Update existing event <br>
API: PUT /api/events/updateEvent/{eventId} <br>
5. Delete existing event <br>
API: DELETE /api/remove/{eventId} <br>
(Note: create, update, delete can be done only by admin) <br>

#Seat Management:
1. Get seat details of an event <br>
API: GET /api/seats/{eventId} <br>
2. Get seat details of an event and particular seat <br>
API: GET /api/seats/{eventId}/{seatId} <br>

#Reservation Management:
1. Display all reservations <br>
API: GET /api/reservations <br>
2. Get one reservation <br>
API: GET /api/reservations/{reservationId} <br>
3. Create new reservation <br>
API: POST /api/reservations/createReservation <br>
![image](https://github.com/Anagha0202/EventManagementSystem/assets/53923590/32124f96-4da2-4544-a08b-bc80678c6ce8) <br>

#Payment Management:
1. Complete payment <br>
API: POST /api/payment <br>
![image](https://github.com/Anagha0202/EventManagementSystem/assets/53923590/d5790920-dfb1-4219-bc7e-2bc386e274e3) <br>
(Note: Payments are only mocked.)
