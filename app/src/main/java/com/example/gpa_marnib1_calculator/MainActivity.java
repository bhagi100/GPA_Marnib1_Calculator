package com.example.gpa_marnib1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.text.TextUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private EditText sub1;
    private EditText sub2;
    private EditText sub3;
    private EditText sub4;
    private EditText sub5;
    private Button button;
    private TextView finalResult;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sub1 = findViewById(R.id.editText1);
         sub2 = findViewById(R.id.editText2);
         sub3 = findViewById(R.id.editText3);
         sub4 = findViewById(R.id.editText4);
         sub5 = findViewById(R.id.editText5);

        TextView errorMessage1 = findViewById(R.id.error1);
        TextView errorMessage2 = findViewById(R.id.error2);
        TextView errorMessage3 = findViewById(R.id.error3);
        TextView errorMessage4 = findViewById(R.id.error4);
        TextView errorMessage5 = findViewById(R.id.error5);

        mainLayout = findViewById(R.id.constraintLayout);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (button.getText().toString().equals("Clear Form")) {
                    clearForm();
                } else
                {
                    String inputText1 = sub1.getText().toString().trim();
                validateEditText(inputText1, errorMessage1);
                String inputText2 = sub2.getText().toString().trim();
                validateEditText(inputText2, errorMessage2);
                String inputText3 = sub3.getText().toString().trim();
                validateEditText(inputText3, errorMessage3);
                String inputText4 = sub4.getText().toString().trim();
                validateEditText(inputText4, errorMessage4);
                String inputText5 = sub5.getText().toString().trim();
                validateEditText(inputText5, errorMessage5);

                if (errorMessage1.getVisibility() == View.GONE && errorMessage2.getVisibility() == View.GONE
                        && errorMessage3.getVisibility() == View.GONE && errorMessage4.getVisibility() == View.GONE
                        && errorMessage5.getVisibility() == View.GONE) {
                    calculateAndShowGrade();

                    button.setText("Clear Form");
                }
            }
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void clearForm() {
        sub1.setText("");
        sub2.setText("");
        sub3.setText("");
        sub4.setText("");
        sub5.setText("");
        button.setText("Compute GPA");
        finalResult.setText("");
        mainLayout.setBackgroundColor(Color.WHITE);
    }

    @SuppressLint("SetTextI18n")
    private void calculateAndShowGrade() {

        double value1 = parseDouble(sub1.getText().toString());
        double value2 = parseDouble(sub2.getText().toString());
        double value3 = parseDouble(sub3.getText().toString());
        double value4 = parseDouble(sub4.getText().toString());
        double value5 = parseDouble(sub5.getText().toString());

        double sum = (value1 + value2 + value3 + value4 + value5)/5;

        finalResult = findViewById(R.id.finalresult);
        finalResult.setText(sum+"%");
        if(sum<60){
            mainLayout.setBackgroundColor(Color.rgb(255, 102, 102));
        }
        else if(sum>60 && sum<80){
            mainLayout.setBackgroundColor(Color.YELLOW);
        }
        else {
            mainLayout.setBackgroundColor(Color.GREEN);
        }

    }
    private double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid double
            return 0.0;
        }
    }

    @SuppressLint("SetTextI18n")
    private void validateEditText(String inputText, TextView errorTextView) {

        if (TextUtils.isEmpty(inputText)) {
            // EditText is empty, show an error message
            showErrorMessage("Please enter a grade",errorTextView);
        } else {
            // EditText is not empty, try to parse as double
            try {
                double number = Double.parseDouble(inputText);
                if(number>100){
                    showErrorMessage("Grade should be valid",errorTextView);
                }
                else {
                    // Value is valid, you can perform additional actions here
                    hideErrorMessage(errorTextView);
                }

            } catch (NumberFormatException e) {
                // Invalid double input, show an error message
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText("Invalid number format");
            }
        }
    }

    private void showErrorMessage(String message, TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(message);
    }

    private void hideErrorMessage(TextView errorTextView) {
        errorTextView.setVisibility(View.GONE);
    }
}