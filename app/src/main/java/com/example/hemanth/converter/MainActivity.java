package com.example.hemanth.converter;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private EditText editText1;
    private RadioGroup button_group;
    private EditText editText;
    int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_layout);

        editText = (EditText) findViewById(R.id.editText2);
        editText1 = (EditText) findViewById(R.id.editText);
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
                break;


            case R.id.radioButton3:
                String message = editText.getText().toString();
                double n = Double.parseDouble(message);
                n = c2f(n);
                message = n+"";
                editText1.setText(message);
                break;
        }

    }

    private double c2f(double n) {
        return (n * (9.0 / 5.0) + 32);
    }

    private double f2c(double n) {
        return (n - 32) * 5.0 / 9.0;
    }
}
