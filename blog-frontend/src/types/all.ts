// Types
export interface Category {
    id: string;
    name: string;
    postCount?: number;
  }
  
  export interface Tag {
    id: string;
    name: string;
    postCount?: number;
  }
  
  export interface Post {
    id: string;
    title: string;
    content: string;
    author?: {
      id: string;
      name: string;
    };
    category: Category;
    tags: Tag[];
    readingTime?: number;
    createdAt: string;
    updatedAt: string;
    status?: PostStatus;
  }

  export enum PostStatus {
    DRAFT = 'DRAFT',
    PUBLISHED = 'PUBLISHED'
  }
  
  export interface CreatePostRequest {
    title: string;
    content: string;
    categoryId: string;
    tagIds: string[];
    status: PostStatus;
  }
  
  export interface UpdatePostRequest extends CreatePostRequest {
    id: string;
  }
  