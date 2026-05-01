package com.example.library.listener;

import com.example.library.event.BookRentedEvent;
import com.example.library.model.ActivityLog;
import com.example.library.repository.ActivityLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventListener {

    private static final Logger log = LoggerFactory.getLogger(BookEventListener.class);

    private final ActivityLogRepository activityLogRepository;

    public BookEventListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @EventListener
    public void handleBookRented(BookRentedEvent event) {
        log.info("Book rented: {}", event.getBookName());

        activityLogRepository.save(
                new ActivityLog(event.getBookName(), "BOOK_RENTED")
        );

        if (event.getRemainingCopies() == 0) {
            log.warn("Book is now unavailable: {}", event.getBookName());

            activityLogRepository.save(
                    new ActivityLog(event.getBookName(), "BOOK_UNAVAILABLE")
            );
        }
    }
}