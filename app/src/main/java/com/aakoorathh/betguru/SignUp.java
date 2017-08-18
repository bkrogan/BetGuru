package com.aakoorathh.betguru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity
{
    private TextView txt_Back;
    
    
    public static final String REGISTER_URL = "http://35.154.116.102/betguru/index.php/api/signup?firstname=samadhan&lastname=nirali&mobile=9561949387&email=s.matksos@gmail.com&password=123&deviceid=123&username=sammie&signupfrom=1";
    
    public static final String KEY_USERNAME = "username";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    
    private EditText edt_SignUp_Username, edt_SignUp_FirstName, edt_Signup_LastName, edt_SignUp_Email, edt_SignUp_Password;
    
    private TextView tv_SignUp_SignUp;
    
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
          /* Show Action bar */
        getSupportActionBar().hide();
        
        /* All editext of Sign Up Form */
        
        edt_SignUp_Email = (EditText) findViewById(R.id.edt_SignUp_Email);
        edt_SignUp_Username = (EditText) findViewById(R.id.edt_SignUp_Username);
        edt_SignUp_FirstName = (EditText) findViewById(R.id.edt_SignUp_FirstName);
        edt_Signup_LastName = (EditText) findViewById(R.id.edt_Signup_LastName);
        edt_SignUp_Password = (EditText) findViewById(R.id.edt_SignUp_Password);
        
        /* SignUp Button */
        
        tv_SignUp_SignUp = (TextView) findViewById(R.id.tv_SignUp_SignUp);
        
        tv_SignUp_SignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                username = edt_SignUp_Username.getText().toString().trim();
                first_name = edt_SignUp_FirstName.getText().toString().trim();
                last_name = edt_Signup_LastName.getText().toString().trim();
                email = edt_SignUp_Email.getText().toString().trim();
                password = edt_SignUp_Password.getText().toString().trim();
                
                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                Toast.makeText(SignUp.this, response, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        
                        params.put(KEY_USERNAME, username);
                        params.put(KEY_FIRST_NAME, first_name);
                        params.put(KEY_LAST_NAME, last_name);
                        params.put(KEY_EMAIL, email);
                        params.put(KEY_PASSWORD, password);
                        
                        return params;
                    }
                    
                };
                
                RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
                requestQueue.add(stringRequest);
            }
            
            
        });
        
        /* Action on Back text field click event  */
        
        txt_Back = (TextView)
                
                findViewById(R.id.txt_Back);
        
        txt_Back.setOnClickListener(new View.OnClickListener()
        
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SignUp.this, SplashScreen.class));
            }
        });
        
    }
    
}
