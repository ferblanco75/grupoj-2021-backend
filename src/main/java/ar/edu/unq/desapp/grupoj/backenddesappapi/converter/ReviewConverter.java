package ar.edu.unq.desapp.grupoj.backenddesappapi.converter;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.ReviewDTO;

import java.util.List;
import java.util.stream.Collectors;


public class ReviewConverter {

    public static List<ReviewDTO> convertToReviewDTO(List<Review> reviews) {
        return reviews.stream().map(review ->
                ReviewDTO.builder()
                        .titleId(review.getTitleId())
                        .text(review.getText())
                        .textExtended(review.getTextExtended())
                        .rating(review.getRating())
                        .spoilerAlert(review.getSpoilerAlert())
                        .language(review.getLanguage())
                        .build()
        ).collect(Collectors.toList());
    }



    /*public static Review convertToReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setTitleId(reviewDTO.getTitleId());
        review.setDate(reviewDTO.getDate());
        return review;
    }*/

}

