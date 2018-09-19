package com.example.tjgaming.gpa_spartj3_calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText mGradeOne, mGradeTwo, mGradeThree, mGradeFour, mGradeFive, mGpa;
    public ConstraintLayout mCalcLayout;
    public Button mCalcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateViews();
        setCalcButtonClickListener();
    }

    public void instantiateViews() {
        mGradeOne = findViewById(R.id.grade_one);
        mGradeTwo = findViewById(R.id.grade_two);
        mGradeThree = findViewById(R.id.grade_three);
        mGradeFour = findViewById(R.id.grade_four);
        mGradeFive = findViewById(R.id.grade_five);
        //Field to display gpa
        mGpa = findViewById(R.id.gpa_field);
        //Layout to change background based on gpa
        mCalcLayout = findViewById(R.id.calc_constraint_layout);
        //Calculate gpa
        mCalcButton = findViewById(R.id.calc_button);
    }

    //Click listener to calculate gpa.
    public void setCalcButtonClickListener() {
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCalcButton.getText().equals(getString(R.string.reset_fields_button))){
                    resetFields();
                    changeButtonText();
                }
                else {
                    if (emptyViews()) {
                        Toast.makeText(getApplicationContext(), "Cannot have empty fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        changeButtonText();
                        mGpa.setText(String.valueOf(calculateGPA()));
                        mCalcLayout.setBackgroundColor(gpaColor());

                    }
                }
            }
        });
    }

    private void resetFields() {
        mGradeOne.setText("");
        mGradeTwo.setText("");
        mGradeThree.setText("");
        mGradeFour.setText("");
        mGradeFive.setText("");
        mGpa.setText("");
        mCalcLayout.setBackgroundColor(Color.WHITE);

    }

    private void changeButtonText() {
        if (mCalcButton.getText().equals(getString(R.string.calc_button)))
            mCalcButton.setText(getString(R.string.reset_fields_button));
         else
            mCalcButton.setText(getString(R.string.calc_button));
    }

    private int gpaColor() {
        double gpa = Double.parseDouble(mGpa.getText().toString());
        if (gpa < 60)
            return Color.RED;
         else if (gpa > 60 && gpa < 80)
            return Color.YELLOW;
         else
            return Color.GREEN;
    }

    private double calculateGPA() {
        double tot =
                Double.parseDouble(mGradeOne.getText().toString()) +
                Double.parseDouble(mGradeTwo.getText().toString()) +
                Double.parseDouble(mGradeThree.getText().toString()) +
                Double.parseDouble(mGradeFour.getText().toString()) +
                Double.parseDouble(mGradeFive.getText().toString());

        return tot / 5;
    }

    public boolean emptyViews() {
        return (mGradeOne.getText().toString().equals("") ||
                mGradeTwo.getText().toString().equals("") ||
                mGradeThree.getText().toString().equals("") ||
                mGradeFour.getText().toString().equals("") ||
                mGradeFive.getText().toString().equals(""));
    }
}
