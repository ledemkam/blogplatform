# 📝 Blog Platform

A modern, full-stack blog platform built with React/TypeScript and Spring Boot. This application provides a complete blogging solution with authentication, post management, categories, and tags.

## 🏗️ Architecture

This project follows a monorepo structure with separate frontend and backend applications:

```
blogplatform/
├── blog-frontend/    # React + TypeScript + Vite
└── blog-backend/     # Spring Boot + PostgreSQL
```

## 🚀 Technologies

### Frontend
- **React 18** with TypeScript
- **Vite** for fast development
- **Axios** for API communication
- **React Router** for navigation
- JWT-based authentication

### Backend
- **Spring Boot 3.x**
- **Spring Security** with JWT
- **Spring Data JPA**
- **PostgreSQL** database
- **Lombok** for cleaner code
- **Maven** for dependency management

## ✨ Features

- 🔐 **Authentication & Authorization** - JWT-based secure authentication
- 📄 **Post Management** - Create, read, update, and delete blog posts
- 📑 **Draft System** - Save posts as drafts before publishing
- 🏷️ **Categories & Tags** - Organize posts with categories and tags
- ⏱️ **Reading Time** - Automatic calculation of estimated reading time
- 🔍 **Filtering** - Filter posts by category and tags
- 👤 **User Management** - User profiles and author information

## 📋 Prerequisites

- **Node.js** 18+ and npm/yarn
- **Java** 17+
- **Maven** 3.8+
- **PostgreSQL** 14+
- **Git**

## 🛠️ Installation

### 1. Clone the repository

```bash
git clone https://github.com/ledemkam/blogplatform.git
cd blogplatform
```

### 2. Backend Setup

See [blog-backend/README.md](./blog-backend/README.md) for detailed backend setup instructions.

### 3. Frontend Setup

See [blog-frontend/README.md](./blog-frontend/README.md) for detailed frontend setup instructions.

## 🚀 Quick Start

### Start Backend
```bash
cd blog-backend
mvn spring-boot:run
```

Backend will run on `http://localhost:8080`

### Start Frontend
```bash
cd blog-frontend
npm install
npm run dev
```

Frontend will run on `http://localhost:5173`

## 📚 API Documentation

For detailed API documentation, see [API.md](./API.md)

### Main Endpoints

- `POST /api/v1/auth/login` - User authentication
- `GET /api/v1/posts` - Get all published posts
- `POST /api/v1/posts` - Create a new post
- `GET /api/v1/posts/{id}` - Get a specific post
- `PUT /api/v1/posts/{id}` - Update a post
- `DELETE /api/v1/posts/{id}` - Delete a post
- `GET /api/v1/categories` - Get all categories
- `GET /api/v1/tags` - Get all tags

## 🏗️ Project Structure

### Backend Structure
```
blog-backend/
├── src/main/java/com/kte/blog/
│   ├── controllers/        # REST controllers
│   ├── services/          # Business logic
│   ├── repositories/      # Data access layer
│   ├── domain/            # Domain models and DTOs
│   ├── mappers/           # Entity-DTO mappers
│   └── security/          # Security configuration
└── src/main/resources/
    └── application.properties
```

### Frontend Structure
```
blog-frontend/
├── src/
│   ├── components/        # React components
│   ├── services/          # API services
│   ├── types/            # TypeScript types
│   └── main.tsx          # Application entry point
└── index.html
```

## 🔒 Authentication

The application uses JWT (JSON Web Tokens) for authentication:

1. User logs in with email and password
2. Backend generates a JWT token (valid for 24 hours)
3. Frontend stores token in localStorage
4. Token is sent in Authorization header for protected routes

## 🤝 Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](./CONTRIBUTING.md) for details.

## 📝 License

This project is open source and available under the MIT License.

## 👤 Author

**ledemkam**
- GitHub: [@ledemkam](https://github.com/ledemkam)

## 🐛 Known Issues & Future Enhancements

- [ ] Add comment system for posts
- [ ] Implement user registration
- [ ] Add social media sharing
- [ ] Implement search functionality
- [ ] Add pagination for posts list
- [ ] Add image upload for posts

## 📞 Support

If you have any questions or issues, please open an issue on GitHub.

---

Made with ❤️ by ledemkam