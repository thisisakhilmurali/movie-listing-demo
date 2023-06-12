package com.ust.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
	
	@NotNull
	private String movieName;
	@NotNull
	private String movieDirector;
	@NotNull
	private String movieGenre;
	@NotNull
	private String movieReleaseDate;
	@NotNull
	private String movieLanguage;
	@NotNull
	private String duration;
	@NotNull
	private String country;
	@Size(min=1,max=10)
	private double overallRate;

}
