package yosidozli.com.interview;


import android.text.method.MultiTapKeyListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import yosidozli.com.interview.tasks.Task;
import yosidozli.com.interview.tasks.TaskManager;

import static org.junit.Assert.*;

public class TaskManagerTest {

    private Task _firstTask ,_secondTask , thirdTask;
    private TaskManager _manager;
    @Before
    public void setUp(){
        String content = "First Item";
        _secondTask = Task.createTask( "Second item",true);
        _secondTask = Task.createTask( "Third item",true);
        boolean isDone = false;
        _firstTask = Task.createTask(content, isDone);
        _manager = TaskManager.createManager();
    }

    @Test
    public void createTask(){
        String content = "First Item";
        boolean isDone = false;
        Task task = Task.createTask(content, isDone);
        assertEquals(content,task.getContent());
        assertEquals(isDone,task.isDone());
    }

    @Test
    public void setDoneTest(){
        //precondition
        assertFalse(_firstTask.isDone());
        _firstTask.done();
        assertTrue(_firstTask.isDone());

    }

    @Test
    public void unDoneTest(){
        _firstTask.done();
        //precondition
        assertTrue(_firstTask.isDone());
        _firstTask.unDone();
        assertFalse(_firstTask.isDone());

    }



    @Test
    public void createTaskManagerTest(){
        TaskManager manager = TaskManager.createManager();
        assertNotNull(manager);
        assertEquals(0,manager.totalTasks());
        assertEquals(0,manager.completedTasks());

    }

    @Test
    public void addTaskTest(){
        int totalTasks = _manager.totalTasks();
        int completedTasks = _manager.completedTasks();
        _manager.addTask(_firstTask);

        assertEquals(totalTasks+1,_manager.totalTasks());
        assertEquals(completedTasks,_manager.completedTasks());

        List<Task> tasks = _manager.getTasks();
        assertTrue(tasks.contains(_firstTask));

    }

    @Test
    public void TaskMangerCompletedTaskCountTeset(){
        //precondition
        _firstTask.unDone();
        assertFalse(_firstTask.isDone());
        assertEquals(0,_manager.totalTasks());
        assertEquals(0,_manager.completedTasks());
        _manager.addTask(_firstTask);
        assertEquals(1,_manager.totalTasks());
        assertEquals(0,_manager.completedTasks());

        _firstTask.done();

        //post condition
        assertTrue(_firstTask.isDone());
        assertEquals(1,_manager.totalTasks());
        assertEquals(1,_manager.completedTasks());

        _firstTask.unDone();
        assertFalse(_firstTask.isDone());
        assertEquals(1,_manager.totalTasks());
        assertEquals(0,_manager.completedTasks());

        //check no task updated twice
        _firstTask.unDone();
        _firstTask.unDone();
        assertFalse(_firstTask.isDone());
        assertEquals(1,_manager.totalTasks());
        assertEquals(0,_manager.completedTasks());
        _firstTask.done();
        _firstTask.done();
        assertTrue(_firstTask.isDone());
        assertEquals(1,_manager.totalTasks());
        assertEquals(1,_manager.completedTasks());

    }








}
