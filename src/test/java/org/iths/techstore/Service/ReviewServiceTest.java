package org.iths.techstore.Service;

import org.iths.techstore.Model.Review;
import org.iths.techstore.Repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    // Attribute
    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService reviewService;

    // Get all reviews
    @Test
    public void getAllReviewsShouldReturnList() {
        // Arrange
        when(reviewRepository.findAll()).thenReturn(List.of(
                new Review(1L, "Samsung", 9, "Great phone", LocalDate.now(), "Psy"),
                new Review(2L, "LG Monitor", 8, "Nice colors", LocalDate.now(), "Jungkook"),
                new Review(3L, "Hyundai SSD", 7, "Fast and compact", LocalDate.now(), "G-Dragon")
        ));

        // Act
        List<Review> result = reviewService.getAllReviews();

        // Assert
        assertEquals(3, result.size());
        verify(reviewRepository).findAll();
    }

    // Get review by id
    @Test
    public void getReviewByIdShouldReturnReview() {
        // Arrange
        Review review = new Review(1L, "Samsung", 9, "Great phone", LocalDate.now(), "Psy");
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // Act
        Review result = reviewService.getReviewById(1L);

        // Assert
        assertEquals(review, result);
        verify(reviewRepository).findById(1L);
    }

    // Create review
    @Test
    public void createReviewShouldSaveReview() {
        // Arrange
        Review review = new Review(1L, "Samsung", 9, "Great phone", LocalDate.now(), "Psy");
        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review result = reviewService.createReview(review);

        // Assert
        assertEquals(review, result);
        verify(reviewRepository).save(review);
    }

    // Update review
    @Test
    public void updateReviewShouldSaveUpdatedReview() {
        // Arrange
        Review review = new Review(1L, "Samsung", 9, "Updated comment", LocalDate.now(), "Psy");
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review result = reviewService.updateReview(1L, review);

        // Assert
        assertEquals(review, result);
        verify(reviewRepository).save(review);
    }

    // Delete review
    @Test
    public void deleteReviewShouldCallDeleteByIdMethod() {
        // Arrange
        Review review = new Review(1L, "Samsung", 9, "Great phone", LocalDate.now(), "Psy");
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // Act
        reviewService.deleteReview(1L);

        // Assert
        verify(reviewRepository).deleteById(1L);
    }
}
