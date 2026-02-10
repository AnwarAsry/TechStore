package org.iths.techstore.Service;

import org.iths.techstore.Model.Review;
import org.iths.techstore.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewMissingException(id));
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review review) {
        Review found = reviewRepository.findById(id).orElseThrow(() -> new ReviewUpdateFailException(id));

        review.setId(found.getId());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.findById(id).orElseThrow(() -> new ReviewMissingException(id));

        reviewRepository.deleteById(id);
    }
}
