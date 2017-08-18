package com.aakoorathh.betguru;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GP'$ on 12-08-2017.
 */

public class AboutUs extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        
        View rootView = inflater.inflate(R.layout.about_us, container, false);
        return rootView;
    }
    
    @Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
{
    super.onViewCreated(view, savedInstanceState);
    
    getActivity().setTitle("About Us");
}
}
