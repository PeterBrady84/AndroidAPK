package com.peter.androidapk;

/*
 * Created by Peter Brady on 06/04/2017.
 * X00115070
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<AndroidAPI> androidList;
    private EditText apiLevelInput;
    private TextView apiInfoDisplay, apiValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise data - to do: read from web service
        androidList = new ArrayList<AndroidAPI>() {
            {
                add(new AndroidAPI("Nougat", "7.1", 25, "2016"));
                add(new AndroidAPI("Nougat", "7.0", 24, "2016"));
                add(new AndroidAPI("Marshmallow", "6.0", 23, "2015"));
                add(new AndroidAPI("Lollipop", "5.1", 22, "2014"));
                add(new AndroidAPI("Lollipop", "5.0", 21, "2014"));
                add(new AndroidAPI("KitKat", "4.4 - 4.4.4", 19, "2013"));
                add(new AndroidAPI("Jelly Bean", "4.3.x", 18, "2012"));
                add(new AndroidAPI("Jelly Bean", "4.2.x", 17, "2012"));
                add(new AndroidAPI("Jelly Bean", "4.1.x", 16, "2012"));
            }
        };

        // initialise edit text
        apiLevelInput = (EditText) findViewById(R.id.apiLevelEditText);
        // create and initialise display button
        Button displayBtn = (Button) findViewById(R.id.displayButton);
        // initialise information text view
        apiInfoDisplay = (TextView) findViewById(R.id.apiInformationTextView);

        // validation for api value input
        apiValidation = (TextView) findViewById(R.id.input_api_validation);
        apiValidation.setVisibility(View.GONE);

        // on click listener for display button
        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if input is empty - error
                if (apiLevelInput.getText().toString().equals("")) {
                    apiValidation.setVisibility(View.VISIBLE);
                    apiValidation.setText(R.string.empty_value);
                    apiInfoDisplay.setText("");
                }
                // if input is not numeric - error
                else if (!isNumeric(apiLevelInput.getText().toString())) {
                    apiValidation.setVisibility(View.VISIBLE);
                    apiValidation.setText(R.string.non_numeric_value);
                    apiInfoDisplay.setText("");
                } else {
                    apiValidation.setVisibility(View.GONE);
                    AndroidAPI apiInfo = findByAPI(Integer.parseInt(apiLevelInput.getText()
                            .toString()));
                    if (apiInfo != null)
                        apiInfoDisplay.setText(getString(R.string.api_output, apiInfo.getName(),
                                apiInfo.getPlatformVersion(), Integer.toString(apiInfo
                                        .getApiLevel()), apiInfo.getYear()));
                    else
                        apiInfoDisplay.setText(R.string.api_not_found);
                }
            }
        });
    }

    // find by api level and and return the object for that api if exists
    public static AndroidAPI findByAPI(int apiLevel) {
        AndroidAPI api = null;
        for (AndroidAPI a : androidList) {
            if (apiLevel == a.getApiLevel()) {
                api = a;
            }
        }
        return api;
    }

    // is numeric method - returns false if exception thrown in parsing
    public static boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
