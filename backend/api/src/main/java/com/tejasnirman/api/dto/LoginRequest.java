package com.tejasnirman.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    public static class Request {
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String password;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        @java.lang.SuppressWarnings("all")
            public Request() {
        }

        @java.lang.SuppressWarnings("all")
            public void setEmail(final String email) {
            this.email = email;
        }

        @java.lang.SuppressWarnings("all")
            public void setPassword(final String password) {
            this.password = password;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof LoginRequest.Request)) return false;
            final LoginRequest.Request other = (LoginRequest.Request) o;
            if (!other.canEqual((java.lang.Object) this)) return false;
            final java.lang.Object this$email = this.getEmail();
            final java.lang.Object other$email = other.getEmail();
            if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
            final java.lang.Object this$password = this.getPassword();
            final java.lang.Object other$password = other.getPassword();
            if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
            return true;
        }

        @java.lang.SuppressWarnings("all")
            protected boolean canEqual(final java.lang.Object other) {
            return other instanceof LoginRequest.Request;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $email = this.getEmail();
            result = result * PRIME + ($email == null ? 43 : $email.hashCode());
            final java.lang.Object $password = this.getPassword();
            result = result * PRIME + ($password == null ? 43 : $password.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "LoginRequest.Request(email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
        }
    }


    public static class Response {
        private String accessToken;
        private String tokenType = "Bearer";

        public Response(String accessToken) {
            this.accessToken = accessToken;
        }

        @java.lang.SuppressWarnings("all")
            public String getAccessToken() {
            return this.accessToken;
        }

        @java.lang.SuppressWarnings("all")
            public String getTokenType() {
            return this.tokenType;
        }

        @java.lang.SuppressWarnings("all")
            public void setAccessToken(final String accessToken) {
            this.accessToken = accessToken;
        }

        @java.lang.SuppressWarnings("all")
            public void setTokenType(final String tokenType) {
            this.tokenType = tokenType;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof LoginRequest.Response)) return false;
            final LoginRequest.Response other = (LoginRequest.Response) o;
            if (!other.canEqual((java.lang.Object) this)) return false;
            final java.lang.Object this$accessToken = this.getAccessToken();
            final java.lang.Object other$accessToken = other.getAccessToken();
            if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken)) return false;
            final java.lang.Object this$tokenType = this.getTokenType();
            final java.lang.Object other$tokenType = other.getTokenType();
            if (this$tokenType == null ? other$tokenType != null : !this$tokenType.equals(other$tokenType)) return false;
            return true;
        }

        @java.lang.SuppressWarnings("all")
            protected boolean canEqual(final java.lang.Object other) {
            return other instanceof LoginRequest.Response;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $accessToken = this.getAccessToken();
            result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
            final java.lang.Object $tokenType = this.getTokenType();
            result = result * PRIME + ($tokenType == null ? 43 : $tokenType.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
            public java.lang.String toString() {
            return "LoginRequest.Response(accessToken=" + this.getAccessToken() + ", tokenType=" + this.getTokenType() + ")";
        }
    }
}
