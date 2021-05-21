 package com.example.internalbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

 public class MainActivity extends AppCompatActivity
 {
     final String LOG_TAG = "myLogs";
     final String DIR_SD = "MyFiles";
     final String FILENAME_SD = "fileSD";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button searchButton = findViewById(R.id.searchButton);

        //TextView tv = findViewById(R.id.mainTextView);

        WebView wv = (WebView) findViewById(R.id.mainWebView);
        wv.setWebViewClient(new internalClient());

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);

        wv.getSettings().setAllowContentAccess(true);
        wv.getSettings().setAllowFileAccess(true);

        /*
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
         */

        /*
        // Вывод пути к приложению к TextView
        String my_path = getExternalStorageDirectory().getAbsolutePath();
        tv.setText(my_path);
         */

        //wv.loadUrl("file:///sdcard/");
        //wv.loadUrl("file:///sdcard/Download/Мясник%20убивает%20игрока%20-%20Хы-ха-ха-ха-ха%20ты%20погляди%20свежее%20мясо_(mp3songsdirect.fun).mp3");
        wv.loadUrl("https://www.google.ru/maps/@55.7481984,37.6078336,12z");
        //wv.loadUrl("file:///sdcard/Download/googleMaps.html");
        //wv.loadUrl("file:///android_asset/googleMaps.html");
        //wv.loadUrl("https://yandex.ru");
    }


     @Override
     public void onBackPressed()
     {
         WebView wv = findViewById(R.id.mainWebView);
         if(wv.canGoBack())
             wv.goBack();
         else
             super.onBackPressed();
     }

     private class internalClient extends WebViewClient
     {
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
         {
             view.loadUrl(request.getUrl().toString());
             return true;
         }
     }
}