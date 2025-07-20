User API with authentication
-----------------------------

Next REST API and functionality available:
1. "\register" - allows a new user to register with his login/password and receive JWT access-token and also refresh-token to interact with protected resources
2. "\login" - allows users to get a JWT token for accessing protected resources using his login and password to interact with protected resources
3. "\users" - allows authenticated users to create a personal profile with information about themselves, as well as view contact information for other users
4. "\refresh" - allows to update JWT access-token and also refresh-token
5. "\properties" - allows to dynamically update the application environment parameters
6. "\admin\users" - allows to access protected resources with a JWT access-token and view advanced user information

**_In development:_**
- covering functional requirements with unit and integration tests
- redesigning the application from a monolithic to a microservice architecture
- containerization of services using Docker
- refining the concept of working with users (for example, implementing access according to their security roles)