package com.l2l.androided.mh122354.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //Currency & percent formatter objects
    private static final NumberFormat currencyFormat=
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat=
            NumberFormat.getPercentInstance();

    private double billAmount=0.0;
    private double percent =0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get Reference to Views in GUI
        amountTextView=(TextView)findViewById(R.id.amountTextView);
        percentTextView=(TextView)findViewById(R.id.percentTextView);
        tipTextView=(TextView)findViewById(R.id.tipTextView);
        totalTextView=(TextView)findViewById(R.id.totalTextView);

        //Set tip  & total text views
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        //Set TextWatcher
        EditText amountEditText= (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        //Set Seekbar listener
        SeekBar percentSeekBar=(SeekBar)findViewById(R.id.tipSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    //Calculate tip & total amounts, set vies to these amounts
    private void calculate(){

        percentTextView.setText(percentFormat.format(percent));


        double tip = billAmount*percent;
        double total=billAmount+tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));


    }

    //update seekbar to appropriate percentage
    private final SeekBar.OnSeekBarChangeListener seekBarListener=
            new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                    percent=i/100;
                    calculate();


                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    //calculate & change textview according to user input
    private final TextWatcher amountEditTextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s , int start, int before , int count){
            try{
                billAmount=Double.parseDouble(s.toString());
                amountTextView.setText(currencyFormat.format(billAmount));
            }catch(NumberFormatException e){
                amountTextView.setText("");
                billAmount=0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
