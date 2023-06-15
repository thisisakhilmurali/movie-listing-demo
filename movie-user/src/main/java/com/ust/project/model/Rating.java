package com.ust.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RATING_TABLE")
@Entity
public class Rating {
	@Id
	@GeneratedValue
	private long ratingId;
	private long userId;
	private long movieId;
	private double rating;
	private String message;

}
