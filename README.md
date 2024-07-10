# Social Media API

This is a Spring Boot API for a social media application that provides endpoints for user authentication, post management, and user management.

## Base URL 
https://spring-boot-app-production-f847.up.railway.app/


## Endpoints

### Authentication Endpoints

#### Sign Up
- **URL:** `/auth/signup`
- **Method:** `POST`
- **Description:** Creates a new user account.
- **Request Body:**
  ```json
  {
      "firstName": "sachin",
      "lastname": "nagar",
      "email": "sachin.nagar@example.com",
      "password": "password123",
      "gender": "male"
  }
## Response 
{
    "token": "jwt-token",
    "message": "Registration Successful"
}
## URL: /auth/login
## Method: POST

## Request Body:

{
    "email": "sachin.nagar@example.com",
    "password": "password123"
}
## Post Endpoints
## Create Post
## URL: /post/user/{userId}
## Method: POST
## Description: Creates a new post for a user.
## Request Body:
{
    "image" : "Post URL",
    "caption" : "my Image"
}

## Response Body:
{
  "id": 1,
  "caption": "my Image",
  "image": "https://media-bom2-2.cdn.whatsapp.net/v/t61.24694-24/356870021_990668022073100_1481580994782060614_n.jpg?ccb=11-4&oh=01_AdQYPYeh5uOuj5SvaxnYsY2xwLa9VmvYj_VqT9JMhsbl1A&oe=65F67E93&_nc_sid=e6ed6c&_nc_cat=106",
  "video": null,
  "user": {
    "id": 1,
    "firstName": "sachin",
    "lastname": "nagar",
    "email": "sachin.nagar@gmail.com",
    "password": "$2a$10$hIvVhazW3ocQ2kNqxeTsP.IdurjZl8g/sauMmvsW0qILwEkNFOjiG",
    "gender": "male",
    "followers": [],
    "following": []
  },
  "cretedAt": "2024-07-10T15:08:39.321387932",
  "liked": []
}

## Get Post by ID
URL: /posts/{postId}
Method: GET
Description: Retrieves a post by its ID.
Response: Post object
## Get Posts by User
URL: /posts/user/{userId}
Method: GET
Description: Retrieves all posts by a specific user.
Response: List of Post objects
## Get All Posts
URL: /posts
Method: GET
Description: Retrieves all posts.
Response: List of Post objects
Save Post
URL: /posts/save/{postId}/user/{userId}
Method: PUT
Description: Saves a post.
Response: Post object
## Like Post
URL: /posts/like/{postId}/user/{userId}
Method: PUT
Description: Likes a post.
Response: Post object
User Endpoints
## Get All Users
URL: /api/users
Method: GET
Description: Retrieves all users.
Response: List of User objects
## Get User by ID
URL: /api/user/{userId}
Method: GET
Description: Retrieves a user by their ID.
Response: User object
Update User
URL: /api/user/{userId}
Method: PUT
Description: Updates a user's information.
## Request Body:
json
Copy code
{
    "firstName": "UpdatedName",
    "lastname": "UpdatedLastName",
    "email": "updated.email@example.com",
    "password": "newPassword123",
    "gender": "male"
}
Response: User object
## Follow User
URL: /api/users/follow/{userId1}/{userId2}
Method: PUT
Description: User with ID userId1 follows user with ID userId2.
Response: User object
## Search Users
URL: /api/users/search
Method: GET
Description: Searches for users by query.
Query Parameters: query (string)
Response: List of User objects
## Delete User
URL: /api/user/{userId}
Method: DELETE
Description: Deletes a user by ID.
## Response:
json
Copy code
{
    "message": "User deleted successfully"
}
Additional Information
Ensure that you include the JWT token in the Authorization header as Bearer {token} for all authenticated endpoints.
The API uses Spring Security for authentication and authorization.
Passwords are securely hashed using a password encoder.
This API is designed to help you create your own social media application. Use the endpoints provided to build and manage users, posts, and more.
