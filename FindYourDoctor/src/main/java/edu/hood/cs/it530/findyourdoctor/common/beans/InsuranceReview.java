package edu.hood.cs.it530.findyourdoctor.common.beans;

import java.util.Date;

public class InsuranceReview {

    private Insurance insurance;
    
    private Date reviewDate;
    
    private int ratings;
    
    private String comments;
    

    

    /**
     * @return the reviewDate
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * @param reviewDate the reviewDate to set
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * @return the ratings
     */
    public int getRatings() {
        return ratings;
    }

    /**
     * @param ratings the ratings to set
     */
    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * @return the insurance
     */
    public Insurance getInsurance() {
        return insurance;
    }

    /**
     * @param insurance the insurance to set
     */
    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
    
}
