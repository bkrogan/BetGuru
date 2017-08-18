package com.aakoorathh.betguru;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity
{
    
    private static final String LOGIN_URL = "http://35.154.116.102/betguru/index.php/api/login?email=sammie&password=123&deviceid=3456&loginfrom=1";
    
    // public static final String KEY_USERNAME = "username";
    
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";
    
    private ImageView imgvw_Facebook, imgvw_Gplus;
    
    private EditText edt_Login_Username, edt_Login_Password;
    
    private TextView tv_Login;
    
    String username;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
          /* Hide Action bar */
        getSupportActionBar().hide();
        
        // Username or Email and Password
        
        edt_Login_Username = (EditText) findViewById(R.id.edt_Login_Username);
        
        edt_Login_Password = (EditText) findViewById(R.id.edt_Login_Password);
        
        // Login
        
        tv_Login = (TextView) findViewById(R.id.tv_Login);
        
          /* Facebook login */
        
        imgvw_Facebook = (ImageView) findViewById(R.id.imgvw_Facebook);
        
        imgvw_Facebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = null;
                try
                {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    String url = "https://www.facebook.com/";
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + url));
                }
                catch (Exception e)
                {
                    // no Facebook app, revert to browser
                    String url = "https://facebook.com/";
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                }
                startActivity(intent);
            }
        });
        
        /* Google Plus login */
        
        imgvw_Gplus = (ImageView) findViewById(R.id.imgvw_Gplus);
        
        imgvw_Gplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                
                String profile = null;
                Intent intent = null;
                try
                {
                    getPackageManager().getPackageInfo("com.google.android.apps.plus", 0);
                    String url = "https://plus.google.com/";
                    intent.setClassName("com.google.android.apps.plus",
                            "com.google.android.apps.plus.phone.UrlGatewayActivity");
                    intent.putExtra("customAppUri", profile);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + profile + "/posts"));
                    
                }
                catch (Exception e)
                {
                    // no Facebook app, revert to browser
                    String url = "https://plus.google.com/";
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                }
                startActivity(intent);
            }
        });
        
        tv_Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                username = edt_Login_Username.getText().toString().trim();
                email = edt_Login_Username.getText().toString().trim();
                password = edt_Login_Password.getText().toString().trim();
                                
                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                if (response.trim().equals("success"))
                                {
                                    openProfile();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(KEY_USERNAME, username);
                        map.put(KEY_EMAIL, email);
                        map.put(KEY_PASSWORD, password);
                        return map;
                    }
                };
                
                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);
            }
            
            private void openProfile()
            {
                Intent intent = new Intent(Login.this, Home.class);
                intent.putExtra(KEY_USERNAME, username);
                startActivity(intent);
            }
        });
    }
}
