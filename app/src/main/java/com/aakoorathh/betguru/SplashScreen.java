package com.aakoorathh.betguru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity
{
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private CardView card_Login, card_SignUp;
    private TextView txt_Skip;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
         /* Hide Action bar */
        getSupportActionBar().hide();
        
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        
        viewPager.setAdapter(viewPagerAdapter);
    
         /* Action on Skip text field click event   */
        
        txt_Skip = (TextView) findViewById(R.id.txt_Skip);
        
        txt_Skip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SplashScreen.this, Home.class));
            }
        });
    
        /* Home Click */
        
        card_Login = (CardView) findViewById(R.id.card_Login);
        
        card_Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                
                startActivity(new Intent(SplashScreen.this, Login.class));
            }
        });
    
        /* SignUp click */
        
        card_SignUp = (CardView) findViewById(R.id.card_SignUp);
        
        card_SignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SplashScreen.this, SignUp.class));
            }
        });
    
        /* code for image indicator in DOT pattern */
        
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        
        for (int i = 0; i < dotscount; i++)
        {
            
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            
            params.setMargins(8, 0, 8, 0);
            
            sliderDotspanel.addView(dots[i], params);
            
        }
        
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                
            }
            
            @Override
            public void onPageSelected(int position)
            {
                
                for (int i = 0; i < dotscount; i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                
            }
            
            @Override
            public void onPageScrollStateChanged(int state)
            {
                
            }
        });
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
        
        
    }
    
    /* Timer for image auto swipe  */
    
    public class MyTimerTask extends TimerTask
    {
        
        @Override
        public void run()
        {
            
            SplashScreen.this.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    
                    if (viewPager.getCurrentItem() == 0)
                    {
                        viewPager.setCurrentItem(1);
                    }
                    else
                        if (viewPager.getCurrentItem() == 1)
                        {
                            viewPager.setCurrentItem(2);
                        }
                        else
                            if (viewPager.getCurrentItem() == 2)
                            {
                                viewPager.setCurrentItem(3);
                            }
                            else
                                if (viewPager.getCurrentItem() == 3)
                                {
                                    viewPager.setCurrentItem(4);
                                }
                                else
                                {
                                    viewPager.setCurrentItem(0);
                                }
                    
                }
            });
            
        }
    }
}

