package com.zombie.deliziusz.appnotas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class grabadora extends AppCompatActivity {

    private MediaRecorder grabacion;
    private String archivoSalida = null; //asignamos un nombre a la pista de audio que vamos a agregar
    private Button btn_recorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabadora);
        btn_recorder = (Button)findViewById(R.id.btn_rec);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(grabadora.this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

    }

         void Recorder(View view){
            if(grabacion == null){
                archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Grabacion.mp3";
                grabacion = new MediaRecorder();
                grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
                grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                grabacion.setOutputFile(archivoSalida);

                try{
                    grabacion.prepare();
                    grabacion.start();
                }catch (IOException e){
                }

                btn_recorder.setBackgroundResource(R.drawable.rec);
                Toast.makeText(getApplicationContext(), "Grabando...", Toast.LENGTH_SHORT).show();
            }else if(grabacion!= null) {
                grabacion.stop();
                grabacion.release();
                grabacion = null;
                btn_recorder.setBackgroundResource(R.drawable.stop_rec);
                Toast.makeText(getApplicationContext(), "Grabación finalizada...", Toast.LENGTH_SHORT).show();
            }

    }


}
