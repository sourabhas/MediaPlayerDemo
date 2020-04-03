package com.soushetty.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;


/*reference: https://developer.android.com/guide/topics/media/mediaplayer*/


//add the audio file into the raw directory created inside "res".
//Added two buttons ,one play and one pause to perform the desired
public class MainActivity extends AppCompatActivity {
    //MediaPlayer class is the primary API for playing sound and video.
    private MediaPlayer mediaPlayer;
    private Button play;
    private Button pause;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=findViewById(R.id.play_button);
        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.beautiful_feat);
        seekbar=findViewById(R.id.seekBar);

                seekbar.setMax(mediaPlayer.getDuration());
                play.setOnClickListener(new View.OnClickListener() { //when user clicks PLAY button
                    @Override
                    public void onClick(View v) {
                        if(!mediaPlayer.isPlaying()){
                            playMusic();
                        }
                    }
                });


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){ //true if the user has touched the seekbar
                    mediaPlayer.seekTo(progress); //going to that particular time duration

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        pause=findViewById(R.id.pause_button);
        pause.setOnClickListener(new View.OnClickListener() { //when user clicks PAUSE button
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    pauseMusic();
                }
            }
        });

    }
//to start the music
    private void playMusic() {
        if(mediaPlayer!=null){
            mediaPlayer.start();
           // button.setText("Pause");
        }
    }
//to pause the music which is already playing
    private void pauseMusic() {
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            // button.setText("Play");
        }
    }

    //Mandatory clean up's to do at the end
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            mediaPlayer.release(); //to remove it from memory while leaving the activity

        }
    }
}
