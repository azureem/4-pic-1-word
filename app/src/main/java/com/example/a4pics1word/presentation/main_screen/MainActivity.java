package com.example.a4pics1word.presentation.main_screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.a4pics1word.R;
import com.example.a4pics1word.data.QuestionData;
import com.example.a4pics1word.domain.MySharedPreference;
import com.example.a4pics1word.presentation.dialog_screen.DialogActivity;
import com.example.a4pics1word.presentation.dialog_screen.DialogBack;
import com.example.a4pics1word.presentation.dialog_screen.DialogDelete;
import com.example.a4pics1word.presentation.dialog_screen.DialogHint;
import com.example.a4pics1word.presentation.dialog_screen.DialogNoMoney;
import com.example.a4pics1word.presentation.result_screen.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private ImageView imageQuestion1;
    private ImageView imageQuestion2;
    private ImageView imageQuestion3;
    private ImageView hint;
    private ImageView deleteAll;
    private ImageView imageQuestion4;
    private List<AppCompatButton> answerButtons = new ArrayList<>();
    private List<AppCompatButton> variantButtons = new ArrayList<>();
    private TextView levelTextView;
    private TextView coinHolder;
    int shareLevel;

    QuestionData current;
    int coinInShared;
    LinearLayoutCompat answer;
    LinearLayoutCompat varinat1;
    LinearLayoutCompat variant2;
    private MySharedPreference myShar;
    int deleteTimes;



    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findComponentsButtons();
        presenter = new MainPresenter(this);
        myShar = MySharedPreference.getInstance();
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#459C93"));

       //coinInShared = myShar.getCoins();

        hint = findViewById(R.id.hint);
        hint.setOnClickListener(v->{
            if (coinInShared>=50){
                Log.d("WWW", "dddddddddd " + coinInShared);
                openHintDialog();
          }else {
              showNotEnoughCoinMsg();
          }
        });


        deleteAll = findViewById(R.id.delete_all);
        deleteAll.setOnClickListener(v->{

            if(myShar.getDelete() != 1){
                clickDelete();
            }
            myShar.saveDelete(1);

            presenter.onDelete();
        });
    }

    // bu method UI xml dagi buttonlar componentlarni topib, muayyan bir maydonga doimiy qilib saqlab olish vazifasini bajaradi
    private void findComponentsButtons() { // UI qismidan componentalarimzini topib olamiz, yani imagequestion aynan rasm savol qoyilishi kerak bolgan joy

       // hint = findViewById(R.id.hint);

        imageQuestion1 = findViewById(R.id.question_container1);
        imageQuestion2 = findViewById(R.id.question_container2);
        imageQuestion3 = findViewById(R.id.question_container3);
        imageQuestion4 = findViewById(R.id.question_container4);
        coinHolder = findViewById(R.id.coin_holder);
        levelTextView = findViewById(R.id.level_holder);
//        hint.setOnClickListener(v -> {
//            presenter.onClickHnt();
//        });
         answer = findViewById(R.id.ansewerContainer);
         MediaPlayer answerSound =  MediaPlayer.create(this, R.raw.answer1);//bu yerda linearni ichidagi child kari answer buttonlarni listga saqlab oldik
        for (int i = 0; i < answer.getChildCount(); i++) {
            AppCompatButton button = (AppCompatButton) answer.getChildAt(i);
            int index = i;
            button.setOnClickListener(v -> {
            answerSound.start();
                presenter.onClickAnswer(index);
            });
            answerButtons.add(button);
        }
         varinat1 = findViewById(R.id.variantContainer1);//bu yerda linearni ichidagi childlari 1 qator variant buttonlarni listga saqlab oldik
        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.variant);
        for (int i = 0; i < varinat1.getChildCount(); i++) {
            AppCompatButton button = (AppCompatButton) varinat1.getChildAt(i);
            int index = i;
            button.setOnClickListener(v -> {
                Log.d("RRR", "findComponentsButtons: bosil");
                mediaPlayer2.start();
                presenter.onClickVariant(index);
            });
            variantButtons.add(button);
        }
         variant2 = findViewById(R.id.variantContainer2);//bu yerda linearni ichidagi childlari 2 qator variant buttonlarni listga saqlab oldik
        MediaPlayer mediaPlayer3 = MediaPlayer.create(this, R.raw.variant);
        for (int i = 0; i < variant2.getChildCount(); i++) {
            AppCompatButton button = (AppCompatButton) variant2.getChildAt(i);
            int index = i + varinat1.getChildCount();
            button.setOnClickListener(v -> {
                mediaPlayer3.start();
                presenter.onClickVariant(index);
            });//bu variantni boskanimizda INVISIBLE qilishimiz kerak
            variantButtons.add(button);
        }
    }

    @Override
    public void updateCoins() {
        coinInShared = presenter.getCoin();
        coinHolder.setText(String.valueOf(presenter.getCoin()));
    }

    @Override
    public void openingWin() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    @Override
    public void continueDialog() {
        Log.d("JJJ", "winDialog: ");
        DialogActivity dialogFragment = new DialogActivity();
        dialogFragment.setMainPresenter((MainPresenter) presenter);
        dialogFragment.show(getSupportFragmentManager(), "custom_dialog");
        dialogFragment.setCancelable(false);
    }

    @Override
    public void showLevelComponent(int level) {
        if (levelTextView != null) {
            shareLevel = level;
            levelTextView.setText(String.valueOf(level + 1));
        } else {
            Log.e("MainActivity", "levelTextView is null");
        }
    }

