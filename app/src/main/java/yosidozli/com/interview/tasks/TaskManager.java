package yosidozli.com.interview.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TaskManager {



    private int _completedTasks;
    private List<Task> _tasks;

    private TaskManager(){
        _tasks = new ArrayList<>();
    }

    public static TaskManager createManager() {
        return new TaskManager();
    }


    public int totalTasks() {
        return _tasks.size();
    }

    public int completedTasks() {
        return _completedTasks;
    }

    public void addTask(Task task) {
         task.setTaskManager(this);
        if (_tasks.contains(task))
            return;
        else
            {
            _tasks.add(task);
            if(task.isDone())
                _completedTasks+=1;
        }


    }



    public List<Task> getTasks() {
        return new ArrayList<>(_tasks);

    }

     void updateCompleted(Task task){
        //update only already in manager
        if(_tasks.contains(task)) {
            if (task.isDone())
                _completedTasks += 1;
            else
                _completedTasks -= 1;
        }

     }
}
