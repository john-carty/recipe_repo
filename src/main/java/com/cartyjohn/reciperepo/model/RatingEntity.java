package com.cartyjohn.reciperepo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class RatingEntity implements Serializable {
    private static final long serialVersionUID = -3673939038704478178L;

    @Min(1)
    @Max(5)
    private Integer rating;
}
