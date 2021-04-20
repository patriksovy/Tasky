package sk.itsovy.kincel.tasky;

import sk.itsovy.kincel.tasky.collection.Task;
import sk.itsovy.kincel.tasky.mongoDb.MongoImpl;

import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        testAddNewTask();
        printAllTasks();
    }

    private static void printAllTasks(){
        MongoImpl mongo = new MongoImpl();
        List<Task> list = mongo.getAllTasks();
        for (Task t: list){
            System.out.println(t.toString());
        }
    }

    public static void testAddNewTask(){
        Task task = new Task("kupiť chleba",6,false, new Date(),2);
        MongoImpl mongo = new MongoImpl();
        mongo.insertTask(task);
    }
}