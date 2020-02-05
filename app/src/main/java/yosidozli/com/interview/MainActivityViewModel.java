package yosidozli.com.interview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import yosidozli.com.interview.tasks.Task;
import yosidozli.com.interview.tasks.TaskManager;

public class MainActivityViewModel extends ViewModel {
    private TaskManager _tm;
    private MutableLiveData<List<Task>> _tasks;


    public LiveData<List<Task>> getTasks(){
        if(_tasks == null) {
            _tasks = new MutableLiveData<>();
            loadTasks();
        }
    return _tasks;
    }




    private void loadTasks(){
           if(_tm == null)
               initializeTaskManager();
           _tasks.postValue(_tm.getTasks());
    }


    private void initializeTaskManager() {
        _tm = TaskManager.createManager();
        Task firstTask = Task.createTask( "First item",true);
        Task secondTask = Task.createTask( "Second item",true);
        Task thirdTask = Task.createTask( "Third item",false);
        _tm.addTask(firstTask);
        _tm.addTask(secondTask);
        _tm.addTask(thirdTask);

    }


    public void addTask(String str) {
        _tm.addTask( Task.createTask(str,false));
        _tasks.postValue(_tm.getTasks());
    }

    public int completed() {
      return _tm.completedTasks();
    }

    public int total(){
        return _tm.totalTasks();
    }


    public TaskManager getTaskManger() {
        return _tm;
    }
}
