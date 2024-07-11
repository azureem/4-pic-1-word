package com.example.a4pics1word.presentation.main_screen;

import android.os.Handler;
import android.util.Log;

import com.example.a4pics1word.data.QuestionData;
import com.example.a4pics1word.domain.MySharedPreference;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.Model model;
    private MainContract.View view;
    private int coins;
    private MySharedPreference myShar;
    private int level;
    private int hint;
    private StringBuilder myAnswer;   // *UF***
    private StringBuilder myVariant;
    private QuestionData currentQuestion;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        model = new MainModel();
        myShar = MySharedPreference.getInstance();
        level = myShar.getLevel();
        //  coins = myShar.getCoins();
        setQuestion();
    }

    private void checkingForCorrectAnswer() {
        int size = model.getQuestionCount();
        QuestionData questionData = model.getQuestionDataByIndexFrom(getLevel());
        String realAnswer = questionData.getAnswer();
        String providedAnswer = myAnswer.toString();

        if (realAnswer.equals(providedAnswer)) {
            if (size == level + 1) {
                coins = 0;

                view.openingWin();

            } else {
                view.setGreen();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.continueDialog();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.refreshAnswer();
                            }
                        }, 1500);
                    }
                }, 1500);

                view.updateCoins();
                level++;
                myAnswer.setLength(0);
                view.makeAllVisible();
            }
        } else {
                if (!myAnswer.toString().contains("*")){
                    view.setRed();
                }
        } ;
    }

    public void setQuestion() {
        view.setWhite();
        currentQuestion = model.getQuestionDataByIndexFrom(getLevel());
        view.showLevelComponent(level);
        Log.d("MainPresenter", "Setting question for level " + this.level);
        view.setQuestionToView(currentQuestion);
        String s = "".concat("*").repeat(currentQuestion.getAnswer().length());
        myAnswer = new StringBuilder(s);
        myVariant = new StringBuilder(currentQuestion.getVariant());
    }

    private boolean isFull() {
        String realAnswer = currentQuestion.getAnswer();
        if (!realAnswer.equals(myAnswer)) {

        }
        return myAnswer.indexOf("*") == -1;
    }

    @Override
    public void onDelete() {
        view.setWhite();
        myAnswer.setLength(0);
        view.makeAllVisible();
        view.refreshAnswer();
        String s = "".concat("*").repeat(currentQuestion.getAnswer().length());
        myAnswer = new StringBuilder(s);
        myVariant = new StringBuilder(currentQuestion.getVariant());
    }

    @Override
    public void onClickVariant(int index) {
        if (isFull()) return;
        view.changeButtonVisibility(index);
        char ch = currentQuestion.getVariant().charAt(index); //A
        int firstSmileIndex = myAnswer.indexOf("*");
        Log.d("TTT", "index " + firstSmileIndex);
        myAnswer.setCharAt(firstSmileIndex, ch);
        myVariant.setCharAt(index, '#');
        view.setDataToAnswerFromVariants(firstSmileIndex, String.valueOf(ch));
        checkingForCorrectAnswer();
        Log.d("QQQ", "varinatni tekwir: ");
    }


    @Override
    public void onClickAnswer(int index) {
        view.setWhite();
        if (myAnswer.charAt(index) == '*') return;
        view.removeAnswerByPos(index);
        char ch = myAnswer.charAt(index);  // A
        myAnswer.setCharAt(index, '*');
        boolean isFind = false;
        int index2 = -1;
        while (!isFind) {
            index2 = currentQuestion.getVariant().indexOf(ch, index2 + 1);
            isFind = myVariant.charAt(index2) == '#';
        }
        myVariant.setCharAt(index2, ch);
        view.backVariantByPos(index2);

    }


    @Override
    public void onClickHnt() {
        view.setWhite();
        String realAnswer = currentQuestion.getAnswer();
        if (realAnswer.length() == myAnswer.length()) {
            checkingForCorrectAnswer();
        }
        coins -= 50;
        view.updateCoins();
        int neededLetter = myAnswer.indexOf("*");
        if (neededLetter != -1) {

            char realLetter = currentQuestion.getAnswer().charAt(neededLetter);
//            boolean isFind = false;
//            int index2 = -1;
//            while (!isFind) {
//                index2 = currentQuestion.getVariant().indexOf(neededLetter, index2 + 1);
//                isFind = myVariant.charAt(index2) == '#';
//            }
            myAnswer.setCharAt(neededLetter, realLetter);

            int variantIndex = currentQuestion.getVariant().indexOf(realLetter);
            if (variantIndex != -1) {
                myVariant.setCharAt(variantIndex, '#');
                view.changeButtonVisibility(variantIndex);
            }
            view.setDataToAnswerFromVariants(neededLetter, String.valueOf(realLetter));
            checkingForCorrectAnswer();
        }

    }


    @Override
    public void setLevel(int level) {
        this.level = level;
        Log.d("JJJ", "level" + level);
    }

    @Override
    public int getLevel() {
        Log.d("JJJ", "getLevel" + coins);
        return level;

    }

    @Override
    public void setCoin(int coin) {
        this.coins = coin;
        view.updateCoins();
        Log.d("JJJ", "setCoin" + coins);
    }

    @Override
    public int getCoin() {
        Log.d("JJJ", "getCoin" + coins);
        return coins;
    }


