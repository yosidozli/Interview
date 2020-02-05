package yosidozli.com.interview.tasks;

public class Task {

    private final String _content;
    private boolean _done;
    private TaskManager _taskManager;

    private Task(String content, boolean isDone){
        _content = content;
        _done = isDone;
    }

    public static Task createTask(String content, boolean isDone) {
       return new Task(content,isDone);
    }

    public String getContent() {
        return _content;
    }

    public boolean isDone(){
        return _done;
    }

    private void setDone(boolean done){
        _done = done;
    }

    public void done() {
        boolean oldStatus = isDone();
        setDone(true);
        if(_taskManager != null && !oldStatus)
            _taskManager.updateCompleted(this);
    }

    public void unDone() {
        boolean oldStatus = isDone();
        setDone(false);
       if(_taskManager != null && oldStatus)
            _taskManager.updateCompleted(this);

    }

    void setTaskManager(TaskManager taskManager){
        _taskManager = taskManager;
    }

}
