package com.example.hunter.motiva;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
    Button imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageButton=(Button)findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new MyTask().execute("other").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //Bitmap bitmap= null;
        try {
            new MyTask().execute("other").get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //    imageView.setImageBitmap(bitmap);
    }
    public static boolean isReachable(String addr, int openPort, int timeOutMillis) {
        // Any Open port on other machine
        // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public class MyTask extends AsyncTask<String, Void, String> {
        //  @Override
        //    protected void onPreExecute() {
        //    progressBar.setVisibility(View.VISIBLE);
        // }
        String img_url;
        HttpHandler sh=new HttpHandler();
        String JSON_STRING;

        String getimageurl = "http://192.168.0.7/Motiva/getRandImg.php";

        @Override
        protected String doInBackground(String... voids) {

            HttpURLConnection httpURLConnection=null;

                try {
                    if (!(isReachable("192.168.0.7",80,200)))
                    {
                        return "Connect to the Internet!";
                    }

                    URL url=new URL(getimageurl);
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    String post_data = URLEncoder.encode("event_id", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(1),"UTF-8");
                    sh.WritetoOutputStream(outputStream,post_data);
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    JSON_STRING=sh.convertStreamtoString(inputStream);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if(JSON_STRING!=null)
                {
                    try{
                        //JSONObject jsonObject=new JSONObject(JSON_STRING);
                        //Getting JSON Array node
                        JSONArray results=new JSONArray(JSON_STRING);
                        for(int i=0;i<results.length();i++)
                        {
                            JSONObject c=results.getJSONObject(i);
                            img_url=c.getString("img_url");
                        }
                    }catch(final JSONException e)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Error:"+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }else{
                    //          Log.e(TAG,"Couldnt get Json from Server");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            return "Cool";
            }

        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("Cool")) {
                Picasso.with(MainActivity.this)
                        .load(img_url)
                        .into(imageView);
            }
            else{
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }
        }
    }
}

