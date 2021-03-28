package ru.novitskiy.lab_3_rss_parser_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("StringFormatMatches")
    public void OnClick(View view)
    {
        XmlPullParser parser = getResources().getXml(R.xml.simple_rss);

        ArrayList<String> stringArrayList = new ArrayList<>();
        try
        {
            /*XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            File file = new File(Environment.getExternalStorageState() + "/simple_xml.xml");
            if (!file.exists())
            {
                Toast.makeText(this, "Этого файла по указанному пути не существует!", Toast.LENGTH_LONG);
                return;
            }

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fis);

            parser.setInput(reader);*/

            parser.next();
            String titleStr = "";
            String linkStr = "";
            String descrStr = "";
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT)
            {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("item"))
                {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("title"))
                    {
                        titleStr = parser.nextText();
                        parser.next();
                    }
                    if(parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("link"))
                    {
                        linkStr = parser.nextText();
                        parser.next();
                        parser.nextText();
                        parser.next();
                    }
                    if(parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("description"))
                    {
                        descrStr = parser.nextText();
                        parser.next();
                    }

                    //stringArrayList.add(String.format(getResources().getString(R.string.rss_template), titleStr, linkStr, descrStr));
                    stringArrayList.add(String.format(getResources().getString(R.string.rss_template2), titleStr, linkStr, descrStr));
                    //stringArrayList.add(String.format(getResources().getString(R.string.rss_template2), titleStr, linkStr, descrStr));

                    //Button button = (Button) findViewById(R.id.button2);
                    //button.setText(String.format(getResources().getString(R.string.get_news2), "крутые"));
                }
                parser.next();
            }
        }
        catch(XmlPullParserException e)
        {
            Toast.makeText(this, "Возникла ошибка типа XmlPullParserException", Toast.LENGTH_SHORT);
        }
        catch(IOException e)
        {

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, stringArrayList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}