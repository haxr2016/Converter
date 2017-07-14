package com.example.hemanth.converter;

import android.os.Bundle;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.hemanth.converter.MainActivity.LOGS;

public class LogsActivity extends AppCompatActivity {

    private TextView logTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        logTv = (TextView) findViewById(R.id.log_tv);
        Bundle bundle = getIntent().getExtras().getBundle(LOGS);
        ArrayList<String> logs = bundle.getStringArrayList(LOGS);
        showLogs(logs);
    }

    private void showLogs(ArrayList<String> logs) {
        for (String val:logs){
            logTv.setText(logTv.getText().toString()+"\n"+val);
        }

    }

}
