package com.example.a4pics1word.presentation.dialog_screen;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.a4pics1word.R;
import com.example.a4pics1word.presentation.main_screen.MainPresenter;

public class
DialogActivity extends DialogFragment {

    private MainPresenter mainPresenter;

    LottieAnimationView fireAnim;
    LottieAnimationView coinAnim;
    LottieAnimationView addCoin;
    ConstraintLayout dialogConstr;
    TextView twentyFive;

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
       @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog, container, false);


        twentyFive = view.findViewById(R.id.dlg_coin);
        twentyFive.setVisibility(View.VISIBLE);

        dialogConstr = view.findViewById(R.id.constrdialog);
        dialogConstr.setVisibility(View.VISIBLE);
        fireAnim = view.findViewById(R.id.fire_anim);
        fireAnim.playAnimation();
        coinAnim = view.findViewById(R.id.coin_anim);
        coinAnim.playAnimation();

        addCoin =  view.findViewById(R.id.addcoin_anim);


        Button btnContinue = view.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  dialogConstr.setVisibility(View.GONE);
                coinAnim.setVisibility(View.GONE);
                twentyFive.setVisibility(View.GONE);
                addCoin.playAnimation();

                if (mainPresenter != null) {

                    //mainPresenter.setQuestion();
                    int i = mainPresenter.getCoin();
                    int m = i + 25;


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mainPresenter.setQuestion();
                        }
                    }, 1500);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mainPresenter.setCoin(m);
                        }
                    }, 1500); // Delay for 1500 milliseconds (1.5 seconds)
                }


                // Delay dismissal using a Handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 1500); // Delay for 1500 milliseconds (1.5 seconds)
            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();
        this.getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}