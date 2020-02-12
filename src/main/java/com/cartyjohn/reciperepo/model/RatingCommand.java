package com.cartyjohn.reciperepo.model;

public class RatingCommand {

    private Long id;
    private Integer rating;
    public RatingCommand(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
