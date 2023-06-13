## Movie Rating and Listing Application
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
    * Register new User: `http://domain:port/version/registerNewUser`
    * Authenticate (Admin/User): `http://domain:port/version/authenticate`
    * With JWT: `http://domain:port/version/forUser` / `http://domain:port/version/forAdmin`