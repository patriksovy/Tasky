package sk.itsovy.kincel.tasky.mongoDb;

import sk.itsovy.kincel.tasky.collection.Task;

import java.util.List;

public interface MongoDb {

    public void insertTask(Task task);

    public void makeTaskDone(int id);

    public List<Task> getAllTasks();

    public List<Task> getAllTasks(boolean done);

    public List<Task> getTasksByPriority(int priority);

    public List<Task> getTasksByName(String name);

    public void DeleteFinishedTasks();

}
