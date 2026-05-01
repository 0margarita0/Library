package com.example.library.service.impl;

import com.example.library.dto.AuthorResponse;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorResponse(
                        author.getId(),
                        author.getName(),
                        author.getSurname(),
                        author.getCountry().getName()
                ))
                .toList();
    }
}
