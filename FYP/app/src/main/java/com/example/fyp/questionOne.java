package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class questionOne extends AppCompatActivity {

    ImageView imageView;
    Button nextButton, continueButton;

    Random r;
    private final Integer[] viewImage = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14,
            R.drawable.image15,
            R.drawable.image16,
            R.drawable.image17,
            R.drawable.image18,
            R.drawable.image19,
            R.drawable.image20
    };

    private final List<Integer> imageList = Arrays.asList(viewImage);
    private final List<Integer> randomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_one);

        imageView = findViewById(R.id.imageView);
        nextButton = findViewById(R.id.next);
        continueButton = findViewById(R.id.continueToQTwo);
        continueButton.setVisibility(View.INVISIBLE);
        r = new Random();

        createImageList();
        imageView.setImageResource(randomList.get(0));
        randomList.remove(0);
        nextButton.setOnClickListener(view -> {
            if(!randomList.isEmpty()) {
                imageView.setImageResource(randomList.get(0));
                randomList.remove(0);
            }
            else {
                nextButton.setVisibility(View.INVISIBLE);
                continueButton.setVisibility(View.VISIBLE);
            }
        });
        continueButton.setOnClickListener(v -> continueToQuestionTwo());
    }

    public void continueToQuestionTwo() {
        Intent intent = new Intent(this, questionTwo.class);
        startActivity(intent);
    }

    private void shuffleList(List<Integer> imageList) {
        Collections.shuffle(imageList);
        Collections.shuffle(imageList);
        Collections.shuffle(imageList);
    }

    private void createImageList() {
        int [] randomNumbers = new int [5];
        shuffleList(this.imageList);
        int listSize = this.imageList.size();
        for (int i = 1 ; i <= listSize; i++) {
            randomNumbers[0] = (int)(Math.random()*listSize)+1;

            while (randomNumbers[1] == randomNumbers[0])
            {
                randomNumbers[1] = (int)(Math.random()*listSize)+1;
            }
            while ((randomNumbers[2] == randomNumbers[1]) || (randomNumbers[2] == randomNumbers[0]) )
            {
                randomNumbers[2] = (int)(Math.random()*listSize)+1;
            }
            while ((randomNumbers[3] == randomNumbers[0]) || (randomNumbers[3] == randomNumbers[1]) || (randomNumbers[3] == randomNumbers[2]) )
            {
                randomNumbers[3] = (int)(Math.random()*listSize)+1;
            }
            while ((randomNumbers[4] == randomNumbers[0]) ||
                    (randomNumbers[4] == randomNumbers[1]) ||
                    (randomNumbers[4] == randomNumbers[2]) ||
                    (randomNumbers[4] == randomNumbers[3]) )
            {
                randomNumbers[4] = (int)(Math.random()*listSize)+1;
            }

        }
        this.randomList.add(this.imageList.get(randomNumbers[0]));
        this.randomList.add(this.imageList.get(randomNumbers[1]));
        this.randomList.add(this.imageList.get(randomNumbers[2]));
        this.randomList.add(this.imageList.get(randomNumbers[3]));
        this.randomList.add(this.imageList.get(randomNumbers[4]));
    }
}