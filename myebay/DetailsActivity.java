package com.example.preethi.myebay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.net.Uri;
import com.facebook.*;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;

import com.facebook.share.widget.ShareDialog;
import com.facebook.CallbackManager;
import org.json.JSONException;
import org.json.JSONObject;





public class DetailsActivity extends ActionBarActivity {
    public String resp;
    public String item;
    ShareDialog shareDialog;
    CallbackManager callbackManager;



    public TextView r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r12,r14;
    public WebView r11,r13,r15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //callbackManager = CallbackManager.Factory.create();


        callbackManager = CallbackManager.Factory.create();

         shareDialog = new ShareDialog(this);

        //Login Callback registration
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


            }
            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException exception) {

            }
            });



        Intent intent = getIntent();
        resp = intent.getExtras().getString("response");
        item = Integer.toString(intent.getExtras().getInt("item"));


        String shipping="";
        String convertedcurrentprice="";
        String shippingservicecost="";
        String textfree="";
        r1 = (TextView) findViewById(R.id.textView11);
        r2 = (TextView) findViewById(R.id.textView12);
        r3 = (TextView) findViewById(R.id.textView13);
        r4 = (TextView) findViewById(R.id.textView14);
        r5 = (TextView) findViewById(R.id.textView15);
        r6 = (TextView) findViewById(R.id.textView16);
        r7 = (TextView) findViewById(R.id.textView17);
        r8 = (TextView) findViewById(R.id.textView18);
        r9 = (TextView) findViewById(R.id.textView19);
        r10 = (TextView) findViewById(R.id.textView20);
        r11 = (WebView) findViewById(R.id.textView21);
        r12 = (TextView) findViewById(R.id.textView22);
        r13 = (WebView) findViewById(R.id.textView23);
        r14 = (TextView) findViewById(R.id.textView24);
        r15 = (WebView) findViewById(R.id.textView25);



        try {
            String items = "item"+item;
            JSONObject jObj = new JSONObject(resp);
            JSONObject info = jObj.getJSONObject(items);
            JSONObject basicInfo = info.getJSONObject("basicInfo");
            String title = basicInfo.getString("title");
            String gallery = basicInfo.getString("galleryURL");
            String picSupersize = basicInfo.getString("pictureURLSuperSize");
            convertedcurrentprice = basicInfo.getString("convertedCurrentPrice");
            shippingservicecost = basicInfo.getString("shippingServiceCost");
            String location = basicInfo.getString("location");
            String toprated = basicInfo.getString("topRatedListing");

            WebView webfb = (WebView) findViewById(R.id.webView7);
            webfb.setOnTouchListener(new customTouchListener(this));

            if(shippingservicecost.isEmpty()|| (Float.valueOf(shippingservicecost) == 0.0))
            {
                   shipping = "(FREE Shipping)";
            }
            else
            {
                shipping = "( +$" + shippingservicecost + " Shipping)";
            }

            textfree ="Price:$" +convertedcurrentprice + shipping;
            WebView web = (WebView) findViewById(R.id.webView);
            web.getSettings().setLoadWithOverviewMode(true);
            web.getSettings().setUseWideViewPort(true);
            web.layout(200,50,800,600);
            if(!picSupersize.isEmpty()) {

                web.loadUrl(picSupersize);
            }
            else
            {

                web.loadUrl(gallery);

            }


            r1.setText(title);
            r2.setText(textfree);
            r3.setText("Location:"+location);
            if(toprated.equals("true")) {
                WebView web1 = (WebView) findViewById(R.id.webView6);
                web1.loadUrl("http://cs-server.usc.edu:60135/itemTopRated.jpg");
            }
            WebView web2 = (WebView) findViewById(R.id.webView7);
            web2.loadUrl("http://cs-server.usc.edu:60135/fb.png");

        }
        catch(JSONException e)
        {
            e.printStackTrace();

        }
    }

    public void basicInfoOnClick(View v)
    {

        try {
            String items = "item"+item;
            JSONObject basicObj = new JSONObject(resp);

            JSONObject item1 = basicObj.getJSONObject(items);
            JSONObject basicInfo1 = item1.getJSONObject("basicInfo");
            r4.setText("");
            r5.setText("");
            r6.setText("");
            r7.setText("");
            r8.setText("");
            r9.setText("");
            r10.setText("");
            r11.loadUrl("about:blank");
            r12.setText("");
            r13.loadUrl("about:blank");
            r14.setText("");
            r15.loadUrl("about:blank");



            r4.setText("Category Name");
            String category = basicInfo1.getString("categoryName");
            r5.setText(category);
            r6.setText("Condition");
            String condition = basicInfo1.getString("conditionDisplayName");
            if(!condition.isEmpty()) {
                r7.setText(condition);
            }
            else
            {
                r7.setText("N/A");
            }
            r8.setText("Buying Format");
            String listing = basicInfo1.getString("listingType");
            String listingType;
            if(!listing.isEmpty())
            {
                if(listing == "FixedPrice" || listing =="StoreInventory")
                {
                    listingType="Buy It Now";
                }
                else if(listing =="Auction")
                {
                    listingType="Auction";
                }
                else if(listing == "Classified")
                {
                    listingType = "Classified Ad";
                }
                else
                {
                    listingType = listing;
                }

            }
            else
            {
                listingType ="N/A";
            }

            r9.setText(listingType);


        }
        catch (JSONException e)
        {
            e.printStackTrace();

        }

    }


    public void sellerInfoOnClick(View v)
    {

        try {
            String items = "item"+item;
            JSONObject sellerObj = new JSONObject(resp);
            JSONObject item2 = sellerObj.getJSONObject(items);
            JSONObject sellerInfo = item2.getJSONObject("sellerInfo");
            r4.setText("");
            r5.setText("");
            r6.setText("");
            r7.setText("");
            r8.setText("");
            r9.setText("");
            r10.setText("");
            r11.loadUrl("about:blank");
            r12.setText("");
            r13.loadUrl("about:blank");
            r14.setText("");
            r15.loadUrl("about:blank");


            r4.setText("User Name");
            String user = sellerInfo.getString("sellerUserName");
            r5.setText(user);

            r6.setText("Feedback Score");
            String feedback = sellerInfo.getString("feedbackScore");
            r7.setText(feedback);

            r8.setText("Positive Feedback");
            String posfeed = sellerInfo.getString("positiveFeedbackPercent");
            r9.setText(posfeed);

            r10.setText("Feedback Rating");
            String rating = sellerInfo.getString("feedbackRatingStar");
            //r11.setText(rating);

            String text0="<html><head>"+ "<style type=\"text/css\">body{}"+ "</style></head>"+ "<body>"+ rating+ "</body></html>";
            r11.loadData(text0,"text/html",null);

            r12.setText("TopRated");
            String top = sellerInfo.getString("topRatedSeller");
            if(top.equals("true"))
            {
                r13.loadUrl("file:///android_asset/yes.gif");

                //r13.setText("Yes");
            }
            else
            {
               r13.loadUrl("file:///android_asset/no.jpeg");

                //r13.setText("No");
            }

            r14.setText("Seller Store");
            String store = sellerInfo.getString("sellerStoreName");
            if(!store.isEmpty())
            {
                String text1="<html><head>"+ "<style type=\"text/css\">body{}"+ "</style></head>"+ "<body>"+ store+ "</body></html>";
                r15.loadData(text1,"text/html",null);

                //r15.setText(store);
            }
            else
            {
                String text1="<html><head>"+ "<style type=\"text/css\">body{color: #000000;}"+ "</style></head>"+ "<body>"+ "N/A"+ "</body></html>";
                r15.loadData(text1,"text/html",null);
                //r15.setText("N/A");
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();

        }


    }

    public void shipOnClick(View v)
    {

        try {
            String items = "item"+item;
            JSONObject shipObj = new JSONObject(resp);
            JSONObject item3 = shipObj.getJSONObject(items);
            JSONObject shipInfo = item3.getJSONObject("shippingInfo");

            r4.setText("");
            r5.setText("");
            r6.setText("");
            r7.setText("");
            r8.setText("");
            r9.setText("");
            r10.setText("");
           // r11.setText("");
            r11.loadUrl("about:blank");
            r12.setText("");
            r13.loadUrl("about:blank");
            //r13.setText("");
            r14.setText("");
            r15.loadUrl("about:blank");
            //r15.setText("");

            r4.setText("Shipping Type");
            String type = shipInfo.getString("shippingType");
            if(!type.isEmpty())
            {
                r5.setText(type);
            }
            else
            {
                r5.setText("N/A");
            }

            r6.setText("Handling Time");
            String time = shipInfo.getString("handlingTime");
            if(!time.isEmpty())
            {
                r7.setText(time);
            }
            else
            {
                r7.setText("N/A");
            }


            r8.setText("Shipping Locations");
            String loc = shipInfo.getString("shipToLocations");
            if(!loc.isEmpty())
            {
                r9.setText(loc);
            }
            else
            {
                r9.setText("N/A");
            }

            r10.setText("Expedited Shipping");
            String exp = shipInfo.getString("expeditedShipping");
            if(exp.equals("true"))
            {
                //r11.setText("Yes");
                r11.loadUrl("file:///android_asset/yes.gif");
            }
            else
            {
                r11.loadUrl("file:///android_asset/no.jpeg");
                //r11.setText("No");
            }

            r12.setText("One day shipping");
            String one = shipInfo.getString("oneDayShippingAvailable");
            if(one.equals("true"))
            {
                r13.loadUrl("file:///android_asset/yes.gif");
                //r13.setText("Yes");
            }
            else
            {
                r13.loadUrl("file:///android_asset/no.jpeg");
                //r13.setText("No");
            }


            r14.setText("Returns Accepted");
            String ret = shipInfo.getString("returnsAccepted");
            if(ret.equals("true"))
            {
                r15.loadUrl("file:///android_asset/yes.gif");
                //r15.setText("Yes");
            }
            else
            {
                r15.loadUrl("file:///android_asset/no.jpeg");
                //r15.setText("No");
            }




        }
        catch (JSONException e)
        {
            e.printStackTrace();

        }


    }


    class customTouchListener implements View.OnTouchListener
    {
       Activity act;
        String response;
        String itemurl;
        public customTouchListener(Activity act)
        {
            this.act=act;

        }
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {

            try
            {

            String items = "item" + item;
            JSONObject Obj = new JSONObject(resp);
            JSONObject fb = Obj.getJSONObject(items);
            JSONObject fbInfo = fb.getJSONObject("basicInfo");
            String itemurl = fbInfo.getString("viewItemURL");
            String title = fbInfo.getString("title");
            String galleryURL = fbInfo.getString("galleryURL");
            String convertedcurrentprice = fbInfo.getString("convertedCurrentPrice");
            String shippingservicecost = fbInfo.getString("shippingServiceCost");
            String location = fbInfo.getString("location");
            String shipping = "";
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        String postId = result.getPostId();
                        if(postId == null){
                            Toast.makeText(getApplicationContext(),"Post Cancelled", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Posted Story ID:" + postId, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(),"Post Cancelled", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(),"Error occurred during post", Toast.LENGTH_LONG).show();
                    }
                });



            if (!shippingservicecost.isEmpty()) {
                if (Float.valueOf(shippingservicecost) == 0.0) {
                    shipping = "(Free Shipping)";
                } else {
                    shipping = "( +" + shippingservicecost + " Shipping)";
                }
            }
            String total = "Price:$" + convertedcurrentprice + shipping + ",Location:" + location;
            ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse(itemurl)).setContentTitle(title).setImageUrl(Uri.parse(galleryURL)).setContentDescription(total).build();
            //ShareDialog.show(act, content);
                ShareDialog.show(act, content);


                /*shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        String postId = result.getPostId();
                        if(postId == null){
                            Toast.makeText(getApplicationContext(),"Post Cancelled", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Posted Story ID:" + postId, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(),"Post Cancelled", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(),"Error occurred during post", Toast.LENGTH_LONG).show();
                    }
                });*/


        }
            catch(JSONException e)
            {
                e.printStackTrace();

            }
            return false;

        }
    }

    public void buynow(View v) throws JSONException
    {
        String itemnumber="item"+item;
        JSONObject myObject = new JSONObject(resp);
        JSONObject myObject1 = myObject.getJSONObject(itemnumber);
        String itemurl = myObject1.getJSONObject("basicInfo").getString("viewItemURL");
        String title = myObject1.getJSONObject("basicInfo").getString("title");
        String galleryURL = myObject1.getJSONObject("basicInfo").getString("galleryURL");
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(itemurl));
        startActivity(i);


    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
