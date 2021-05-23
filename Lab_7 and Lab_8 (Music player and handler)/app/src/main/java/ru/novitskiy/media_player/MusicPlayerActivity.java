package ru.novitskiy.media_player;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer = null;
    private int songIndex = -1;
    private ArrayList<SongInfo> songList;

    private SeekBar seekBar;

    //TextView min_TextView = (TextView)findViewById(R.id.min_textView);
    //TextView max_TextView = (TextView)findViewById(R.id.max_textView);
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        //Intent intent = getIntent();
        Bundle arguments = getIntent().getExtras();
        songIndex = arguments.getInt("position", -1);
        if(arguments != null)
        {
            try
            {
                songList = (ArrayList<SongInfo>)arguments.getSerializable(SongInfo.class.getSimpleName());
                SetTrackInfo();
            }
            catch (Exception e)
            {
                TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
                helper_TextView.setText("Ошибка при принятиии SongInfo");
            }
        }

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                seekChange(v);
                return false;
            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    counter++;
                    double value_time = ChangeText();

                    seekBar.setProgress((int)value_time);

                    TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
                    helper_TextView.setText(String.valueOf(value_time));
                    handler.postDelayed(this, 1000);
                }
                catch (Exception e)
                {
                    TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
                    helper_TextView.setText("Значение не изменилось");
                }
            }
        };
        runnable.run();
    }

    private double ChangeText()
    {
        try
        {
            TextView min_TextView = (TextView)findViewById(R.id.min_textView);
            TextView max_TextView = (TextView)findViewById(R.id.max_textView);

            //double finalTime = seekBar.getProgress();
            double finalTime = mediaPlayer.getCurrentPosition();

            //TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
            //helper_TextView.setText(String.valueOf(finalTime));

            //mediaPlayer.seekTo(seekBar.getProgress());
            min_TextView.setText(String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    finalTime)))
            );

            double finalTime2 = seekBar.getMax() - finalTime;

            max_TextView.setText(String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime2),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime2) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    finalTime2)))
            );

            return finalTime;
        }
        catch (Exception e)
        {
            TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
            helper_TextView.setText("Проблемы автоизменении колеса прокрутки");
            return 0.0;
        }
    }

    private void seekChange(View v)
    {
        if(mediaPlayer.isPlaying())
        {
            seekBar = (SeekBar)v;
            mediaPlayer.seekTo(seekBar.getProgress());
        }
        ChangeText();
    }

    private void SetTrackInfo()
    {
        TextView title_TextView = (TextView)findViewById(R.id.title_textView);
        TextView artist_TextView = (TextView)findViewById(R.id.artist_textView);
        title_TextView.setText(songList.get(songIndex).getTitle());
        artist_TextView.setText(songList.get(songIndex).getArtist());

        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(songList.get(songIndex).getPath());
        byte[] cover = metaRetriever.getEmbeddedPicture();
        if(cover != null)
        {
            Bitmap image = BitmapFactory.decodeByteArray(cover, 0, cover.length);
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
        }
    }

    private void Play()
    {
        if (mediaPlayer != null)
        {
            if (mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }

        if ((songIndex < songList.size()) && (songIndex >= 0))
        {
            SetTrackInfo();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer)
                {
                    seekBar.setProgress(0);
                    if (songIndex + 1 < songList.size())
                    {
                        songIndex++;
                        Play();
                    }
                }
            });
            try
            {
                mediaPlayer.setDataSource(songList.get(songIndex).getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();

                seekBar.setMax(mediaPlayer.getDuration());
                ChangeText();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    // Нажатие на кнопку "Играть"
    public void PlayClick(View view)
    {
        try
        {
            Button playButton = (Button)findViewById(R.id.button_play);

            if(playButton.getText() == getString(R.string.play))
            {
                if (mediaPlayer == null)
                {
                    Play();
                }
                else if (!mediaPlayer.isPlaying())
                {
                    mediaPlayer.start();
                }

                playButton.setText(getString(R.string.pause));
            }
            else if(playButton.getText() == getString(R.string.pause))
            {
                if (mediaPlayer != null && mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                }

                playButton.setText(getString(R.string.play));
            }
            else
            {
                TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
                helper_TextView.setText("Проблемы с проверкой текста");
            }
        }
        catch (Exception e)
        {
            TextView helper_TextView = (TextView)findViewById(R.id.helper_textView);
            helper_TextView.setText("Проблемы с проверкой текста");
        }
    }

    // Нажатие на кнопку ">"
    public void NextClick(View view)
    {
        if (songIndex + 1 < songList.size())
        {
            songIndex++;
            Play();
        }
    }

    // Нажатие на кнопку "<"
    public void PreviousClick(View view)
    {
        if (songIndex - 1 >= 0)
        {
            songIndex--;
            Play();
        }
    }
}