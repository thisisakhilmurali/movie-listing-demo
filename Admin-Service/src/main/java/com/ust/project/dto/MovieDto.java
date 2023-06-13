package com.ust.project.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
	
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9-]+$",message="Movie name should not be null")
	private String movieName;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9\\-.,]+$",message="Director name should not be null")
	private String movieDirector;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]+$",message="Genre should  contain alphabetic characters only")
	private String movieGenre;
	@NotNull
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message="Release date  should be in the format :'YYYY-MM-DD")
	private String movieReleaseDate;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]+$",message="Movie Language should  contain alphabetic characters only")
	private String movieLanguage;
	@NotNull
	@Pattern(regexp = "^\\d+$",message="Duration should  only contain numeric digits (0-9)")
	private String duration;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]+$",message="Country name  should only contain alphabetic characters")
	private String country;
	@NotNull
	//@Pattern(regexp = "^\\d{1,10}$", message = "Rating must be between 1 and 10")
	
	//@Pattern(regexp = "^(?:10(?:\\.0+)?|[1-9](?:\\.\\d+)?)$")
	@Pattern(regexp = "^(?:10(?:\\.0+)?|[1-9](?:\\.\\d+)?)$")
	//@Min(1)
	//@Max(10)
	private double overallRate;

}
