package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionTwo extends AppCompatActivity {
    private final TextView [] wordsUsed = new TextView[5];
    private List<String> list = new ArrayList<>();
    private final List<String> randomList = new ArrayList<>();
    private Button correct, incorrect, start, continueButton;
    private int score = 0, pos = 0, questionOne = 0;
    private final String guess = "Guess this word";
    private final SpannableString spannableString = new SpannableString(guess);
    private String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two);

        retrieveBundleInformation();
        sortIds();
        fontChange();

        start.setOnClickListener(view -> {
            start.setVisibility(View.INVISIBLE);
            correct.setVisibility(View.VISIBLE);
            incorrect.setVisibility(View.VISIBLE);
            wordsUsed[pos].setText(spannableString);
            pos++;
        });

        correct.setOnClickListener(view -> {
            score++;
            if(pos == 5) {
                wordsUsed[pos-1].setVisibility(View.INVISIBLE);
                finishGame();
            }
            else {
                wordsUsed[pos-1].setVisibility(View.INVISIBLE);
                wordsUsed[pos].setText(spannableString);
                pos++;
            }

        });

        incorrect.setOnClickListener(view -> {
            if(pos == 5) {
                wordsUsed[pos-1].setVisibility(View.INVISIBLE);
                finishGame();
            }
            else {
                wordsUsed[pos-1].setVisibility(View.INVISIBLE);
                wordsUsed[pos].setText(guess);
                pos++;
            }
        });

        continueButton.setOnClickListener(view -> continueToQuestionThree());
    }
    public void continueToQuestionThree() {
        Intent intent = new Intent(this, QuestionThree.class);
        intent.putExtra("name", name);
        intent.putExtra("questionOne", questionOne);
        intent.putExtra("questionTwo", score);
        System.out.println("[INFO] " + name + " scored a result of " + score + " in question 2");
        startActivity(intent);
    }

    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            name = patientInfo.getString("name");
            questionOne = patientInfo.getInt("questionOne");
        }
    }

    private void fontChange() {
        StyleSpan italicFont = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(italicFont, 0, guess.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void finishGame() {
        continueButton.setVisibility(View.VISIBLE);
        correct.setEnabled(false);
        incorrect.setEnabled(false);
    }

    private void sortIds() {
        wordsUsed[0] = findViewById(R.id.guessWordOne);
        wordsUsed[1] = findViewById(R.id.guessWordTwo);
        wordsUsed[2] = findViewById(R.id.guessWordThree);
        wordsUsed[3] = findViewById(R.id.guessWordFour);
        wordsUsed[4] = findViewById(R.id.guessWordFive);
        getListOfWords();

        for(int i = 0; i < wordsUsed.length; i++)
            wordsUsed[i].setText(randomList.get(i));

        correct = findViewById(R.id.correct);
        incorrect = findViewById(R.id.incorrect);
        correct.setVisibility(View.INVISIBLE);
        incorrect.setVisibility(View.INVISIBLE);

        start = findViewById(R.id.goButton);
        continueButton = findViewById(R.id.continueToQThree);
        continueButton.setVisibility(View.INVISIBLE);
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
        int listSize = this.list.size()-1;
        for (int i = 0; i <= listSize; i++) {
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
        randomList.add(list.get(randomNumbers[0]));
        randomList.add(list.get(randomNumbers[1]));
        randomList.add(list.get(randomNumbers[2]));
        randomList.add(list.get(randomNumbers[3]));
        randomList.add(list.get(randomNumbers[4]));
    }
}