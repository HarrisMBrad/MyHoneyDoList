package com.myhoneydolist.bmichaelh13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText enterMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveButton = findViewById(R.id.saveBtnId);
        enterMessage = findViewById(R.id.todoListId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic goes here
                if (!enterMessage.getText().toString().equals("")) {

                    String message = enterMessage.getText().toString();
                    writeToFile(message);
                }else {
                    //do nothing for now
                }


            }
        });

        try {
            if (readFromFile() != null) {

            enterMessage.setText(readFromFile());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeToFile(String message) {
        try {
            OutputStreamWriter outsideStreamWriter = new OutputStreamWriter(openFileOutput("todolist.txt", Context.MODE_PRIVATE));
            outsideStreamWriter.write(message);
            outsideStreamWriter.close(); //always close your streams !!

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readFromFile() throws IOException {
        String result = "";
        InputStream inputStream = openFileInput("todolist.txt");

    if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String tempString = "";
        StringBuilder stringBuilder = new StringBuilder();

        while ((tempString = bufferedReader.readLine()) != null) {

            stringBuilder.append(tempString);

        }
        inputStream.close();
        result = stringBuilder.toString();
    }

        return  result;

    }

    private class inputStreamReader {
    }
}

