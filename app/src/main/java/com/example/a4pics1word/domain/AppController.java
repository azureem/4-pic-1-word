package com.example.a4pics1word.domain;

import com.example.a4pics1word.data.QuestionData;

import java.util.List;

public interface AppController {
    void loadQuestions(); //savollarni yuklaydigan method

    List<QuestionData> getQuestionList(); //savollar listini qaytarib beradigan method


    QuestionData getQuestion(int level);// har bitta savolni indexi boylab olib beradgan method
}
