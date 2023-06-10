package com.ust.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingDto {
	private long movieId;
	private long userId;
	private double rating;
	private String message;

}
