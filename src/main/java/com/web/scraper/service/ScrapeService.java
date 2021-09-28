package com.web.scraper.service;

import java.util.concurrent.CompletableFuture;

public class ScrapeService {
	
	
	/**
	 * The method invokes the scraping process Asynchronously.
	 */
	public void initiateScrapeSerivce() {
		CompletableFuture.runAsync(() -> {
			new CoursesParser().init();
		});
	}
}
