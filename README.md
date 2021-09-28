# JavaAssignment

The assignment project is developed using SpringBoot.

The REST APIs involved are -
POST: http://localhost:8080/swayam/api/scrape  (This API doesn't need any parameters to be passed for invokation and initiates the scraping process in asynchronous fashion)
GET: http://localhost:8080/swayam/api/courses  (This API gets all the Courses which are scraped)

Some information related to the assignment project important artifacts -
The SpringBoot bootstrap Java file with path - src/main/java/com/web/scraper/ScraperApplication.java
The RestController Java file with path - src/main/java/com/web/scraper/service/CoursesRestController
