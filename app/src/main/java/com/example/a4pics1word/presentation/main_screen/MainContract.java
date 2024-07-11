package com.example.a4pics1word.presentation.main_screen;

import com.example.a4pics1word.data.QuestionData;

public interface MainContract {

    interface Model {
        QuestionData getQuestionDataByIndexFrom(int index);// app repositoryadan questionlistdan birma bir so'ralgan indexi bo'ylab savolni olib beradi

        int getQuestionCount();
    }


    interface View {
        void updateCoins();

        void openingWin();

        void continueDialog();

        void showLevelComponent(int level);

        void setQuestionToView(QuestionData questionData); // kelayotkan question datalarini viewga set qilish yani qiymatni

        void changeButtonVisibility(int index); // buttonlar holatini click ozgartirish yangi boskanda invisible qilish

        void setDataToAnswerFromVariants(int index, String value); // variantdagi malumotni answerga olib otish

        void removeAnswerByPos(int pos);

        void backVariantByPos(int pos);

        void refreshAnswer();

       void showNotEnoughCoinMsg();
       void setRed();
       void setGreen();

       void setWhite();

        void makeAllVisible();
        // List<AppCompatButton> returningVariantButtons();

//        void justClickVariantsFromView(int index);  //varinatni bosish uchun
//        void justClickAnswerFromView(int index);    //javobni bosish
        //   void refreshVAriants();
        // void changingInvisibleToVisible(int index);

    }


    interface Presenter {
        void onDelete();
        void onClickVariant(int index);

        void onClickAnswer(int index);

        void onClickHnt();

        void setLevel(int level);

        int getLevel();

        void setCoin(int level);

        int getCoin();


        //void returningLevel();

        // void checkingForCorrectAnswer();


    }
}
