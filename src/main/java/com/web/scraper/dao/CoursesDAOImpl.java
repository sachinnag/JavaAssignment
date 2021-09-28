package com.web.scraper.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.web.scraper.entity.Courses;
import com.web.scraper.helper.Utils;
import com.web.scraper.service.ScraperConstants;

@Repository
public class CoursesDAOImpl implements CoursesDAO {
	
	/**
	 * The method inserts the list of Courses into the MongoDB after dropping the collection as a refreshed data.
	 */
	@Override
	public void saveCourses(List<Courses> courses) throws Exception {
		
		String json = null;
		List<Document> documents = new ArrayList<>();
		
		for(Courses course : courses) {
			// Iterates each Course and converts it to JSON
			json = Utils.convertPOJOToJson(course);
			
			// Parses the Course JSON into Document object and stores into list of Document object
			if(Utils.isNotEmpty(json)) {
				Document doc = Document.parse(json);
				documents.add(doc);
			}
		}
		
		if(Utils.isNotEmpty(documents)) {
			MongoClient mongoClient = null;
			
			try {
				// Gets the MongoClient
				mongoClient = Utils.getMongoClient();
				
				// Gets the MongoDatabse
				MongoDatabase mongoDatabase = Utils.getMongoDatabase(mongoClient);
				
				// Gets the MongoCollection and Drops it
				mongoDatabase.getCollection(ScraperConstants.MONGO_COLLECTION).drop();
				
				// Gets the MongoCollection and inserts list of Document object
				mongoDatabase.getCollection(ScraperConstants.MONGO_COLLECTION).insertMany(documents);
			}
			finally {
				if(mongoClient != null) {
					mongoClient.close();
				}
				
			}
		}
	}

	/**
	 * The method returns all the Courses from the MongoDB collection.
	 */
	@Override
	public List<Courses> getAllCourses() {
		
		List<Courses> courses = null;
		MongoClient mongoClient = null;
		
		try {
			// Creates a CodecRegistry for parsing the BSON type data into Java POJO
			CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), 
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			
			mongoClient = Utils.getMongoClient();
			
			// Gets the Database with the created CodecRegistry
			MongoDatabase database = (MongoDatabase) Utils.getMongoDatabase(mongoClient).withCodecRegistry(codecRegistry);
			
			// Gets the Collection with the class type as Courses
			MongoCollection<Courses> collection = database.getCollection(ScraperConstants.MONGO_COLLECTION, Courses.class);
			
			// Finds all the Documents in the Collection, converts the each Document object into Java Object and stores into a list
			courses = collection.find(new Document(), Courses.class).into(new ArrayList<Courses>());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(mongoClient != null)
				mongoClient.close();
		}
		
		return courses;
	}
	
}
