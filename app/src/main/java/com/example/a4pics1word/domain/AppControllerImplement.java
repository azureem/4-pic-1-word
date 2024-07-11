package com.example.a4pics1word.domain;

import android.util.Log;

import com.example.a4pics1word.R;
import com.example.a4pics1word.data.QuestionData;

import java.util.ArrayList;
import java.util.List;

public class AppControllerImplement implements  AppController{
    private static AppControllerImplement appController;
    private List<QuestionData> questionList ;

    private AppControllerImplement(){
        questionList = new ArrayList<>();
        loadQuestions();
    }

    public static AppControllerImplement init(){
        if (appController == null){
            appController = new AppControllerImplement();
        }
       return appController;
    }


    @Override
    public List<QuestionData> getQuestionList() {
        return questionList;
    }

    @Override
    public QuestionData getQuestion(int level) {
        return questionList.get(level);
    }
    @Override
    public void loadQuestions() {
        questionList.add(new QuestionData(R.drawable.purple1, R.drawable.purple2, R.drawable.purple3 , R.drawable.purple4,"purple", "orurplegadepq"));
        questionList.add(new QuestionData(R.drawable.key1,R.drawable.key2, R.drawable.key3 , R.drawable.key4,"key", "ijispktyetyi"));
        questionList.add(new QuestionData(R.drawable.forest1,R.drawable.forest2, R.drawable.forest3 , R.drawable.forest4,"forest", "ojifpkryesyt"));
        questionList.add(new QuestionData(R.drawable.coding1,R.drawable.coding2, R.drawable.coding3 , R.drawable.coding4,"coding", "opiacmdgernm"));//programmin
        questionList.add(new QuestionData(R.drawable.think3,R.drawable.think2, R.drawable.think1 , R.drawable.think4,"think", "pltdhweiznqk"));//programmin
        questionList.add(new QuestionData(R.drawable.tennis1,R.drawable.tennis2, R.drawable.tennis3 , R.drawable.tennis4,"tennis", "iehthwnznpsk"));//programmin
        questionList.add(new QuestionData(R.drawable.vehicle1,R.drawable.vehicle2, R.drawable.vehicle3 , R.drawable.vehicle4,"vehicle", "ceitevnlnphk"));//programmin
        questionList.add(new QuestionData(R.drawable.school1,R.drawable.school2, R.drawable.school3 , R.drawable.school4,"school", "oectevhlnsho"));//programmin
        questionList.add(new QuestionData(R.drawable.apple1,R.drawable.apple2, R.drawable.apple3 , R.drawable.apple4,"apple", "ekcpevalnshp"));//programmin
        questionList.add(new QuestionData(R.drawable.chicken1,R.drawable.chicken2, R.drawable.chicken3 , R.drawable.chicken4,"chicken", "ekcpecalnihp"));//programmin
        questionList.add(new QuestionData(R.drawable.mouse1,R.drawable.mouse2, R.drawable.mouse3 , R.drawable.mouse4,"mouse", "skcuekamniho"));//programmin
        questionList.add(new QuestionData(R.drawable.honey1,R.drawable.honey2, R.drawable.honey3 , R.drawable.honey4,"honey", "ykchekanoiho"));//programmin
        questionList.add(new QuestionData(R.drawable.water1,R.drawable.water2, R.drawable.water3 , R.drawable.water4,"water", "rkahekatoihw"));//programmin
        questionList.add(new QuestionData(R.drawable.run1,R.drawable.run2, R.drawable.run3 , R.drawable.run4,"run", "wnaheuatoihr"));//programmin
        questionList.add(new QuestionData(R.drawable.smile1,R.drawable.smile2, R.drawable.smile3 , R.drawable.smile4,"smile", "ekaesuamoihl"));//programmin
        questionList.add(new QuestionData(R.drawable.ice1,R.drawable.ice2, R.drawable.ice3 , R.drawable.ice4,"ice", "wkaheuctoihr"));//programmin
        questionList.add(new QuestionData(R.drawable.nation11,R.drawable.nation2, R.drawable.nation3 , R.drawable.nation4,"nation", "okaneuctoihn"));//programmin
        questionList.add(new QuestionData(R.drawable.cinema1,R.drawable.cinema2, R.drawable.cinema3 , R.drawable.cinema4,"cinema", "mkaneuctoihn"));//programmin
        questionList.add(new QuestionData(R.drawable.pink1,R.drawable.pink2, R.drawable.pink3 , R.drawable.pink4,"pink", "olapeuktoihn"));//programmin
        questionList.add(new QuestionData(R.drawable.finish1,R.drawable.finish2, R.drawable.finish3 , R.drawable.finish4,"finish", "jliheskfoihn"));//programmin
        Log.d("AppControllerImplement", "Loading questions...");
    }



}
