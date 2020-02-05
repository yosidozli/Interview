package yosidozli.com.interview;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import yosidozli.com.interview.tasks.Task;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.TasksViewHolder> {

    private final MainActivity.Listener _listener;
    private List<Task> _tasks;



        public RvAdapter(List<Task> tasks, MainActivity.Listener listener) {
            _tasks = tasks;
            _listener = listener;
        }

        public void update(List<Task>tasks ){
            _tasks  = tasks;
        }

        @Override
        public RvAdapter.TasksViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

            View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_view, parent, false);

            TasksViewHolder vh = new TasksViewHolder(v,_listener);
            return vh;
        }

        @Override
        public void onBindViewHolder(final TasksViewHolder holder, int position) {
            holder._task = _tasks.get(position);
            holder._textView.setText(String.format("\u2022 \t %s", holder._task.getContent()));
            if(holder._task.isDone())
                holder._textView.setPaintFlags(holder._textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            else
                holder._textView.setPaintFlags(holder._textView.getPaintFlags() &  ~Paint.STRIKE_THRU_TEXT_FLAG);


            holder._textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { {
                        if(holder._task != null)
                            if(holder._task.isDone())
                                holder._task.unDone();
                            else
                                holder._task.done();
                        notifyDataSetChanged();
                        holder._listener.itemPressed();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return _tasks.size();
        }
        public static class TasksViewHolder extends RecyclerView.ViewHolder  {
            private Task _task;
            private TextView _textView;
            private MainActivity.Listener _listener;
            TasksViewHolder(View v,MainActivity.Listener listener) {
                super(v);
                _textView = v.findViewById(R.id.text_view);
                _listener = listener;
            }


        }

}
