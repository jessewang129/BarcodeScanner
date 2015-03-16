package com.example.nwhack;

import org.json.*;
import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	public String scanContent = null;
	public String login_result = null;
	private Button scanBtn, loginBtn, submitBtn;
	private TextView formatTxt, contentTxt, productNameTxt, loginTxt, commentTxt; 
	private EditText emailTxt, passwordTxt, descTxt;

	private class comment extends AsyncTask<String, Void, String> {
		
		
		private String resultValue;

        protected String doInBackground(String ... params) {
            resultValue = null;
             
     		HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://sodiumcut.com:7080/nwhacks/public/index.php/api");
            HttpResponse response = null;
            
            List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (5);
            nameValuePairs.add(new BasicNameValuePair("product", "true"));
            nameValuePairs.add(new BasicNameValuePair("upc", params[0]));
            nameValuePairs.add(new BasicNameValuePair("name", params[1]));
            
            try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (Exception e){
            	e.printStackTrace();
            }            
            
            try{
            response = httpClient.execute(httpPost);
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            try{
            	resultValue =  EntityUtils.toString(response.getEntity());
            }catch(Exception e){
            	e.printStackTrace();
            }
     		
           return resultValue;
        }

        protected void onProgressUpdate(Void ... progress) {
            
        }

        protected void onPostExecute(String result) {
        	resultValue = result;
        }
        
        
      }
	private class desc extends AsyncTask<String, Void, String> {
		
		
		private String resultValue;

        protected String doInBackground(String ... params) {
            resultValue = null;
             
     		HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://sodiumcut.com:7080/nwhacks/public/index.php/api");
            HttpResponse response = null;
            
            List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (5);
            nameValuePairs.add(new BasicNameValuePair("comment", "true"));
            nameValuePairs.add(new BasicNameValuePair("upc", params[0]));
            nameValuePairs.add(new BasicNameValuePair("content", params[1]));
            
            try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (Exception e){
            	e.printStackTrace();
            }            
            
            try{
            response = httpClient.execute(httpPost);
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            try{
            	resultValue =  EntityUtils.toString(response.getEntity());
            }catch(Exception e){
            	e.printStackTrace();
            }
     		
           return resultValue;
        }

        protected void onProgressUpdate(Void ... progress) {
            
        }

        protected void onPostExecute(String result) {
        	resultValue = result;
        }
        
        
      }
	private class login extends AsyncTask<String, Void, String> {
		
		
		private String resultValue;

        protected String doInBackground(String ... params) {
            resultValue = null;
             
     		HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://sodiumcut.com:7080/nwhacks/public/index.php/api");
            HttpResponse response = null;
            
            List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (5);
            nameValuePairs.add(new BasicNameValuePair("login", "true"));
            nameValuePairs.add(new BasicNameValuePair("email", params[0]));
            nameValuePairs.add(new BasicNameValuePair("password", params[1]));
            
            try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            }catch (Exception e){
            	e.printStackTrace();
            }            
            
            try{
            response = httpClient.execute(httpPost);
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            try{
            	resultValue =  EntityUtils.toString(response.getEntity());
            }catch(Exception e){
            	e.printStackTrace();
            }
     		
           return resultValue;
        }

        protected void onProgressUpdate(Void ... progress) {
            
        }

        protected void onPostExecute(String result) {
        	resultValue = result;
        }
                
      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button)findViewById(R.id.scan_button);
        loginBtn = (Button)findViewById(R.id.login_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        loginTxt = (TextView)findViewById(R.id.login_result);
        commentTxt = (TextView)findViewById(R.id.comment);
        productNameTxt = (TextView)findViewById(R.id.product_name);
        //userTxt = (TextView)findViewById(R.id.user);
        emailTxt = (EditText)findViewById(R.id.email_address);
        passwordTxt = (EditText)findViewById(R.id.password);
        descTxt = (EditText)findViewById(R.id.description);
		submitBtn = (Button)findViewById(R.id.submit_button);
		submitBtn.setOnClickListener(this);
        scanBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);  
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View v) {
		
		if(v.getId()==R.id.scan_button){
			//scan
			
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
			
		}
		if(v.getId()==R.id.login_button){
			
			login A = new login();
	        try{
	        	login_result = A.execute(emailTxt.getText().toString(), passwordTxt.getText().toString()).get();
	        	if(login_result.equals("loginSuccess")){
	        		submitBtn.setVisibility(View.VISIBLE);
	        		descTxt.setVisibility(View.VISIBLE);
	        	}
	        }catch (Exception e){
	       	 System.out.println(e.getMessage());
	        }
	        
		}
		if(v.getId()==R.id.submit_button){
			desc B = new desc();
			
			try {
				B.execute(scanContent, descTxt.getText().toString()).get();	        	
										
			}catch (ExecutionException e) {
				
				e.printStackTrace();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
		}
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			//we have a result
			scanContent = scanningResult.getContents();
			scanContent = scanContent.substring(1,scanContent.length()-1);
			String scanFormat = scanningResult.getFormatName();
			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);
			networkGo A = new networkGo();
	        try{
	        	String result = A.execute().get();
	        	
	        	productNameTxt.setText("Product Name: " + result);
	        	comment C = new comment();
				String result2 ="";
				try {
					result2 = C.execute(scanContent, productNameTxt.getText().toString()).get();	        	
										
				}catch (ExecutionException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
	        	try{
	         		String inputLine = result2;
	        		JSONObject reader = new JSONObject(inputLine);
	         		inputLine = reader.getString("comments");
	         		JSONArray reader2 = new JSONArray(inputLine);
	         		Object input2 = reader2.get(0);
	         		commentTxt.setText("Decription: " + input2);
	         		System.out.println(input2.toString());
	         		}
	         		catch(Exception e){
	         			System.out.println(e.getMessage());
	         			
	         		}
	        }catch (Exception e){
	       	 System.out.println(e.getMessage());
	        }
		}
	}
	private class networkGo extends AsyncTask<Void, Void, String> {
		
		
		private String resultValue;

        protected String doInBackground(Void ... params) {
            resultValue = null;
            URL upcApi = null;
            
            String myURL =  "http://api.upcdatabase.org/json/6eed8e46a8adbf7b2286b514fc08e1ac/"+scanContent;
            
            try {
     			upcApi = new URL(myURL);
     			//jesse = new URL("http://api.upcdatabase.org/json/6eed8e46a8adbf7b2286b514fc08e1ac/6672101632");
     		}catch(Exception e){
     			System.out.println("some exception!");
     		}
     		
             BufferedReader in = null;
     		try {
     			in = new BufferedReader(
     			new InputStreamReader(upcApi.openStream()));
     		} catch (Exception e) {
     			
     		}

     		String inputLine = "";
     		
     		try {
     			inputLine = in.readLine();
     			} catch (IOException e) {
     			System.out.println("!");
     			
     		}
      
     		try {
       			in.close();
       		} catch (Exception e) {
       			System.out.println("LAST!");
       			
       		}
             
     		try{
     		JSONObject reader = new JSONObject(inputLine);
     		inputLine = reader.getString("itemname");
     		}
     		catch(Exception e){
     			System.out.println(e.getMessage());
     			
     		}        
            /*
     		HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://sodiumcut.com:7080/nwhacks/public/index.php/api");
            HttpResponse response = null;
            
            List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (5);
            nameValuePairs.add(new BasicNameValuePair("login", "true"));
            nameValuePairs.add(new BasicNameValuePair("name", "testUser2"));
            nameValuePairs.add(new BasicNameValuePair("email", "nays85@gmail.com"));
            nameValuePairs.add(new BasicNameValuePair("password", "aa12345"));
            
            try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (Exception e){
            	e.printStackTrace();
            }            
            
            try{
            response = httpClient.execute(httpPost);
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            try{
            	resultValue =  EntityUtils.toString(response.getEntity());
            }catch(Exception e){
            	e.printStackTrace();
            }*/
     		resultValue = inputLine;
           return resultValue;
        }

        protected void onProgressUpdate(Void ... progress) {
            
        }

        protected void onPostExecute(String result) {
        	resultValue = result;
        }
        
        
      }
	
}
