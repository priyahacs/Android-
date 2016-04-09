package com.example.preethi.myebay;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.view.MenuItem;
import android.view.MotionEvent;
import java.lang.Object;
import org.json.JSONException;
import org.json.JSONObject;
import android.webkit.WebView;
import android.widget.TextView;
import android.app.Activity;
import android.net.Uri;


public class first_activity extends ActionBarActivity {

    //private TextView r1,r2,r3,r4,r5;
    //public String s;
    //int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);

        String s="";
        String key="";
        int i;
        TextView r1,r2,r3,r4,r5,r6;
            Intent intent = getIntent();
             s = intent.getExtras().getString("response");
             key = intent.getExtras().getString("keywords");
             r6 = (TextView) findViewById(R.id.result);
              r6.setText("Results for "+key);

        try {
            JSONObject jObject1 = new JSONObject(s);

/*item0 beg*/
                JSONObject item0 = jObject1.getJSONObject("item0");
                JSONObject basicInfo0 = item0.getJSONObject("basicInfo");
                String title0 = basicInfo0.getString("title");
                String gallery0 = basicInfo0.getString("galleryURL");
                String itemurl = basicInfo0.getString("viewItemURL");
                WebView web = (WebView) findViewById(R.id.webView1);
                web.loadUrl("about:blank");
                web.loadUrl(gallery0);
                web.setOnTouchListener(new customTouchListener(this,s,itemurl));
                r1 = (TextView) findViewById(R.id.textView6);
                r1.setText("");
                //r1.setText(title0);
                i = 0;
                r1.setOnClickListener(new CustomClickListener(i,this,s));
            String convertedcurrentprice0 = basicInfo0.getString("convertedCurrentPrice");
            String shippingservicecost0 = basicInfo0.getString("shippingServiceCost");
            String shipping0="";
            if(shippingservicecost0.isEmpty()|| (Float.valueOf(shippingservicecost0) == 0.0))

            {
                shipping0 = "(FREE Shipping)";
            }
            else
            {
                shipping0 = "( +$" + shippingservicecost0 + " Shipping)";
            }

            String textfree0 ="Price:$" +convertedcurrentprice0 + shipping0;
            //title0.append(System.getProperty("line.separator"));
            r1.setText(title0+"\n\n\n"+textfree0);

            /*item0 end*/

            /*item1 beg*/
            JSONObject item1 = jObject1.getJSONObject("item1");

            JSONObject basicInfo1 = item1.getJSONObject("basicInfo");
            String title1 = basicInfo1.getString("title");
            String gallery1 = basicInfo1.getString("galleryURL");
            String itemurl1 = basicInfo0.getString("viewItemURL");
            WebView web1 = (WebView) findViewById(R.id.webView2);
            web1.loadUrl(gallery1);
            web1.setOnTouchListener(new customTouchListener(this,s,itemurl1));
            r2 = (TextView) findViewById(R.id.textView7);
            //r2.setText(title1);
            i = 1;
            r2.setOnClickListener(new CustomClickListener(i,this,s));
            String convertedcurrentprice1 = basicInfo1.getString("convertedCurrentPrice");
            String shippingservicecost1 = basicInfo1.getString("shippingServiceCost");
            String shipping1="";
            if(shippingservicecost1.isEmpty()|| (Float.valueOf(shippingservicecost1) == 0.0))

            {
                shipping1 = "(FREE Shipping)";
            }
            else
            {
                shipping1 = "( +$" + shippingservicecost1 + " Shipping)";
            }

            String textfree1 ="Price:$" +convertedcurrentprice1 + shipping1;
            r2.setText(title1+"\n\n\n"+textfree1);

            /*item1 end*/

            /*item2 beg*/
            JSONObject item2 = jObject1.getJSONObject("item2");
            JSONObject basicInfo2 = item2.getJSONObject("basicInfo");
            String title2 = basicInfo2.getString("title");
            String gallery2 = basicInfo2.getString("galleryURL");
            String itemurl2 = basicInfo2.getString("viewItemURL");
            WebView web2 = (WebView) findViewById(R.id.webView3);
            web2.loadUrl(gallery2);
            web2.setOnTouchListener(new customTouchListener(this,s,itemurl2));
            r3 = (TextView) findViewById(R.id.textView8);
            //r3.setText(title2);
            i = 2;
            r3.setOnClickListener(new CustomClickListener(i,this,s));
            String convertedcurrentprice2 = basicInfo2.getString("convertedCurrentPrice");
            String shippingservicecost2 = basicInfo2.getString("shippingServiceCost");
            String shipping2="";
            if(shippingservicecost2.isEmpty()|| (Float.valueOf(shippingservicecost2) == 0.0))

            {
                shipping2 = "(FREE Shipping)";
            }
            else
            {
                shipping2 = "( +$" + shippingservicecost2 + " Shipping)";
            }

            String textfree2 ="Price:$" +convertedcurrentprice2 + shipping2;
            r3.setText(title2+"\n\n\n"+textfree2);
            /*item2 end*/

            /*item3 beg*/
            JSONObject item3 = jObject1.getJSONObject("item3");
            JSONObject basicInfo3 = item3.getJSONObject("basicInfo");
            String title3 = basicInfo3.getString("title");
            String gallery3 = basicInfo3.getString("galleryURL");
            String itemurl3 = basicInfo3.getString("viewItemURL");
            WebView web3 = (WebView) findViewById(R.id.webView4);
            web3.loadUrl(gallery3);
            web3.setOnTouchListener(new customTouchListener(this,s,itemurl3));
            r4 = (TextView) findViewById(R.id.textView9);
            //r4.setText(title3);
            i = 3;
            r4.setOnClickListener(new CustomClickListener(i,this,s));
            String convertedcurrentprice3 = basicInfo3.getString("convertedCurrentPrice");
            String shippingservicecost3 = basicInfo3.getString("shippingServiceCost");
            String shipping3="";
            if(shippingservicecost3.isEmpty()|| (Float.valueOf(shippingservicecost3) == 0.0))

            {
                shipping3 = "(FREE Shipping)";
            }
            else
            {
                shipping3 = "( +$" + shippingservicecost3 + " Shipping)";
            }

            String textfree3 ="Price:$" +convertedcurrentprice3 + shipping3;
            r4.setText(title3+"\n\n\n"+textfree3);
            /*item3 end*/

            /*item4 beg*/
            JSONObject item4 = jObject1.getJSONObject("item4");
            JSONObject basicInfo4 = item4.getJSONObject("basicInfo");
            String title4 = basicInfo4.getString("title");
            String gallery4 = basicInfo4.getString("galleryURL");
            String itemurl4 = basicInfo4.getString("viewItemURL");
            WebView web4 = (WebView) findViewById(R.id.webView5);
            //web4.getSettings().setLoadWithOverviewMode(true);
            //web4.getSettings().setUseWideViewPort(true);

            web4.loadUrl(gallery4);
            web4.setOnTouchListener(new customTouchListener(this,s,itemurl4));
            r5 = (TextView) findViewById(R.id.textView10);
            //r5.setText(title4);
            i = 4;
            r5.setOnClickListener(new CustomClickListener(i,this,s));
            String convertedcurrentprice4 = basicInfo4.getString("convertedCurrentPrice");
            String shippingservicecost4 = basicInfo4.getString("shippingServiceCost");
            String shipping4="";
            if(shippingservicecost4.isEmpty()|| (Float.valueOf(shippingservicecost4) == 0.0))

            {
                shipping4 = "(FREE Shipping)";
            }
            else
            {
                shipping4 = "( +$" + shippingservicecost4 + " Shipping)";
            }

            String textfree4 ="Price:$" +convertedcurrentprice4 + shipping4;
            r5.setText(title4+"\n\n\n"+textfree4);
            /*item4 end*/

            }

        catch(JSONException e)
        {
            e.printStackTrace();

        }

    }



  class CustomClickListener implements View.OnClickListener
  {

      int item;
      Activity act;
      String response;
      public CustomClickListener(int item,Activity act,String response) {
          this.item = item;
          this.act = act;
          this.response=response;
      }

      @Override
      public void onClick(View v)
      {
          Intent intent = new Intent(act, DetailsActivity.class);
          intent.putExtra("response", response);
          intent.putExtra("item", item);
          act.startActivity(intent);
      }

  }
    class customTouchListener implements View.OnTouchListener
    {
        Activity act;
        String response;
        String itemurl;
        public customTouchListener(Activity act,String response,String itemurl)
        {
            this.act=act;
            this.response=response;
            this.itemurl=itemurl;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(itemurl));
            act.startActivity(intent);
            return false;
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_activity, menu);
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
