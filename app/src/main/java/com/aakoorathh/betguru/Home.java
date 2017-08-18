package com.aakoorathh.betguru;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private ImageView iv_UserPhoto;
    private TextView tv_UserName,tv_UserEmail;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        // Framelayout
    
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content);
        
         // To hide all views of framelayout
        
        // frameLayout.removeAllViews();
        
        // Bottom Navigation
        
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        
        if (bottomNavigationView != null)
        {
            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener()
                    {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item)
                        {
                            // Write code to perform some actions.
    
                            int id = item.getItemId();
                            
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            
                            FragmentTransaction fragmentTransaction =
                                    fragmentManager.beginTransaction();
    
                            frameLayout.removeAllViews();
                            
                            if (id == R.id.navigation_live_bets)
                            {
                                // Code For Live Bets
                                
                                LiveBets liveBets = new LiveBets();
                                
                                //fragmentTransaction.add(R.id.content, liveBets).commit();
                                
                                fragmentTransaction.replace(R.id.content, liveBets).commit();
                                
                            }
                            else
                                if (id == R.id.navigation_wallet)
                                {
                                    // Code For Wallet
                                    
                                    Wallet wallet = new Wallet();
                                    
                                    //fragmentTransaction.add(R.id.content,wallet).commit();
                                    
                                    fragmentTransaction.replace(R.id.content, wallet).commit();
                                    
                                }
                                else
                                    if (id == R.id.navigation_history)
                                    {
                                        // Code For History
                                        
                                        History history = new History();
                                        
                                        // fragmentTransaction.add(R.id.content,history).commit();
                                        
                                        fragmentTransaction.replace(R.id.content, history).commit();
                                        
                                    }
                                    else
                                        if (id == R.id.navigation_about_us)
                                        {
                                            // Code For About Us
                                            
                                            
                                        }
                            
                            return false;
                        }
                    });
            
            /*   Profile API integration       */
            
            iv_UserPhoto = (ImageView)findViewById(R.id.iv_UserPhoto);
    
            tv_UserName = (TextView) findViewById(R.id.tv_UserName);
            tv_UserEmail = (TextView)findViewById(R.id.tv_UserEmail);
            
            
        }

        
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    
    // Menu code
    
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }*/
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
    
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new Fragment();
        
        if (id == R.id.nav_Home)
        {
            // Handle the Home action
            
            fragment =new LiveBets();
        }
        else
            if (id == R.id.nav_History)
            {
                fragment = new History();
            }
            else
                if (id == R.id.nav_Wallet)
                {
                    fragment = new Wallet();
                }
                else
                    if (id == R.id.nav_Invite_Friends)
                    {
                        
                        
                    }
                    else
                        if (id == R.id.nav_Sahre_Link)
                        {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"'BEtguru' app..");
                            String shareBodyText = "Hey..\nCheck out new 'BetGuru' app." +
                                    "\n"+"Share and Download it now\n\n"+"https://play.google.com/store/apps/details?id=" + Home.this.getPackageName();
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                            startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
    
                        }
                        else
                            if (id == R.id.nav_About_Us)
                            {
                                
                            }
    
        transaction.replace(R.id.content, fragment);
        transaction.commit();
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
