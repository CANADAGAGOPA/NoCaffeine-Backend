package com.nocaffeine.ssgclone.review.dto.response;


import com.nocaffeine.ssgclone.review.vo.response.ReviewDetailResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDetailResponseDto {
    private Long reviewId;
    private String memberName;
    private String content;
    private int rate;
    private String createdAt;

    public static ReviewDetailResponseVo dtoToVo(ReviewDetailResponseDto reviewDetail) {
        return new ReviewDetailResponseVo(reviewDetail.getReviewId(), reviewDetail.getMemberName(),
                reviewDetail.getContent(), reviewDetail.getRate(), reviewDetail.getCreatedAt());


    }
}
