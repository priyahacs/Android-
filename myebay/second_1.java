package com.example.preethi.myebay;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class second_1 extends ActionBarActivity {

    public String resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_1);
        Intent intent = getIntent();
        resp = intent.getExtras().getString("response");

        try {
            JSONObject jObj = new JSONObject(resp);
            /*

            JSONObject item = jObject1.getJSONObject("item0");
            JSONObject basicInfo = item.getJSONObject("basicInfo");
            String title = basicInfo.getString("title");
            String gallery = basicInfo.getString("galleryURL");
            WebView web = (WebView) findViewById(R.id.webView1);
            web.loadUrl(gallery0);
            r1 = (TextView) findViewById(R.id.textView6);
            r1.setText(title0);*/
        }
        catch(JSONException e)
        {
            e.printStackTrace();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
