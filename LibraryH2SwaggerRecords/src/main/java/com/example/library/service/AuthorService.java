package com.example.library.service;

import com.example.library.dto.AuthorResponse;

import java.util.List;

public interface AuthorService {
    List<AuthorResponse> findAll();
}
