package com.atguigu.yutianzhu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.yutianzhu.base.BaseFragment;
import com.atguigu.yutianzhu.fragment.CustomOneFragment;
import com.atguigu.yutianzhu.fragment.CustomTwoFragment;
import com.atguigu.yutianzhu.fragment.HomeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flmain;
    @InjectView(R.id.custom_home)
    RadioButton customHome;
    @InjectView(R.id.custom_one)
    RadioButton customOne;
    @InjectView(R.id.custom_two)
    RadioButton customTwo;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;

    private ArrayList<BaseFragment> fragments;

    private int position;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();

        MyListener();
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CustomOneFragment());
        fragments.add(new CustomTwoFragment());

    }

    private void MyListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.custom_home:
                        position = 0;
                        break;
                    case R.id.custom_one:
                        position = 1;
                        break;
                    case R.id.custom_two:
                        position = 2;
                        break;
                }
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        rgMain.check(R.id.custom_home);
    }

    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if(tempFragment != currentFragment){

            //得到FragmentMager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if(!currentFragment.isAdded()){
                //缓存的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }

                //添加
                ft.add(R.id.fl_main,currentFragment);

            }else{
                //缓存的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }

                //显示
                ft.show(currentFragment);
            }

            //事务提交
            ft.commit();


            //把当前的赋值成缓存的
            tempFragment = currentFragment;

        }

    }
}
