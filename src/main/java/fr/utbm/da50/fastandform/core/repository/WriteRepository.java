
package fr.utbm.da50.fastandform.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WriteRepository{
    
  @Autowired
  private MongoClient client;

  public List<String> findAllDocuments(String DatabaseName, String CollectionName) {
    final List<String> list = new ArrayList<>();
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
    data.find().map(Document::toJson).forEach(list::add);
    return list;
  }
  
  public void SaveOneDocument(String DateBaseName, String CollectionName, HashMap<String, Object> map){
    final MongoCollection<Document> data = client.getDatabase(DateBaseName).getCollection(CollectionName);
    data.insertOne((new Document(map)));
  }

  public void UpdateOneDocument(String DateBaseName, String CollectionName, HashMap<String, Object> map,Integer id){
    //
  }

}