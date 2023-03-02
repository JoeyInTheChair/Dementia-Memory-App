package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class questionTwo extends AppCompatActivity {

    TextView [] listOfWords = new TextView[5];
    private List<String> list = new ArrayList<>();
    private List<String> randomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two);
        getListOfWords();
        setRandomWordsOnScreen();
    }

    private void setRandomWordsOnScreen() {
        listOfWords[0] = (TextView) findViewById(R.id.guessWordOne);
        listOfWords[1] = (TextView) findViewById(R.id.guessWordTwo);
        listOfWords[2] = (TextView) findViewById(R.id.guessWordThree);
        listOfWords[3] = (TextView) findViewById(R.id.guessWordFour);
        listOfWords[4] = (TextView) findViewById(R.id.guessWordFive);

        listOfWords[0].setText(randomList.get(0));
        listOfWords[1].setText(randomList.get(1));
        listOfWords[2].setText(randomList.get(2));
        listOfWords[3].setText(randomList.get(3));
        listOfWords[4].setText(randomList.get(4));
    }

    public void getListOfWords() {
        String [] array = {"spicy", "fable", "quilt", "lunar", "wreck", "brisk", "fudge", "frown", "trump", "globe", "tulip", "flair", "slump", "whirl", "swoop", "humid", "creed", "stoop", "gloom", "hinge", "crisp", "stomp", "clasp", "shack", "cabin", "plaid", "coast", "braid", "hatch", "stark", "fable", "quilt", "shale", "crumb", "swarm", "flask", "grasp", "snore", "prick", "bison", "dwarf", "snarl", "mirth", "spout", "smirk", "sable", "gully", "sneak", "crank", "drift", "flame", "gummy", "daisy", "glaze", "jolly", "proud", "silly", "salsa", "swoop", "globe", "crown", "fable", "couch", "lemon", "hazel", "nudge", "prize", "quilt", "sable", "sneak", "snore", "spice", "sting", "swirl", "thick", "trace", "trail", "trunk", "vegan", "wedge", "yacht", "blaze", "brisk", "chime", "clerk", "craft", "dwarf", "flair", "frost", "grind", "hatch", "heist", "hurry", "joker", "jumpy", "laser", "lodge", "lunar", "mimic", "mucus"};
        list = Arrays.asList(array);
        shuffleList(list);
        createWordsList();
    }
    private void shuffleList(List<String> list) {
        Collections.shuffle(list);
        Collections.shuffle(list);
        Collections.shuffle(list);
    }

    private void createWordsList() {
        int[] randomNumbers = new int[5];
        shuffleList(this.list);
        int listSize = this.list.size();
        for (int i = 1; i <= listSize; i++) {
            randomNumbers[0] = (int) (Math.random() * listSize) + 1;

            while (randomNumbers[1] == randomNumbers[0]) {
                randomNumbers[1] = (int) (Math.random() * listSize) + 1;
            }
            while ((randomNumbers[2] == randomNumbers[1]) || (randomNumbers[2] == randomNumbers[0])) {
                randomNumbers[2] = (int) (Math.random() * listSize) + 1;
            }
            while ((randomNumbers[3] == randomNumbers[0]) || (randomNumbers[3] == randomNumbers[1]) || (randomNumbers[3] == randomNumbers[2])) {
                randomNumbers[3] = (int) (Math.random() * listSize) + 1;
            }
            while ((randomNumbers[4] == randomNumbers[0]) ||
                    (randomNumbers[4] == randomNumbers[1]) ||
                    (randomNumbers[4] == randomNumbers[2]) ||
                    (randomNumbers[4] == randomNumbers[3])) {
                randomNumbers[4] = (int) (Math.random() * listSize) + 1;
            }

        }
        this.randomList.add(this.list.get(randomNumbers[0]));
        this.randomList.add(this.list.get(randomNumbers[1]));
        this.randomList.add(this.list.get(randomNumbers[2]));
        this.randomList.add(this.list.get(randomNumbers[3]));
        this.randomList.add(this.list.get(randomNumbers[4]));
    }
}