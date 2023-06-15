package com.ust.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import com.ust.project.model.Movie;
import com.ust.project.repository.MovieRepository;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

@Service
public class MovieService {
	
	@Value("${s3.bucket.name}")
	private String bucketName;
 
    @Value("${aws.s3.folder}")
    private String folderName;

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(String name, String duration, MultipartFile imageFile) {
        // Upload image file to AWS S3
        String imageFileName = generateUniqueFileName(imageFile.getOriginalFilename());
        uploadImageToS3(imageFile, imageFileName);

        // Create Movie object and save it in the database
        Movie movie = new Movie(name, duration, generateS3ImageUrl(imageFileName));
        movieRepository.save(movie);
    }
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }


  

    private String generateUniqueFileName(String originalFilename) {
        // Generate a unique filename for the image using a UUID
        String uniqueFilename = UUID.randomUUID().toString();

        // Extract the file extension from the original filename, if present
        String fileExtension = "";
        int extensionIndex = originalFilename.lastIndexOf('.');
        if (extensionIndex >= 0 && extensionIndex < originalFilename.length() - 1) {
            fileExtension = originalFilename.substring(extensionIndex + 1);
        }

        // Append the file extension to the unique filename, if available
        if (!fileExtension.isEmpty()) {
            uniqueFilename += "." + fileExtension;
        }

        return uniqueFilename;
    }


    private void uploadImageToS3(MultipartFile imageFile, String imageFileName) {
        try {
            S3Client s3Client = S3Client.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(() -> AwsBasicCredentials.create("AKIA4GO4DIU3FAXYUPC2", "jNaHA9OB4VzSHDmNinJhsY8VsqjWpVUOFmrwhWku"))
                    .build();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderName + "/" + imageFileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(imageFile.getBytes()));
        } catch (Exception e) {
            System.out.println("Exception occurred during S3 upload: " + e.getMessage());
        }
    }
    
    public void updateMovie(Long id, String name, String duration, MultipartFile imageFile) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + id));
        
        // Delete existing image from AWS S3
        deleteImageFromS3(movie.getImageUrl());
        
        // Upload new image file to AWS S3
        String imageFileName = generateUniqueFileName(imageFile.getOriginalFilename());
        uploadImageToS3(imageFile, imageFileName);
        
        // Update movie details
        movie.setName(name);
        movie.setDuration(duration);
        movie.setImageUrl(generateS3ImageUrl(imageFileName));
        
        movieRepository.save(movie);
    }
    
    public void deleteMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + id));

        // Delete image from AWS S3
        deleteImageFromS3(movie.getImageUrl());

        // Delete the movie from the database
        movieRepository.delete(movie);
    }
    
    private String extractImageFileName(String imageUrl) {
        String[] parts = imageUrl.split("/");
        String lastPart = parts[parts.length - 1];
        return lastPart;
    }


    
    
    private void deleteImageFromS3(String imageUrl) {
        try {
            String imageFileName = extractImageFileName(imageUrl);

            S3Client s3Client = S3Client.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(() -> AwsBasicCredentials.create("AKIA4GO4DIU3FAXYUPC2", "jNaHA9OB4VzSHDmNinJhsY8VsqjWpVUOFmrwhWku"))
                    .build();

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderName + "/" + imageFileName)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            System.out.println("Exception occurred during S3 image deletion: " + e.getMessage());
        }
    }

    
//    private String extractBucketFromImageUrl(String imageUrl) {
//        String[] parts = imageUrl.split("/");
//        if (parts.length >= 3) {
//            return parts[2];
//        }
//        throw new IllegalArgumentException("Invalid image URL: " + imageUrl);
//    }
//    
//    private String extractKeyFromImageUrl(String imageUrl) {
//        String[] parts = imageUrl.split("/");
//        if (parts.length >= 5 && parts[3].equals("images")) {
//            return parts[3];
//        }
//        throw new IllegalArgumentException("Invalid image URL: " + imageUrl);
//    }


    
    
    
    private String generateS3ImageUrl(String imageFileName) {
        return "https://s3.amazonaws.com/" + bucketName + "/" + folderName + "/" + imageFileName;
    }

}

