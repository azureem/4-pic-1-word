package com.example.a4pics1word.data;

public class QuestionData {
    // question images
    private int question1;
    private int question2;
    private int question3;
    private int question4;


    //answer
    private String answer;


    // variants
    private String variant;



    //
    public void setQuestion1(int question) {
        this.question1 = question;
    }

    public void setQuestion2(int question) {
        this.question2 = question;
    }

    public void setQuestion3(int question) {
        this.question3 = question;
    }

    public void setQuestion4(int question) {
        this.question4 = question;
    }




    //setting answer   yani kelgan javobni set qilish
    public void setAnswer(String answer) {
        this.answer = answer;
    }


    // set variant yani variantni set qilish

    public void setVariant(String variant) {
        this.variant = variant;
    }


    // bular har bir datani get qilb olib berish

    public int getQuestion1() {
        return question1;
    }

    public int getQuestion2() {
        return question2;
    }

    public int getQuestion3() {
        return question3;
    }

    public int getQuestion4() {
        return question4;
    }


    public String getAnswer() {
        return answer;
    }


    public String getVariant() {
        return variant;
    }

    public QuestionData(int question1, int question2, int question3, int question4, String answer, String variant) {
        this.question1 = question1;
        this.question2 =  question2;
        this.question3 =  question3;
        this.question4 =  question4;

        this.answer = answer;
        this.variant = variant;
    }

}