//    public void showLevelComponent() {
//        j =  presenter.returningLevel();
//       level_holder.setText(String.valueOf(j));
//    }

    @Override
//mavjud buttonlarnga data yuklash lekin data togridan togri kelmaydi, bu methodaga datani presenterning setQuestion methodi data olib beradi modeldan, model esa appControllerda olib presenterga beradi
    public void setQuestionToView(QuestionData questionData) {
        current = questionData;//model-> presenterdan kelayotkan malumotni UI ga joylash // buttonni activityga chiqarish
        imageQuestion1.setImageResource(questionData.getQuestion1());
        imageQuestion2.setImageResource(questionData.getQuestion2());
        imageQuestion3.setImageResource(questionData.getQuestion3());
        imageQuestion4.setImageResource(questionData.getQuestion4());
        String answer = questionData.getAnswer();
        for (int i = 0; i < answerButtons.size(); i++) {
            if (i < answer.length()) {
                answerButtons.get(i).setVisibility(View.VISIBLE);
            } else {
                answerButtons.get(i).setVisibility(View.GONE);
            }
        }
        char[] variants = questionData.getVariant().toCharArray(); //savolning variantlarini variants ozgaruvchisiga saqlayabmiz
        for (int i = 0; i < variantButtons.size(); i++) {//find comoponentsda listga saqlab olgan buttonlarni yurib chhiqib
            AppCompatButton button = variantButtons.get(i);//har biriga
            button.setText(String.valueOf(variants[i]));//-> variantsni char arryadagi letterlarni buttoga yuklab chiqish
        }

    }

    @Override
    protected void onPause() {
        myShar.saveLevel(presenter.getLevel());
        myShar.saveCoins(presenter.getCoin());

        super.onPause();

    }

    //
    @Override
    protected void onResume() {
        presenter.setCoin(myShar.getCoins());
        presenter.setLevel(myShar.getLevel());

        super.onResume();


    }
    //    @Override
//    public void justClickVariantsFromView(int index) {
//        presenter.onClickVariant(index);
//        changeButtonVisibility(index);
//    }
//
//    @Override
//    public void justClickAnswerFromView(int index) {
//        presenter.onClickAnswer(index);
//    }

    @Override
    public void changeButtonVisibility(int index) {
        variantButtons.get(index).setVisibility(View.INVISIBLE);

    }

    private void clickDelete(){
        DialogDelete dlt = new DialogDelete();
        dlt.show(getSupportFragmentManager(), "custom_dialog");
        dlt.setCancelable(false);
    }

    @Override
    public void setDataToAnswerFromVariants(int indexPosition, String value) {
        answerButtons.get(indexPosition).setText(value);
    }


    @Override
    public void removeAnswerByPos(int pos) {

        answerButtons.get(pos).setText("");
    }

    @Override
    public void backVariantByPos(int pos) {
        variantButtons.get(pos).setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshAnswer() {
        for (int i = 0; i < 8; i++) {
            answerButtons.get(i).setText("");
        }
    }

    @Override
    public void showNotEnoughCoinMsg() {
        DialogNoMoney noDialog = new DialogNoMoney();
        noDialog.show(getSupportFragmentManager(), "custom_dialog");
        noDialog.setCancelable(false);
    }

    @Override
    public void setRed() {
        for (int i = 0; i < answer.getChildCount(); i++) {
            AppCompatButton button = (AppCompatButton) answer.getChildAt(i);
           button.setTextColor(Color.RED);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
        }
    }

    @Override
    public void setGreen() {
        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.winring);
        for (int i = 0; i < answer.getChildCount(); i++) {
            AppCompatButton button = (AppCompatButton) answer.getChildAt(i);
            int green = 0xFF459C93; //
            button.setTextColor(green);
            mediaPlayer2.start();
        }
    }

    //   @Override
//    public void setGreen() {
//        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.winring);
//        for (int i = 0; i < answer.getChildCount(); i++) {
//            AppCompatButton button = (AppCompatButton) answer.getChildAt(i);
//            int green = 0xFF459C93; //
//            button.setTextColor(green);
//            mediaPlayer2.start();
//        }
//    }

    @Override
    public void setWhite() {
        for (int i = 0; i < answer.getChildCount(); i++) {
            AppCompatButton button = (AppCompatButton) answer.getChildAt(i);
            int darkGray = 0xFF292D32;
            button.setTextColor(darkGray);

        }
    }


//    @Override
//    public void setWhite() {
//        for (int i = 0; i < answer.getChildCount(); i++) {
//            AppCompatButton button = (AppCompatButton) answer.getChildAt(i);
//            button.setTextColor(Color.WHITE);
//
//        }
//    }

    private void openHintDialog(){
        DialogHint hintDlg = new DialogHint();
        hintDlg.show(getSupportFragmentManager(), "custom_dialog");
        hintDlg.setMainPresenter((MainPresenter) presenter);
        hintDlg.setCancelable(false);
    }

//    @Override
//    public void refreshVAriants() {
//        variantButtons.clear();
//    }

    @Override
    public void makeAllVisible() {
        for (int i = 0; i < variantButtons.size(); i++) {
            variantButtons.get(i).setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    public List<AppCompatButton> returningVariantButtons() {
//        return variantButtons;
//    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        DialogBack dlg = new DialogBack();
        dlg.show(getSupportFragmentManager(), "custom_dialog");
        dlg.setCancelable(false);

    }
}