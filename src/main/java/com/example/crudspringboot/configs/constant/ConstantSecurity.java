package com.example.crudspringboot.configs.constant;

public class ConstantSecurity {
    public static final String SECRET = "nXQWM7S%/5tpHq^"; // Secret key untuk sign JWT
    public static final long EXPIRATION_TIME = 86400000L * 7; // Expired 7 hari
    public static final long EXPIRATION_TIME_GOOGLE = 86400000L * 7; // Expired OAuth Google
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 15778476000L; // Expired refresh token
    public static final String BEARER_TOKEN_PREFIX = "Bearer "; // Prefix di Authorization header
    public static final String BASIC_TOKEN_PREFIX = "Basic "; // Prefix basic auth
    public static final String SIGN_UP_URL = "/users/sign-up"; // Endpoint signup
    public static final String NAME_APP = "CRUD Spring Boot"; // Nama aplikasi
}
