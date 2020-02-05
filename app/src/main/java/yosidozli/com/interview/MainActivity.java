package yosidozli.com.interview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import yosidozli.com.interview.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private RvAdapter _adapter;
    private List<Task> _tasks = new ArrayList<>();
    private EditText _edit;
    private MainActivityViewModel _viewModel;
    private TextView _textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewModel();
        initializeRecyclerView();
        initializeButton();
        _textView = findViewById(R.id.text_view);

    }

    private void initializeViewModel(){
        _viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        _viewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                _tasks =tasks;
                _adapter.update(_tasks);
                _adapter.notifyDataSetChanged();
                _textView.setText(getString(R.string.message,_viewModel.completed(),_viewModel.total()));
            }
        });
    }

    private void initializeRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        _adapter = new RvAdapter(_tasks, new Listener() {
            @Override
            public void itemPressed() {
                _textView.setText(getString(R.string.message,_viewModel.completed(),_viewModel.total()));
            }
        });
        recyclerView.setAdapter(_adapter);
    }

    private void  initializeButton(){
        Button button = findViewById(R.id.button);
        _edit = findViewById(R.id.edit_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = _edit.getText().toString();
                if(str.isEmpty())
                    Toast.makeText(MainActivity.this,"no task to add",Toast.LENGTH_LONG).show();
                else
                    _viewModel.addTask(str);
            }
        });
    }


    interface Listener{
        public void itemPressed();
    }
}