//    private int index = 0;
//    private List<String> filledAnswer;
//    int indexOfFilled;
//    private List<AppCompatButton> variantButtons;


//    public MainPresenter(MainContract.View view) {
//        this.view = view;
//        model = new MainModel();
//        setQuestion();
//         indexOfFilled = 0;
//    }


    //  private void setQuestion() {// datalarni modelning getQuestionDataByIndexFrom methodi orqali presenter malumot olyabdi
//
//        QuestionData questionData = model.getQuestionDataByIndexFrom(index);
//        view.setQuestionToView(questionData);
//        variantButtons = view.returningVariantButtons();
//
//        int actualAnswerLength = questionData.getAnswer().length();
//        filledAnswer = new ArrayList<>(actualAnswerLength);
//        for (int i = 0; i < actualAnswerLength; i++) {
//            filledAnswer.add(null);
//        }
//    }
//

//    public void checkingForCorrectAnswer() {
//        QuestionData questionData = model.getQuestionDataByIndexFrom(this.index);
//        String currentAnswer = questionData.getAnswer();
//           StringBuilder filledAnswersString =  new StringBuilder();
//
//        for (String letter : filledAnswer) {
//            filledAnswersString.append(letter);
//        }
//
//        String a = filledAnswersString.toString();
//           if (a.equals(currentAnswer)){
//               index++;
//               filledAnswer.clear();
//               setQuestion();
//           }else{
//               return;
//           }
//        }

//    @Override
//    public void onClickVariant(int index) {
//        if (isFull()){
//            checkingForCorrectAnswer();
//        }
//        QuestionData questionData = model.getQuestionDataByIndexFrom(this.index);
//        String vrLetter = String.valueOf(questionData.getVariant().charAt(index));
//        int indexPosition = getNeedPosition();
//        if (isAnswerLengthFilled()) {
//            indexOfFilled++;
//            filledAnswer.set(indexPosition, vrLetter);
//            view.setDataToAnswerFromVariants(indexPosition, vrLetter);
//            view.changeButtonVisibility(index);
//        }
//    }
//
//    @Override
//    public void onClickAnswer(int index) {
//        String clickedAnswerText = filledAnswer.get(index);
//        if (clickedAnswerText != null) {
//            filledAnswer.set(index, null);
//            view.setDataToAnswerFromVariants(index, "");
//            int correspondingVariantIndex = findCorrespondingVariantThatHasBeenClicked(clickedAnswerText);
//            if (correspondingVariantIndex != -1) {
//            view.changingInvisibleToVisible(correspondingVariantIndex);
//            }
//        }
//    }
//    @SuppressLint("SuspiciousIndentation")
//    private int findCorrespondingVariantThatHasBeenClicked(String clickedanswerText) {
//        QuestionData questionData = model.getQuestionDataByIndexFrom(this.index);
//        String variant = questionData.getVariant();
//        for (int i = 0; i < variant.length(); i++) {
//            String variantsLetter = String.valueOf(variant.charAt(i));
//            if (clickedanswerText.equals(variantsLetter)) {
//                if (variantButtons.get(i).getVisibility() != View.VISIBLE)
//                return i;
//            }
//        }
//        return -1;
//    }
//
//
//    private boolean isAnswerLengthFilled() {
//        return filledAnswer.contains(null);
//    }
//
//    private int getNeedPosition() {
//        return filledAnswer.indexOf(null);
//    }
//
//    private boolean isFull(){
//        return filledAnswer.size() == indexOfFilled;
//    }
}
