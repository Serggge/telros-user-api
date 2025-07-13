User API with authentication
-----------------------------

Three REST-controllers available:
1. "\users" - UserController. Allows you to create and view users without authentication. It is possible to view contact information about users.
2. "\login" - LoginController. Allows you to get a JWT token for accessing protected resources using your login and password. The following user has already been created for demonstration purposes: admin/admin
3. "\admin\users" - AdminController. Allows you to access protected resources with a JWT access token

The authorization type with JWT tokens was chosen as the most up-to-date, highly secure and the standard of the development industry

**_In development:_**
- covering functional requirements with unit and integration tests
- redesigning the application from a monolithic to a microservice architecture
- containerization of services using Docker
- refining the concept of working with users (for example, the ability for new users to register with a login and password, receive access tokens to protected resources, implementing access according to their security roles)