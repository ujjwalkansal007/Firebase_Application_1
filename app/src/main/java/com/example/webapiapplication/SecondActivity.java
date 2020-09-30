package com.example.webapiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

public class SecondActivity extends AppCompatActivity {

    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;

    private int indicatorWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Assign View Reference
        mTabs=findViewById(R.id.tab);
        mIndicator=findViewById(R.id.indicator);
        mViewPager=findViewById(R.id.viewPager);

        //Setup the viewpager and fragements
        TabFragmentAdapter adapter=new TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(),"Login");
        adapter.addFragment(new RegisterFragment(),"Register");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        final Intent intent = new Intent(getIntent());
        int value = intent.getIntExtra("value", -1);

        if(value == 1) {
            mViewPager.setCurrentItem(0);
        }
        else {
            mViewPager.setCurrentItem(1);
            }

        //Determine indicator width at runtime
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth=mTabs.getWidth()/mTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width=indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                FrameLayout.LayoutParams params= (FrameLayout.LayoutParams) mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset = (positionOffset+position)*indicatorWidth;

                params.leftMargin = (int) translationOffset;

                mIndicator.setLayoutParams(params);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}