# 🤝 Contributing to Blog Platform

Thank you for your interest in contributing to Blog Platform! This document provides guidelines and instructions for contributing.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Setup](#development-setup)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Reporting Bugs](#reporting-bugs)
- [Suggesting Features](#suggesting-features)

---

## 🤝 Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inclusive environment for all contributors.

### Expected Behavior

- Be respectful and considerate
- Use welcoming and inclusive language
- Accept constructive criticism gracefully
- Focus on what is best for the community
- Show empathy towards other community members

### Unacceptable Behavior

- Harassment, discrimination, or offensive comments
- Trolling or insulting comments
- Publishing others' private information
- Other conduct which could reasonably be considered inappropriate

---

## 🎯 How Can I Contribute?

### Reporting Bugs

Found a bug? Help us fix it!

1. **Check existing issues** - Someone might have already reported it
2. **Create a new issue** with:
   - Clear, descriptive title
   - Steps to reproduce
   - Expected vs actual behavior
   - Screenshots (if applicable)
   - Environment details (OS, browser, Java version, etc.)

### Suggesting Features

Have an idea for a new feature?

1. **Check existing issues** - It might already be proposed
2. **Open a feature request** with:
   - Clear description of the feature
   - Why it would be useful
   - Possible implementation approach
   - Examples or mockups (if applicable)

### Code Contributions

1. **Find an issue** to work on or create one
2. **Comment on the issue** to let others know you're working on it
3. **Fork the repository**
4. **Create a branch** from `main`
5. **Make your changes**
6. **Test thoroughly**
7. **Submit a pull request**

### Documentation

Help improve our documentation:

- Fix typos or unclear explanations
- Add examples or tutorials
- Improve API documentation
- Translate documentation

---

## 🛠️ Development Setup

### Prerequisites

- Java 17+
- Node.js 16+
- PostgreSQL 12+
- Maven 3.6+
- Git

### Clone and Setup

```bash
# Fork the repository on GitHub, then clone your fork
git clone https://github.com/YOUR_USERNAME/blogplatform.git
cd blogplatform

# Add upstream remote
git remote add upstream https://github.com/ledemkam/blogplatform.git
```

### Backend Setup

```bash
cd blog-backend

# Create database
createdb blog_db

# Configure application.properties
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit application.properties with your database credentials

# Install dependencies and run
mvn clean install
mvn spring-boot:run
```

### Frontend Setup

```bash
cd blog-frontend

# Install dependencies
npm install

# Create .env file
cp .env.example .env
# Edit .env if needed

# Start development server
npm run dev
```

### Verify Setup

- Backend: http://localhost:8080
- Frontend: http://localhost:5173
- Database: Connection to PostgreSQL successful

---

## 📝 Coding Standards

### Java (Backend)

#### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Maximum line length: 120 characters
- Use 4 spaces for indentation

#### Best Practices

```java
// ✅ Good
public List<Post> getPublishedPosts(UUID categoryId) {
    if (categoryId == null) {
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }
    return postRepository.findAllByStatusAndCategoryId(
        PostStatus.PUBLISHED, 
        categoryId
    );
}

// ❌ Bad
public List<Post> getPosts(UUID c) {
    if(c==null)return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    return postRepository.findAllByStatusAndCategoryId(PostStatus.PUBLISHED,c);
}
```

#### Key Principles

- Use `@Transactional` for database operations
- Handle exceptions appropriately
- Use Lombok annotations to reduce boilerplate
- Write service tests for business logic
- Use constructor injection with `@RequiredArgsConstructor`

### TypeScript (Frontend)

#### Code Style

- Use TypeScript strict mode
- Prefer `const` over `let`, avoid `var`
- Use meaningful variable names
- Maximum line length: 100 characters
- Use 2 spaces for indentation

#### Best Practices

```typescript
// ✅ Good
interface Post {
  id: string;
  title: string;
  content: string;
}

const fetchPost = async (id: string): Promise<Post> => {
  try {
    const response = await api.getPost(id);
    return response;
  } catch (error) {
    console.error('Failed to fetch post:', error);
    throw error;
  }
};

// ❌ Bad
const fetchPost = async (id) => {
  let response = await api.getPost(id);
  return response;
}
```

#### React Components

- Use functional components with hooks
- Extract reusable logic into custom hooks
- Keep components focused and single-purpose
- Use TypeScript for prop types

```typescript
// ✅ Good
interface PostCardProps {
  post: Post;
  onDelete?: (id: string) => void;
}

export const PostCard: React.FC<PostCardProps> = ({ post, onDelete }) => {
  return (
    <div className="post-card">
      <h2>{post.title}</h2>
      <p>{post.content}</p>
      {onDelete && (
        <button onClick={() => onDelete(post.id)}>Delete</button>
      )}
    </div>
  );
};
```

---

## 📝 Commit Guidelines

### Commit Message Format

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

### Examples

```bash
feat(posts): add pagination to post list

Add pagination support with page size of 10 posts per page.
Includes backend endpoint changes and frontend UI updates.

Closes #123

---

fix(auth): resolve token expiration issue

Fixed bug where expired tokens were not properly cleared
from localStorage, causing authentication errors.

Fixes #456

---

docs(readme): update installation instructions

Added more detailed steps for PostgreSQL setup and
environment variable configuration.
```

### Best Practices

- Write clear, concise commit messages
- Use present tense ("add feature" not "added feature")
- Reference issue numbers
- Keep commits focused and atomic
- Don't commit commented-out code
- Don't commit sensitive information (passwords, API keys)

---

## 🔄 Pull Request Process

### Before Submitting

1. **Update from upstream**

```bash
git fetch upstream
git rebase upstream/main
```

2. **Test your changes**

```bash
# Backend
mvn test

# Frontend
npm test
npm run build
```

3. **Review your changes**

```bash
git diff upstream/main
```

### Creating a Pull Request

1. **Push to your fork**

```bash
git push origin your-branch-name
```

2. **Open a Pull Request** on GitHub

3. **Fill out the PR template** with:
   - Description of changes
   - Related issue numbers
   - Testing performed
   - Screenshots (if UI changes)

### PR Title Format

Follow the same format as commit messages:

```
feat(posts): add pagination support
fix(auth): resolve token expiration bug
docs: update API documentation
```

### PR Description Template

```markdown
## Description
Brief description of what this PR does.

## Related Issue
Closes #123

## Changes Made
- Added pagination to post endpoint
- Updated frontend to display page controls
- Added tests for pagination logic

## Testing
- [ ] Tested locally
- [ ] All tests pass
- [ ] Manually tested in browser
- [ ] Checked for console errors

## Screenshots
(if applicable)

## Checklist
- [ ] Code follows project style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex logic
- [ ] Documentation updated
- [ ] No new warnings
- [ ] Tests added/updated
```

### Review Process

1. **Automated checks** must pass (if configured)
2. **Code review** by maintainers
3. **Requested changes** should be addressed
4. **Approval** from at least one maintainer
5. **Merge** by maintainer

### After Your PR is Merged

1. Delete your branch
2. Update your local repository

```bash
git checkout main
git pull upstream main
git push origin main
```

---

## 🧪 Testing

### Backend Tests

```bash
cd blog-backend

# Run all tests
mvn test

# Run specific test
mvn test -Dtest=PostServiceTest

# Run with coverage
mvn test jacoco:report
```

### Frontend Tests

```bash
cd blog-frontend

# Run tests
npm test

# Run with coverage
npm test -- --coverage

# Run in watch mode
npm test -- --watch
```

### Writing Tests

#### Backend Example

```java
@Test
void shouldCreatePost() {
    // Given
    User user = new User();
    CreatePostRequest request = CreatePostRequest.builder()
        .title("Test Post")
        .content("Test content")
        .status(PostStatus.DRAFT)
        .build();
    
    // When
    Post result = postService.createPost(user, request);
    
    // Then
    assertNotNull(result.getId());
    assertEquals("Test Post", result.getTitle());
    assertEquals(PostStatus.DRAFT, result.getStatus());
}
```

#### Frontend Example

```typescript
test('displays post title', () => {
  const post = {
    id: '1',
    title: 'Test Post',
    content: 'Content',
    // ...
  };
  
  render(<PostCard post={post} />);
  
  expect(screen.getByText('Test Post')).toBeInTheDocument();
});
```

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev/)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [GitHub Flow](https://guides.github.com/introduction/flow/)

---

## ❓ Questions?

If you have questions:

1. Check existing documentation
2. Search closed issues
3. Open a new issue with the `question` label
4. Join our discussions (if available)

---

## 🙏 Thank You!

Your contributions make this project better for everyone. We appreciate your time and effort!

**Happy Contributing! 🎉**