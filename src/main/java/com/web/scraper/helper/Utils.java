package com.web.scraper.helper;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.web.scraper.entity.Courses;
import com.web.scraper.service.ScraperConstants;

public class Utils {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	/** This utility method checks if the collection object is not null and not empty and returns true, else returns false.
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Collection c) {
		if(c != null && !c.isEmpty())
			return true;
		return false;
	}
	
	/** This utility method checks if the object is not null and returns true, else returns false.
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		if(obj != null)
			return true;
		return false;
	}
	
	/** This utility method checks if the String is not null and not empty and returns true, else return false.
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str != null && !str.isEmpty())
			return true;
		return false;
	}
	
	/** This utility method gets the WebClient connto extract the websites content using HtmlUnit API.
	 * @return
	 */
	public static WebClient getWebClient() {
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setUseInsecureSSL(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setJavaScriptEnabled(false);
		return client;
	}
	
	/** This utility method returns the MongoClient after creating the connection.
	 * @return
	 */
	public static MongoClient getMongoClient() {
		return new MongoClient(ScraperConstants.MONGO_USER, ScraperConstants.MONGO_PORT);
	}
	
	/** This utility method returns the MongoDatabase from the given MongoClient.
	 * @param mongoClient
	 * @return
	 */
	public static MongoDatabase getMongoDatabase(MongoClient mongoClient) {
		return mongoClient.getDatabase(ScraperConstants.MONGO_DB);
	}
	
	/** This utility method converts JSON String into the Java Object and returns.
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static Object convertJsonToPOJO(String json) throws JsonParseException, IOException {
		return mapper.readValue(json, new TypeReference<List<Courses>>() {});
	}
	
	/** This utility method converts Java Object into JSON String and returns.
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String convertPOJOToJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
}
