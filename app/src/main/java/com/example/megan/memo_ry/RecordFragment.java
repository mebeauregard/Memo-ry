package com.example.megan.memo_ry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class RecordFragment extends Fragment
{
    FloatingActionButton recordButton = null;
    Button pauseButton = null;
    TextView recordingPrompt = null;
    int RecordPromptCount = 0;
    boolean startRecording = true;
    boolean pauseRecording = true;
    Chronometer chronometer = null;

    long timeWhenPaused = 0;

    public RecordFragment ()
    {

    }
public static RecordFragment newInstance(int position)
{
    RecordFragment recordFragment = new RecordFragment();
    Bundle b = new Bundle();
    b.putInt("position",position);
    recordFragment.setArguments(b);
    return recordFragment;
}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_fragment,container,false);

        chronometer = view.findViewById(R.id.chronometer);
        recordingPrompt = view.findViewById(R.id.recording_status);
        recordButton = view.findViewById(R.id.record_button);
        //recordButton.setColorNormal(getResources().getColor(android.R.colorPrimary));
        //recordButton.setColorPressed(getResources().getColor(android.R.colorPrimaryDark));
        return super.onCreateView(inflater, container, savedInstanceState);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(startRecording);

                startRecording = !startRecording;

            }

        });
        pauseButton = view.findViewById(R.id.pauseButton);
        pauseButton.setVisibility(view.GONE);
        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               // onPauseRecord(pauseRecording);
                pauseRecording=!pauseRecording;
            }
        });
        
        return view;
    }

    private void onRecord(boolean start) {
       // Intent i = new Intent(getActivity(),RecordingService.class);
        if(start){
            recordButton.setImageResource(stop);
            Toast.makeText(getActivity(),"recording started", Toast.LENGTH_SHORT).show();

            File folder = new File(Environment.getExternalStorageDirectory()+"/soundRecorder");
            if (!folder.exists())
            {
                folder.mkdir();
            }
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if(RecordPromptCount == 0)
                    {
                        recordingPrompt.setText("recording"+".");
                    }
                    else if (RecordPromptCount == 1)
                    {
                        recordingPrompt.setText("recording"+"..");
                    }
                    else if (RecordPromptCount == 2)
                    {
                        recordingPrompt.setText("recording" + "...");
                        RecordPromptCount = -1;
                    }
                    RecordPromptCount++;
                }


            });
            //getActivity().startService(i);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            recordingPrompt.setText("recording"+".");
            RecordPromptCount++;

        }
        else
        {
            recordButton.setImageResource(R.drawable.ic_mic_white);
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            recordingPrompt.setText("tap the button to start recording");
            //getActivity().stopService(i);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        }
    }

}

