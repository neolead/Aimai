package com.ins.aimai.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.ins.aimai.R;
import com.ins.aimai.bean.Address;
import com.ins.aimai.bean.common.EventBean;
import com.ins.aimai.common.AppHelper;
import com.ins.aimai.ui.adapter.PagerAdapterAddress;
import com.ins.aimai.ui.base.BaseAppCompatActivity;
import com.ins.aimai.utils.ToastUtil;
import com.ins.common.utils.TabLayoutUtil;
import com.ins.common.utils.ViewPagerUtil;

import org.greenrobot.eventbus.EventBus;

public class AddressActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private TabLayout tab;
    private ViewPager pager;
    private PagerAdapterAddress adapterPager;

    private String[] titles = new String[]{"省份", "城市", "区县"};
    private boolean hasAll; //是否允许选择全国
    private Address address;
    private String province = "";
    private String city = "";
    private String disrict = "";

    public static void start(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        //如果是政府那么可以选择全国
        if (AppHelper.isGov()) {
            intent.putExtra("hasAll", true);
        }
        context.startActivity(intent);
    }

    public static void startWithAll(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        intent.putExtra("hasAll", true);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        setToobarText();
        initBase();
        initView();
        initCtrl();
        initData();
    }

    private void initBase() {
        if (getIntent().hasExtra("hasAll")) {
            hasAll = getIntent().getBooleanExtra("hasAll", false);
        }
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        pager = (ViewPager) findViewById(R.id.pager);
        findViewById(R.id.btn_right).setOnClickListener(this);
    }

    private void initCtrl() {
        adapterPager = new PagerAdapterAddress(getSupportFragmentManager(), titles);
        pager.setAdapter(adapterPager);

        TabLayoutUtil.setTab(tab, 0, "请选择");

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                ViewPagerUtil.goPosition(pager, position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right:
                postAddressAndFinish(null);
                break;
        }
    }

    private void setToobarText() {
        if (TextUtils.isEmpty(province) && TextUtils.isEmpty(city) && TextUtils.isEmpty(disrict)) {
            setToolbar("地址选择");
        } else {
            setToolbar(province + city + disrict);
        }
    }

    public void next(Address address) {
        this.address = address;
        String name = address.getName();
        int currentItem = pager.getCurrentItem();
        if (currentItem == 0) {
            province = name;
            city = "";
            disrict = "";
            setToobarText();
            TabLayoutUtil.setTab(tab, 0, name);
            TabLayoutUtil.setTab(tab, 1, "请选择", true);
            TabLayoutUtil.removeTabAt(tab, 2);
            ViewPagerUtil.goPosition(pager, 1);
        } else if (currentItem == 1) {
            city = name;
            setToobarText();
            TabLayoutUtil.setTab(tab, 1, name);
            TabLayoutUtil.setTab(tab, 2, "请选择", true);
            ViewPagerUtil.goPosition(pager, 2);
        } else if (currentItem == 2) {
            disrict = name;
            setToobarText();
            TabLayoutUtil.setTab(tab, 2, name, true);

            //post选择地区消息
            postAddressAndFinish(null);
        } else {
            //不会存在这种情况
        }
    }

    public void postAddressAndFinish(Address address) {
        Address postBean = address == null ? this.address : address;
        if (postBean == null) {
            ToastUtil.showToastShort("请选择地区");
            return;
        }
        EventBean event = new EventBean(EventBean.EVENT_SELECT_ADDRESS);
        postBean.setAddress(getToolbarText());
        event.put("address", postBean);
        EventBus.getDefault().post(event);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() != 0) {
            last();
        } else {
            finish();
        }
    }

    public void last() {
        ViewPagerUtil.last(pager);
        int currentItem = pager.getCurrentItem();
        TabLayoutUtil.setTabCurrent(tab, currentItem);
    }

    /////////////get & set /////////////////


    public boolean isHasAll() {
        return hasAll;
    }
}
