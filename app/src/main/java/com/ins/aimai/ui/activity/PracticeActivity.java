package com.ins.aimai.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ins.aimai.R;
import com.ins.aimai.interfaces.PagerInter;
import com.ins.aimai.ui.adapter.PagerAdapterPractice;
import com.ins.aimai.ui.base.BaseAppCompatActivity;
import com.ins.common.utils.ViewPagerUtil;

public class PracticeActivity extends BaseAppCompatActivity implements View.OnClickListener, PagerInter {

    private ViewPager pager;
    private PagerAdapterPractice adapterPager;

    private String[] titles = new String[]{"练习题库", "练习列表"};

    public static void start(Context context) {
        Intent intent = new Intent(context, PracticeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        setToolbar(titles[0]);
        initBase();
        initView();
        initCtrl();
        initData();
    }

    private void initBase() {
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
    }

    private void initCtrl() {
        adapterPager = new PagerAdapterPractice(getSupportFragmentManager(), titles);
        pager.setAdapter(adapterPager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setToolbar(titles[position]);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right:
                ViewPagerUtil.next(pager);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() != 0) {
            ViewPagerUtil.last(pager);
        } else {
            finish();
        }
    }

    @Override
    public void next() {
        ViewPagerUtil.next(pager);
    }

    @Override
    public void last() {
        ViewPagerUtil.last(pager);
    }

    @Override
    public void goPosition(int position) {
        ViewPagerUtil.goPosition(pager, position);
    }
}
