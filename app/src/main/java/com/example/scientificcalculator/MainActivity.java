package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv, solutionTV;
    MaterialButton C, openBracket, closeBracket, divide, multiply, add, subtract, equals, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ac, dot;
    Button sin, cos, tan, log, ln, sqrt, pow, modulus;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        b0 = findViewById(R.id.button_0);
        b1 = findViewById(R.id.button_1);
        b2 = findViewById(R.id.button_2);
        b3 = findViewById(R.id.button_3);
        b4 = findViewById(R.id.button_4);
        b5 = findViewById(R.id.button_5);
        b6 = findViewById(R.id.button_6);
        b7 = findViewById(R.id.button_7);
        b8 = findViewById(R.id.button_8);
        b9 = findViewById(R.id.button_9);
        C = findViewById(R.id.button_c);
        openBracket = findViewById(R.id.button_open_bracket);
        closeBracket = findViewById(R.id.button_close_bracket);
        divide = findViewById(R.id.button_divide);
        multiply = findViewById(R.id.button_multiply);
        subtract = findViewById(R.id.button_subtract);
        dot = findViewById(R.id.button_dot);
        add = findViewById(R.id.button_add);
        ac = findViewById(R.id.button_ac);
        equals = findViewById(R.id.button_equals);

        sin = findViewById(R.id.button_sin);
        cos = findViewById(R.id.button_cos);
        tan = findViewById(R.id.button_tan);
        log = findViewById(R.id.button_log);
        ln = findViewById(R.id.button_ln);
        sqrt = findViewById(R.id.button_sqrt);
        pow = findViewById(R.id.button_pow);
        modulus = findViewById(R.id.button_modulus);

        setClickListeners();
    }

    private void setClickListeners() {
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        C.setOnClickListener(this);
        openBracket.setOnClickListener(this);
        closeBracket.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        subtract.setOnClickListener(this);
        dot.setOnClickListener(this);
        add.setOnClickListener(this);
        ac.setOnClickListener(this);
        equals.setOnClickListener(this);

        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        log.setOnClickListener(this);
        ln.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        pow.setOnClickListener(this);
        modulus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCal = solutionTV.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTv.setText("0");
        } else if (buttonText.equals("=")) {
            solutionTV.setText(resultTv.getText());
        } else if (buttonText.equals("C")) {
            dataCal = dataCal.substring(0, dataCal.length() - 1);
        } else {
            dataCal = dataCal + buttonText;
        }

        solutionTV.setText(dataCal);
        String finalResult = getResult(dataCal);
        if (finalResult.equals("ERROR")) {
            resultTv.setText(finalResult);
        } else {
            resultTv.setText(finalResult);
        }
    }

    private String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "ERROR";
        }
    }
}
