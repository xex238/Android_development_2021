package ru.novitskiy.exam_task;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

// Импорт файлов для попытки №2
import de.aflx.sardine.DavResource;
import de.aflx.sardine.Sardine;
import de.aflx.sardine.SardineFactory;
//import ru.novitskiy.exam_task.de.aflx.sardine.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
{
    //private static final String PATH = "https://192.168.0.103/webdav/test/";
    //private static final String PATH = "https://www.google.ru/maps/@55.7481984,37.6078336,12z";
    private static final String PATH = "https://webdav.yandex.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Попытка №1
        //Browser_scan();

        // Попытка №2
        //Sardine_initialization();
    }

    // Попытка №1. Открытие яндекс диска с помощью браузера
    // Проблема: Ошибка - приложение "вылетает" при запуске
    /*
    private class internalClient extends WebViewClient
    {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
        {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            {
                getActionBar().setHomeButtonEnabled(false);
                view.loadUrl(request.getUrl().toString());
            }

            return true;
        }
    }

    protected void Browser_scan()
    {
        WebView wv = (WebView) findViewById(R.id.web_view);
        wv.setWebViewClient(new internalClient());

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);

        wv.getSettings().setAllowContentAccess(true);
        wv.getSettings().setAllowFileAccess(true);

        if (18 < Build.VERSION.SDK_INT )
        {
            //18 = JellyBean MR2, KITKAT=19
            wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        if (Build.VERSION.SDK_INT >= 19)
        {
            wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        //wv.loadUrl("https://www.google.ru/maps/@55.7481984,37.6078336,12z");
        wv.loadUrl(PATH);
    }
    */

    // Попытка №2. Использование пакета: https://github.com/yeonsh/Sardine-Android
    // + https://github.com/pethoalpar/SardineExample
    // Проблема: Ошибка в файле пакета de.aflx.sardine ExistsResponseHandler.java - import org.apache.http.HttpResponse невозможен
    /*
    protected void Sardine_initialization()
    {
        ArrayList<String> stringArrayList = new ArrayList<>();

        Sardine sardine = SardineFactory.begin("test","test");
        try
        {
            sardine.createDirectory(PATH+"/test/");
            sardine.put(PATH+"a.txt","a".getBytes());
            sardine.put(PATH+"b.txt","b".getBytes());

            List<DavResource> res = sardine.list(PATH);
            for(DavResource dr : res)
            {
                //System.out.println(dr);
                stringArrayList.add(dr.getPath());
            }
            //System.out.println();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, stringArrayList);
            ListView listView = (ListView)findViewById(R.id.list_view);
            listView.setAdapter(adapter);

            sardine.delete(PATH+"b.txt");
            res = sardine.list(PATH);
            for(DavResource dr : res)
            {
                System.out.println(dr);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    */
}