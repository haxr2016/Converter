package com.example.hemanth.converter;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.value;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private EditText editText1;
    private RadioGroup button_group;
    private EditText editText;
    private TextView history;
    private Button button;
    private int value;
    String[] ListElements = new String[]{"a", "b"};
    int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText2);
        editText1 = (EditText) findViewById(R.id.editText);
        history = (TextView) findViewById(R.id.history);
        history.setText("");
        history.setMovementMethod(new ScrollingMovementMethod());
        button = (Button) findViewById(R.id.button);

        editText1.setKeyListener(null);
        button_group = (RadioGroup) findViewById(R.id.RG);

        button_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectedId = i;
                interchangeText(i);
            }
        });

    }

    private void interchangeText(int i) {
        switch (i) {
            case R.id.radioButton2:
                changeText("FarenHeit", "Celsius");
                clearText();

                break;
            case R.id.radioButton3:
                changeText("Celsius", "FarenHeit");
                clearText();
                break;
        }
    }

    private void clearText() {
        editText.setText("");
        editText1.setText("");
    }

    private void changeText(String farenHeit, String celsius) {
        editText.setHint(farenHeit);
        editText1.setHint(celsius);
    }

    public void Convert(View view) {

        switch (selectedId) {
            case R.id.radioButton2:

                String message1 = editText.getText().toString();
                double n1 = Double.parseDouble(message1);
                n1 = f2c(n1);
                message1 = n1+"";
                editText1.setText(message1);
                String str = "C to F: ";
                createHistory(str);

                break;


            case R.id.radioButton3:
                String message = editText.getText().toString();
                double n = Double.parseDouble(message);
                n = c2f(n);
                message = n+"";
                editText1.setText(message);
                String str1 = "F to C: ";
                createHistory(str1);


                break;
        }

    }

    private void createHistory(final String str1) {
        button = (Button) findViewById(R.id.button);


        String newText =  editText.getText().toString();
        String newText2 = editText1.getText().toString();
        String message = str1 + newText +" -> "+ newText2;
        String historyText = history.getText().toString();
        history.setText(message + "\n" + historyText);
//        value = Integer.parseInt(newText);
//        numText.setText("");

//        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
//
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (MainActivity.this, R.layout.activity_main, ListElementsArrayList);
//
//        listView.setAdapter(adapter);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                ListElementsArrayList.add(str1 + string1.getText().toString() + string2.getText().toString());
//
//                adapter.notifyDataSetChanged();
//
//    }
//
//    });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String hText = history.getText().toString();
        outState.putString("History", hText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        history.setText(savedInstanceState.getString("History"));
    }

    private double c2f(double n) {
        return (n * (9.0 / 5.0) + 32);
    }

    private double f2c(double n) {
        return (n - 32) * 5.0 / 9.0;
    }
}
