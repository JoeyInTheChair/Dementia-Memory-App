package com.example.finalyearproject;

public class PatientResult {
    public int questionOne;
    public int questionTwo;
    public int questionThree;
    public int questionFour;
    public int questionFive;

    void PatientProfile() {
        this.questionOne = 0;
        this.questionTwo = 0;
        this.questionThree = 0;
        this.questionFour = 0;
        this.questionFive = 0;
    }

    public void PatientProfile(int a, int b, int c, int d, int e) {
        this.questionOne = a;
        this.questionTwo = b;
        this.questionThree = c;
        this.questionFour = d;
        this.questionFive = e;
    }

    public void setQuestionOne(int i) { this.questionOne = i; }
    public void setQuestionTwo(int i) { this.questionTwo = i; }
    public void setQuestionThree(int i) { this.questionThree = i; }
    public void setQuestionFour(int i) { this.questionFour = i; }
    public void setQuestionFive(int i) { this.questionFive = i; }

    public int getQuestionOne() { return this.questionOne; }
    public int getQuestionTwo() { return this.questionTwo; }
    public int getQuestionThree() { return this.questionThree; }
    public int getQuestionFour() { return this.questionFour; }
    public int getQuestionFive() { return this.questionFive; }

    public void getResult() {
        System.out.println("[Question One]: " + getQuestionOne());
        System.out.println("[Question Two]: " + getQuestionTwo());
        System.out.println("[Question Three]: " + getQuestionThree());
        System.out.println("[Question Four]: " + getQuestionFour());
        System.out.println("[Question Five]: " + getQuestionFive());
    }
}
