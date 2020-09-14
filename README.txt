1. The solution is based on Java/Maven
2. The REST service is assumed to be built upon springboot
	a. The stock quotes are posted using @PostMapping i.e. HTTP POST using the postQuote() method
	b. The endpoint for this method is "<serviceURL>/postquote"
	c. The stock quotes are queried using @GetMapping i.e. HTTP GET using the getQuotes() method
	d. The endpoint for this method is "<serviceURL>/getquotes"
3. Java ArrayList and HashMap are used to store and process data and in order to 
	minimize the amount of frameworks and libraries used
4. The unit tests are written in JUnit
5. The application can be run by the command "mvn test"
6. The time complexity is O(N*log(N)) because of Collections.sort()
7. The solution is very easy to test. Just use "mvn test". The test data can be easily changed 
	in ApplicationControllerTest.setUp() method
8. Using ArrayList and HashMap ensure a reasonable memory footprint  
9. Edge cases I encountered were: 
	a. No quotes posted within the last 10 minutes, returns empty query list