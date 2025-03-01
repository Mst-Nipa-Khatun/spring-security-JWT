JWT(JSON Web Token)
What is JWT?
JWT (JSON Web Token) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object.

It ensures our security. If we log in to Server B from Client A using a username and password, we have to log in repeatedly. But if we use the JWT library, after logging into the server, it provides us with a token, which is encrypted.

This token ensures whether I am valid or not, and if I am valid, it checks the token and provides the necessary data. JWT is mainly used for authentication and authorization in web applications and APIs.

A JWT token has three parts and is provided as an encoded string, which can be decoded in three ways.

First-Header
{
"alg": "HS256",
"typ": "JWT"
}
alg: The algorithm used for signing (e.g., HS256, RS256).
typ: The type of token (always JWT).

Second- PayLoad
{ "sub": "1234567890",
"name": "John Doe",
"iat": 1710123456, // Issued at
"exp": 1710127456 // Expiration time }

3rd-Singnature:
HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)


Pros and Cons of JWT (JSON Web Token)
Pros of JWT
1. Stateless Authentication (No Server-Side Storage)
   JWT does not require server-side session storage.
   Ideal for scalable applications and microservices architectures.
2. Compact & Fast (URL-Safe Format)
   JWT is a lightweight token encoded in Base64, eta easily send kora jay via HTTP headers.
   Used in web, mobile, and API calls.
3. Secure (If Implemented Correctly)
   JWT supports HMAC (HS256) or RSA (RS256) for signing.
   JWE is Encrypted to provide extra security.
4. Works Across Different Domains (Cross-Domain Authentication)
   JWT is self-contained it can be verified anywhere .
   Useful for Single Sign-On (SSO) systems.

5. Expiry and Custom Claims
   JWT supports custom claims, allowing you to store user roles, permissions, and metadata.

Cons of JWT:

1. Difficult to Revoke (Security Concern)
   Once a JWT is issued, it cannot be revoked easily until it expires.
   If a JWT gets stolen, the attacker can use it until expiration.
   Solution: Use short-lived tokens + refresh tokens.

2. Large Token Size (More Data in Requests)
   JWT contains header + payload + signature, making it larger.
   This can increase bandwidth usage, especially in mobile networks.
   Solution: Minimize token payload size.

3. Security Risks (XSS, CSRF, and Token Theft)
   JWTs do not have built-in protection against CSRF (Cross-Site Request Forgery).

4. No Built-in Session Management
   JWT does not support automatic session invalidation like traditional server-side sessions.
   Solution: Implement a blacklist or token revocation strategy.
5. Requires Proper Implementation
   Incorrect implementation (e.g., using long-lived tokens without proper security) can introduce serious vulnerabilities.
   Solution: Always sign and validate tokens correctly.

When we use JWT:
Working with stateless APIs (RESTful, GraphQL, Microservices).
When Need scalability without server-side session storage.
Require cross-domain authentication (SSO).

Do not use JWT:
When we need frequent session invalidation (e.g., banking apps).
We have short-lived sessions with minimal data (a simple session ID may work better).

If i don’t use JWT in my application, Some problems might be Faced:

1. Loss of Stateless Authentication
2. Reduced API Security & Scalability
3. Performance and Speed Issues
4. Difficulties in Single Sign-On (SSO)
5. Harder Mobile & Frontend Authentication
6. Logout & Token Invalidation Issues
   If i won't us JWT, might face scalability, security, and performance issues, especially in mobile apps, microservices, or high-traffic applications. Session-based authentication works well for small web applications, but modern applications benefit more from JWT.

Real-Life Example: JWT vs. Session-Based Authentication

Example 1: Traditional E-commerce (Session-Based Authentication)
Scenario:

How it works (Session-based authentication):
1️. User logs in using email and password.
2️. The server creates a session ID and stores it in a database or memory (like Redis).
3️. A cookie is sent to the user’s browser with the session ID.
4️. For every request (like adding items to the cart), the browser sends the session ID, and the server verifies it.
5️. If the user logs out, the session is deleted from the server, and the user must log in again.

Problems with session-based authentication:

Scalability issue: If millions of users are logged in, storing session data in memory or a database puts extra load on the server.
Load balancing issues: If one server stores the session, and a user request goes to a different server, the session will not be recognized.
Mobile app authentication is difficult because cookies work best for web browsers.
Best for small web applications where users log in from the same browser.




Example 2: Modern E-commerce (JWT-Based Authentication)
Scenario:

How it works (JWT-based authentication)?
1️. User logs in using email and password.
2️. Instead of creating a session, the server generates a JWT token containing user information and an expiration time.
3️. The JWT token is sent to the user (via HTTP header or local storage).
4️. For every request, the user sends the JWT token in the Authorization header instead of a session ID.
5️.The server does not need to store sessions—it only verifies the JWT token.

Advantages of JWT in this case:

Scalability: No need to store sessions on the server, making it ideal for high-traffic apps.
Load balancing friendly: Any server can validate the JWT without storing session data.
Works across mobile & web apps: Since JWT is stateless, it can be used for mobile authentication easily.
Easy integration with APIs: Perfect for microservices and REST APIs.
.
Another Real-Life Example: Facebook Login

Session-Based (Old System)
When you log in to Facebook on your browser, it creates a session and stores it as a cookie.
If you close your browser and reopen Facebook, it checks the session ID and logs you in again.
If you log in from a different device, Facebook has to create a new session.

JWT-Based (Modern System, Mobile + Web)
Facebook now uses OAuth & JWT tokens for authentication.
When you log in, Facebook gives you an access token (JWT).
If you use Facebook on your mobile app, the same JWT token is used, so you don’t need to log in again. If you use Facebook to log in to Instagram or another service, it sends the JWT token instead of a session.






Which is More Secure: JWT or Session-Based Authentication?

Both JWT (JSON Web Token) and Session-Based Authentication have their own security strengths and weaknesses. The more secure option depends on the use case. Below is a comparison based on different security aspects.

JWT is more secure for APIs, mobile apps, and scalable applications.
Session-based authentication is more secure for traditional web applications that require easy session management.

