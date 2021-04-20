package sk.itsovy.kincel.tasky.mongoDb;

import com.mongodb.client.FindIterable;
import org.bson.types.ObjectId;
import sk.itsovy.kincel.tasky.collection.Task;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.util.List;

public class MongoImpl implements MongoDb {

    //dbname: TaskDB
    //collection: Tasks
    private static final MongoClient mongoClient = new MongoClient();
    private static MongoDatabase database;
    private static Document docs;
    private static MongoCollection<Document> collection;
    private static Date date = new Date();

    @Override
    public void insertTask(Task task) {
        if(task==null){
            return;
        }
        Document newTask = new Document("name",task.getName()).
                append("priority",task.getPriority()).
                append("done",task.isDone()).
                append("date",task.getDate());
        if (task.getPrice()>=0){
            newTask.append("price",task.getPrice());
        }
        if (task.getId()!=null){
            newTask.append("_id",task.getId());
        }
        try{
            MongoCollection<Document> collection = getDocumentMongoCollection();
            collection.insertOne(newTask);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private MongoCollection<Document> getDocumentMongoCollection() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("TaskDB");
        MongoCollection<Document> collection = db.getCollection("Tasks");
        return collection;
    }

    @Override
    public void makeTaskDone( int id) {

    }

    @Override
    public List<Task> getAllTasks() {
        try {
            MongoCollection<Document> collection = getDocumentMongoCollection();
            FindIterable<Document> cursor = collection.find();
            List<Task> list = new ArrayList<>();
            for (Document document : cursor){
                String name = document.getString("name");
                int priority = document.getInteger("priority");
                boolean done = document.getBoolean("done");
                Date date = document.getDate("date");
                Object id = document.getObjectId("_id");
                Task task;
                if (document.containsKey("price")){
                    double price = document.getDouble("price");
                    task = new Task(name,priority,done,date,price);
                }
                else {
                    task = new Task(name,priority,done,date);
                }
                task.setId((ObjectId) id);
                list.add(task);
            }
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> getAllTasks(boolean done) {

        return null;
    }

    @Override
    public List<Task> getTasksByPriority(int priority) {
        return null;
    }

    @Override
    public List<Task> getTasksByName(String name) {
        return null;
    }

    @Override
    public void DeleteFinishedTasks() {

    }
}
