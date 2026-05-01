package com.example.library.projection;

public interface CategoryStatisticsProjection{
    String getCategory();
    Long getTotalBooks();
    Long getTotalAvailableCopies();
    Long getBadStateBooks();
}
