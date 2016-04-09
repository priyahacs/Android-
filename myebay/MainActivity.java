package com.example.preethi.myebay;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Object;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONException;
import android.widget.TextView;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import 	android.content.Context;
import 	android.util.JsonReader;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.net.MalformedURLException;
import static java.net.URLEncoder.encode;



public class MainActivity extends ActionBarActivity {


    public void onClearClick(View v) throws MalformedURLException
    {
        EditText k1 = (EditText) findViewById(R.id.editText3);
        EditText k2 = (EditText) findViewById(R.id.editText);
        EditText k3 = (EditText) findViewById(R.id.editText2);
        Spinner s1=(Spinner)findViewById(R.id.spinner);
        TextView k4 = (TextView) findViewById(R.id.textView26);
        k1.setText("");
        k2.setText("");
        k3.setText("");
        k4.setText("");
        k1.setError(null);
        k2.setError(null);
        k3.setError(null);
        s1.setSelection(0);
        k1.setFocusableInTouchMode(true);
        k1.setFocusable(true);
        k1.requestFocus();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText k1 = (EditText) findViewById(R.id.editText3);
        k1.setFocusableInTouchMode(true);
        k1.setFocusable(true);
        k1.requestFocus();


    }
    @Override
    public void onResume(){
        super.onResume();

        // put your code here...

    }

    public void buttonOnClick(View v)
    {
        //Button button = (Button) v;
        //EditText k1;
        String keyword ="";
        float minprice =0;
        float maxprice=0;
        String sortby="";
        String url1="";
        int error=0;
        String minval="";
        String maxval="";
        String sortbyval="";

        DownloadWebPageTask task = new DownloadWebPageTask();

        error=0;
        Button b = (Button) v;
        EditText k1 = (EditText) findViewById(R.id.editText3);
        EditText k2 = (EditText) findViewById(R.id.editText);
        EditText k3 = (EditText) findViewById(R.id.editText2);
        Spinner s1=(Spinner)findViewById(R.id.spinner);
        if(k2.getText().toString() == null || k2.getText().toString().isEmpty() || k2.getText().toString().length() == 0 || k2.getText().toString().equals(""))
        {
            minval="no";
        }
        if(k3.getText().toString() == null || k3.getText().toString().isEmpty() || k3.getText().toString().length() == 0 || k3.getText().toString().equals(""))
        {
            maxval="no";
        }
        if(!minval.equals("no"))
            minprice = Float.valueOf(k2.getText().toString());
        if(!maxval.equals("no"))
            maxprice = Float.valueOf(k3.getText().toString());

        keyword = k1.getText().toString();

        sortby= (String) s1.getSelectedItem();
        if(sortby.equals("Best Match"))
        {
            sortbyval="BestMatch";
        }
        if(sortby.equals("Price:Highest First"))
        {
            sortbyval="CurrentPriceHighest";
        }
        if(sortby.equals("Price+Shipping:Highest First"))
        {
            sortbyval="PricePlusShippingHighest";
        }
        if(sortby.equals("Price+Shipping:Lowest First"))
        {
            sortbyval="PricePlusShippingLowest";
        }

        if (keyword.matches(""))
        {
            k1.setError("Please enter a keyword");
            error=1;
        }

        if(!minval.equals("no")&&!maxval.equals("no"))
        {
            if (minprice > maxprice)
            {
                k3.setError("Maximum price cannot be less than Minimum price");
                error=1;
            }
        }



        if(Integer.valueOf(error)==0)
        {

            url1 = "http://cs-server.usc.edu:60135/andriod.php";
            //task.execute(new String[] { "http://cs-server.usc.edu:60135/andriod.php?keyword=iphone" });
            /*if(minval.equals("no") && maxval.equals("no"))
            {
                //url1 += "?keyword=" + encode(keyword) + "&MinPrice=" + minval + "&MaxPrice=" + maxval + "&sort_by=" + sortbyval;
                 url1+="?keyword="+encode(keyword)+"&sort_by="+sortbyval;
            }
            else if(maxval.equals("no"))
            {
                //url1 += "?keyword=" + encode(keyword) + "&MinPrice=" + minprice + "&MaxPrice=" + maxval + "&sort_by=" + sortbyval;
                url1+="?keyword="+encode(keyword)+"&MinPrice="+minprice+"&sort_by="+sortbyval;
            }
            else if(minval.equals("no"))
            {
                //url1 += "?keyword=" + encode(keyword) + "&MinPrice=" + minval + "&MaxPrice=" + maxprice + "&sort_by=" + sortbyval;
                url1+="?keyword="+encode(keyword)+"&MaxPrice="+maxprice+"&sort_by="+sortbyval;
            }
            else
            {
                //url1 += "?keyword=" + encode(keyword) + "&MinPrice=" + minprice + "&MaxPrice=" + maxprice + "&sort_by=" + sortbyval;
                url1+="?keyword="+encode(keyword)+"&MinPrice="+minprice+"&MaxPrice="+maxprice+"&sort_by="+sortbyval;
            }*/
            String a = k2.getText().toString();
            String c = k3.getText().toString();
            //url1+="?keyword="+encode(keyword)+"&MinPrice="+minprice+"&sort_by="+sortbyval;
            url1+="?keyword="+encode(keyword)+"&MinPrice="+a+"&MaxPrice="+c+"&sort_by="+sortbyval;

            task.execute(new String[] {url1});
        }

    }


String response="";
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            for (String url : urls) {


                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                String keywords ="";
                EditText k1 = (EditText) findViewById(R.id.editText3);
                keywords = k1.getText().toString();

                JSONObject jObject = new JSONObject(response);
                float resultCount = Float.valueOf(jObject.getString("resultCount"));
                if(resultCount == 0)
                {
                    TextView t26 = (TextView) findViewById(R.id.textView26);
                    t26.setText("No Results Found");

                }
                else {

                    Intent intent = new Intent(MainActivity.this, first_activity.class);
                    intent.putExtra("response", response);
                    intent.putExtra("keywords", keywords);
                    startActivity(intent);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();

            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
