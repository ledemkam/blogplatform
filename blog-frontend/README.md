# 🎨 Blog Platform - Frontend

Modern React frontend built with **TypeScript** and **Vite** for the Blog Platform application.

## 🏗️ Technology Stack

- **React 18** - UI library
- **TypeScript** - Type safety
- **Vite** - Build tool and dev server
- **Axios** - HTTP client
- **React Router** - Routing
- **CSS Modules** - Component styling

## 📋 Prerequisites

- Node.js 18+ 
- npm 9+ (or yarn/pnpm)

## 🚀 Getting Started

### Installation

```bash
# Install dependencies
npm install
```

### Development

```bash
# Start development server
npm run dev
```

The application will be available at: **http://localhost:5173**

### Build for Production

```bash
# Create production build
npm run build

# Preview production build
npm run preview
```

### Linting

```bash
# Run ESLint
npm run lint
```

## 📁 Project Structure

```
blog-frontend/
├── public/              # Static assets
├── src/
│   ├── components/      # React components
│   │   ├── common/      # Shared components
│   │   ├── layout/      # Layout components
│   │   └── posts/       # Post-related components
│   ├── pages/           # Page components
│   │   ├── Home.tsx
│   │   ├── Login.tsx
│   │   ├── PostDetail.tsx
│   │   ├── CreatePost.tsx
│   │   └── EditPost.tsx
│   ├── services/        # API services
│   │   └── apiService.ts
│   ├── types/           # TypeScript types/interfaces
│   │   └── index.ts
│   ├── utils/           # Utility functions
│   ├── styles/          # Global styles
│   ├── App.tsx          # Root component
│   └── main.tsx         # Entry point
├── index.html
├── package.json
├── tsconfig.json        # TypeScript configuration
└── vite.config.ts       # Vite configuration
```

## 🔐 Authentication

The frontend uses JWT token-based authentication:

1. User logs in via the login form
2. JWT token is received and stored in `localStorage`
3. Token is automatically included in API requests via Axios interceptor
4. On 401 errors, user is redirected to login page

### Auth Flow

```typescript
// Login
const response = await apiService.login({ email, password });
// Token is automatically saved to localStorage

// Authenticated requests include token automatically
const posts = await apiService.getPosts();

// Logout
apiService.logout(); // Removes token from localStorage
```

## 📡 API Service

The `ApiService` class handles all API communications:

```typescript
import ApiService from './services/apiService';

const api = ApiService.getInstance();

// Authentication
await api.login({ email, password });
api.logout();

// Posts
const posts = await api.getPosts({ categoryId, tagId });
const post = await api.getPost(id);
const newPost = await api.createPost(postData);
const updated = await api.updatePost(id, postData);
await api.deletePost(id);
const drafts = await api.getDrafts();

// Categories
const categories = await api.getCategories();
const category = await api.createCategory(data);
await api.deleteCategory(id);

// Tags
const tags = await api.getTags();
const tag = await api.createTag(data);
await api.deleteTag(id);
```

## 🎨 Component Examples

### Creating a Post

```tsx
import { useState } from 'react';
import ApiService from '../services/apiService';

function CreatePost() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const api = ApiService.getInstance();
    
    await api.createPost({
      title,
      content,
      status: 'PUBLISHED',
      categoryId: 'uuid-here',
      tagIds: ['tag1-uuid', 'tag2-uuid']
    });
  };
  
  return (
    <form onSubmit={handleSubmit}>
      {/* Form fields */}
    </form>
  );
}
```

## 🔧 Configuration

### Environment Variables

Create a `.env` file in the root:

```env
VITE_API_BASE_URL=http://localhost:8080
```

### Proxy Configuration (Development)

Vite config includes proxy for API requests:

```typescript
// vite.config.ts
export default defineConfig({
  server: {
    proxy: {
      '/api': 'http://localhost:8080'
    }
  }
})
```

## 🎭 TypeScript Types

Main types used in the application:

```typescript
interface Post {
  id: string;
  title: string;
  content: string;
  status: 'DRAFT' | 'PUBLISHED';
  readingTime: number;
  author: User;
  category: Category;
  tags: Tag[];
  createdAt: string;
  updatedAt: string;
}

interface User {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
}

interface Category {
  id: string;
  name: string;
  description?: string;
}

interface Tag {
  id: string;
  name: string;
}

interface LoginRequest {
  email: string;
  password: string;
}

interface AuthResponse {
  token: string;
  expiresIn: number;
}
```

## 🧪 Testing

```bash
# Run tests
npm run test

# Run tests with coverage
npm run test:coverage

# Run tests in watch mode
npm run test:watch
```

## 🚢 Deployment

### Build and Deploy

```bash
# Create production build
npm run build

# The dist/ folder contains optimized static files
# Deploy to:
# - Vercel: vercel deploy
# - Netlify: netlify deploy --prod --dir=dist
# - GitHub Pages: gh-pages -d dist
```

### Environment Setup

For production, set environment variables:
- `VITE_API_BASE_URL` - Your production API URL

## 🎯 Features

- ✅ User authentication (login/logout)
- ✅ View published posts
- ✅ Filter posts by category/tag
- ✅ View post details
- ✅ Create new posts (draft or published)
- ✅ Edit existing posts
- ✅ Delete posts
- ✅ Manage drafts
- ✅ Reading time display
- ✅ Responsive design

## 🐛 Troubleshooting

### CORS Issues
- Ensure backend CORS is configured properly
- Check proxy configuration in `vite.config.ts`

### API Connection Failed
- Verify backend is running on port 8080
- Check `VITE_API_BASE_URL` environment variable

### Build Errors
```bash
# Clear node_modules and reinstall
rm -rf node_modules package-lock.json
npm install
```

## 📚 Additional Resources

- [React Documentation](https://react.dev)
- [Vite Documentation](https://vitejs.dev)
- [TypeScript Documentation](https://www.typescriptlang.org)
- [Axios Documentation](https://axios-http.com)

---

For backend documentation, see [../blog-backend/README.md](../blog-backend/README.md)