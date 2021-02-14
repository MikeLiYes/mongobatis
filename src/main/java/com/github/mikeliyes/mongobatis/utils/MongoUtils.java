package com.github.mikeliyes.mongobatis.utils;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;

import com.github.mikeliyes.mongobatis.model.DataSource;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoUtils {
	
	private static MongoClient mongoClient;
	
	private static MongoDatabase db;
	
	public static void initMongoUtils(DataSource source) {
		if (mongoClient == null) {
			synchronized(MongoUtils.class){
				if (mongoClient == null) {
					mongoClient = MongoClients.create("mongodb://"+source.getUrl());
				}
				
			}			
		}
		
		db = mongoClient.getDatabase(source.getDbName());
	}
	
	public static MongoCollection<Document> getCollection(String collectionName) {
        return db.getCollection(collectionName);  
    }
	
	/**
     * aggregate
     * @param collectionName
     * @param pipeline
     * @return
     */
    public static List<Object> aggregate(String collectionName,List<BsonDocument> pipeline) {
    	if (StringUtils.isBlank(collectionName) || pipeline == null || pipeline.size() == 0) {
    		return null;
    	}

    	MongoCursor cursor = getCollection(collectionName).aggregate(pipeline).iterator();
        List<Object> list = new ArrayList<Object>();
        while (cursor.hasNext()) {
//            list.add(cursor.next());
        }
        
        return list;
    }
	

}
