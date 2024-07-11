package com.example.a4pics1word.presentation.main_screen;

import com.example.a4pics1word.data.QuestionData;
import com.example.a4pics1word.domain.AppControllerImplement;

public class MainModel implements MainContract.Model {
    private AppControllerImplement controller = AppControllerImplement.init();

    @Override
    public QuestionData getQuestionDataByIndexFrom(int index) {  //zanjir,endi bu yerda model appcontrollerImpl dan data olishi,
       return controller.getQuestion(index);

    }

    @Override
    public int getQuestionCount() {

        return controller.getQuestionList().size();
    }

}
