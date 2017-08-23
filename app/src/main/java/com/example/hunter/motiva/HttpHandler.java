package com.example.hunter.motiva;

import android.util.Log;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hunter on 2/5/17.
 */

public class HttpHandler {
private static final String TAG=HttpHandler.class.getSimpleName();
    public HttpHandler()
    {

    }
    public String makeServiceCall(String reqUrl)
    {
        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            //read the response
            InputStream in=new BufferedInputStream(conn.getInputStream());
            response=convertStreamtoString(in);

        }catch (MalformedURLException e)
        {
            Log.e(TAG,"MalformedURLException: "+e.getMessage());

        }catch (ProtocolException e)
        {
            Log.e(TAG,"ProtocolException:"+e.getMessage());

        }catch (IOException e)
        {
            Log.e(TAG,"IOException:"+e.getMessage());

        }catch(Exception e)
        {
            Log.e(TAG,"Exception:"+e.getMessage());
        }
        return response;

    }
    public String convertStreamtoString(InputStream is)
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line;
        try{
            while((line=reader.readLine())!=null)
            {
                sb.append(line).append('\n');
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }finally {
            try{
                is.close();

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();

    }
    public String convertStreamToStringWithoutNewline(InputStream inputStream)
    {
        String result= null;
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

            result = "";
            String line="";

            while((line=bufferedReader.readLine())!=null)
            {
                result+=line;

            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

    public void WritetoOutputStream(OutputStream outputStream, String post_data)
    {
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method converts an ArrayList of UserOne,its attribute user_id into JSON format
     * i.e it creates a JSONArray of user_id from the ArrayList of UserOne class datatype
     * @param users
     * users is an ArrayList which is passed which contains Objects of type UserOne
     * @return
     * it return String of JSONArray of user_id
     */



}
