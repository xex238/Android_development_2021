package ru.novitskiy.media_player;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Intent intent = null;
    private ArrayList<SongInfo> songList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mediaPlayer = MediaPlayer.create(this, R.raw.track1);
        /*File[] mediaDirs = getExternalMediaDirs();
        for (File dir : mediaDirs){
            for (File file : dir.listFiles()){
                if (file.isFile())
                    songList.add(file.getAbsolutePath());
            }
        }*/

        // Проверка на наличие прав доступа к файлам
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.ACCESS_MEDIA_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_MEDIA_LOCATION}, 123);
        }
        else
            fillSongList();

        ListView listView = (ListView)findViewById(R.id.music_ListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                StartIntent(view, position);
            }
        });
    }

    public void StartIntent(View view, int position)
    {
        if(intent == null)
        {
            intent = new Intent(this, MusicPlayerActivity.class);
            //intent.putExtra("songList", songList);
            intent.putExtra(SongInfo.class.getSimpleName(), songList);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123)
        {
            fillSongList();
        }
    }

    // Поиск композиций для воспроизведения
    private void fillSongList()
    {
        ContentResolver contentResolver = getContentResolver();
        String[] projection = new String[]{MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DATA};
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, sortOrder);
        if (cursor != null)
        {
            try
            {
                int colIdIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                int colTitleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int colArtistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int colPathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                ArrayList<String> stringArrayList = new ArrayList<>();
                if (cursor.getCount() != 0)
                {
                    int counter = 0;
                    cursor.moveToFirst();
                    do
                    {
                        counter++;
                        int id = cursor.getInt(colIdIndex);
                        String title = cursor.getString(colTitleIndex);
                        String artist = cursor.getString(colArtistIndex);
                        String path = cursor.getString(colPathIndex);
                        songList.add(new SongInfo(counter, title, artist, path));
                        stringArrayList.add(String.format(getResources().getString(R.string.item_MusicList), String.valueOf(counter), title, artist));
                    } while (cursor.moveToNext());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, stringArrayList);
                ListView listView = (ListView)findViewById(R.id.music_ListView);
                listView.setAdapter(adapter);
            }
            finally
            {
                cursor.close();
            }
        }
    }
}