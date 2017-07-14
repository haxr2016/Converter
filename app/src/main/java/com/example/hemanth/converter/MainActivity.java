package com.example.hemanth.converter;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static android.R.attr.value;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String LOGS = "logs";

    private Switch convertSwitch;
    private TextView convertSwitchTv;
    private EditText inputEt;
    private TextView resultTv;
    private TextView logTv;

    // false for Farenheit to Celsius
    //true for Celsius to Farenheit
    private Button convertBtn;
    private boolean convertState;
    ArrayList<String> logs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        convertSwitch = (Switch) findViewById(R.id.convert_switch);
        convertSwitchTv = (TextView) findViewById(R.id.converter_info);
        inputEt = (EditText) findViewById(R.id.et_input);
        resultTv = (TextView) findViewById(R.id.result_tv);
        convertBtn = (Button) findViewById(R.id.convert_submit);
        logTv = (TextView) findViewById(R.id.log);
        convertState = false;
        interchangeText();
        updateConvertInfo();

        convertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                convertState = b;
                clearText();
                interchangeText();
                updateConvertInfo();
            }
        });
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Convert();
            }
        });

        logTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogActivity();
            }
        });

    }

    private void updateConvertInfo() {
        if (convertState) {
            convertSwitchTv.setText("Farenheit to Celsius");
        } else {
            convertSwitchTv.setText("Celsius to Farenheit");
        }
    }

    private void openLogActivity() {

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(LOGS, logs);
        Intent intent = new Intent(MainActivity.this, LogsActivity.class);
        intent.putExtra(LOGS, bundle);
        startActivity(intent);

    }

    private void interchangeText() {

        if (convertState) {
            changeText("FarenHeit", "Celsius");
        } else {
            changeText("Celsius", "FarenHeit");
        }

    }

    private void clearText() {
        inputEt.setText("");
        resultTv.setText("");
    }

    private void changeText(String value, String value1) {
        inputEt.setHint(value);
        resultTv.setHint(value1);
    }

    public void Convert() {

        if (convertState) {
            String message1 = inputEt.getText().toString();
            double n1 = Double.parseDouble(message1);
            n1 = f2c(n1);
            message1 = n1 + "";
            resultTv.setText(message1);
            String str = "C to F: ";
            createHistory(str);
        } else {
            String message = inputEt.getText().toString();
            double n = Double.parseDouble(message);
            n = c2f(n);
            message = n + "";
            resultTv.setText(message);
            String str1 = "F to C: ";
            createHistory(str1);

        }


    }

    private void createHistory(final String str) {

        String inputValue = inputEt.getText().toString();
        String resultValue = resultTv.getText().toString();
        String log = str + inputValue + " -> " + resultValue;
        logs.add(log);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("resultTv", resultTv.getText().toString());
        outState.putStringArrayList(LOGS, logs);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logs = savedInstanceState.getStringArrayList(LOGS);
        resultTv.setText(savedInstanceState.getString("resultTv"));

    }

    private double c2f(double n) {
        return roundTwoDecimals(n * (9.0 / 5.0) + 32);
    }

    private double f2c(double n) {
        return roundTwoDecimals(n - 32) * 5.0 / 9.0;
    }

    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
    }
}
