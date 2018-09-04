package com.example.xgramajo.parkme_ids_2018.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xgramajo.parkme_ids_2018.Parking.SetupFragment;
import com.example.xgramajo.parkme_ids_2018.R;

import java.util.Locale;

public class TimeLeftFragment extends Fragment {

    private TextView mTextViewCountDown;
    private TextView mPatentCounter;


    private CountDownTimer mCountDownTimer;

    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;

    Button finishBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_left, container, false);


        mPatentCounter = (TextView) view.findViewById(R.id.patent_counter);

        finishBtn = (Button) view.findViewById(R.id.finish_btn);





        // Inicialización de la vista para el contador
        mTextViewCountDown = (TextView) view.findViewById(R.id.text_view_countdown);


        mPatentCounter.setText(SetupFragment.getMatricula());

        // Obtenemos el tiempo y lo almacenamos como entrada
        String input = (SetupFragment.getTime());

        // Convierte el tiempo en texto a milisegundos
        long millisInput = Long.parseLong(input) * 60000;

        // Pasa la variable en milisegundos al tiempo inicial en milisegundos del contador
        setTime(millisInput);

        // Inicia el temporizador
        startTimer();

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "DB Firebase", Toast.LENGTH_LONG).show();
                HomeActivity.setHomeFragment();
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });

        return view;
    }

    private void setTime(long milliseconds){
        //  asigna al tiempo inicial los milisegundos
        mStartTimeInMillis = milliseconds;
        // reinicia el temporizador
        resetTimer();
    }


    private void startTimer(){

        // Tiempo final es igual al tiempo actual del sistema mas el tiempo restante
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        // inicia el temporizador
        // metodo disparado a intervalos regulares marcados en el intervalo del contador cada 1000 milisegundos (1 segundo)
       mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                // actualiza el texto de la vista del contador
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                // metodo disparado cuando se termina el tiempo

                // Esto lo asignamos manualmente porque la clase CountDownTimer termina en 00:01
                   mTimeLeftInMillis=0;
                    // ver mas info en https://www.youtube.com/watch?v=MDuGwI6P-X8&list=PLrnPJCHvNZuB8wxqXCwKw2_NkyEmFwcSd
                    // Y en https://stackoverflow.com/questions/6810416/android-countdowntimer-shows-1-for-two-seconds

                // actualiza el texto de la vista del contador
                updateCountDownText();



            }
        }.start(); // inicia el contador regresivo
        //mTimerRunning = true; // activa el temporizador corriendo
    }

    //private void pauseTimer(){
        //mCountDownTimer.cancel();
        //cancela el contador
        //mTimerRunning = false;
        // desactiva el temporizador corriendo

    //}

    private void resetTimer(){
        // pone el tiempo restante al tiempo inicial
        mTimeLeftInMillis = mStartTimeInMillis;
        // actualiza el texto de la vista del contador
        updateCountDownText();

   }

    private void updateCountDownText(){

        // actualiza el texto de la vista del contador
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        // Pasa a un string el tiempo restante formateado en horas minutos y segundos en la forma 00:00:00 o 00:00 si corresponde.
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        mTextViewCountDown.setText(timeLeftFormatted);
        //Pasa a la vista del contador el tiempo restante
    }



}