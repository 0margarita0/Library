package com.example.library.projection;

public interface BookOverviewViewProjection {
    Long getId();
    String getName();
    String getCategory();
    String getState();
    Integer getAvailableCopies();
    String getAuthorFullName();
    String getCountryName();
}
