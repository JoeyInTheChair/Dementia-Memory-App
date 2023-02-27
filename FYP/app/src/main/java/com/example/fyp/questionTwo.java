package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class questionTwo extends AppCompatActivity {

    TextView [] listOfWords = new TextView[5];
    private List<String> list = new ArrayList<>();
    public String path = Environment.getExternalStorageState().

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two);
        getListOfWords();
        System.out.println(list);
    }
    public static List<String> getListOfWords() {
        List<String> list = new ArrayList<>();
        File inputFile = new File("./src/listOfWords.txt");
        try {
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                list.add(data);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("scanner error");
            e.printStackTrace();
        }
        return list;
    }
}