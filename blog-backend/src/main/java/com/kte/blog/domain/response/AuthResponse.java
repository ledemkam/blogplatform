package com.kte.blog.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private long expiresIn;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiErrorResponse {
        private int status;
        private String message;
        private List<FieldError> errors;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class FieldError {
            private String field;
            private String message;
        }
    }
}
