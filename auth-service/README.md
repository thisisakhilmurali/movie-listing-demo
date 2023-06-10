## Movie Rating and Listing Application
---

### <u>Contributors</u>

<a href="https://github.com/thisisakhilmurali/movie-listing-demo/graphs/contributors">
<img src="https://contrib.rocks/image?repo=thisisakhilmurali/movie-listing-demo" />
</a>

<br>

---

### Microservices
* Eureka Server
* Cloud Config Server
* API Gateway
* User Service
* Admin Service
* Authentication Service
* Zipkin Tracing System

### Endpoints
* Admin
    * View Movies/Dashboard: `http://domain:port/version/admin/viewAllMovies`
    * Add a Movie: `http://domain:port/version/admin/addAMovie`
    * Update a Movie: `http://domain:port/version/admin/updateAMovie`
    * Delete a Movie: `http://domain:port/version/admin/deleteAMovie`
* User
    * View Movies/Dashboard: `http://domain:port/version/user/viewAllMovies`
    * Search Movie by Name: `http://domain:port/version/user/search/movie/{name}`
    * Search Movie by Date: `http://domain:port/version/user/search/movie/date/{date}`
    * Add a Rating to Movie: `http://domain:port/version/user/{movie_name}/rate`
* Auth