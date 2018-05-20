package com.wzh.gank.mygank.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * @author 开发者：WZH
 * @date 创建时间：2017/9/5
 * @Description： 去掉 BottomNavigation 的动画
 * 来源
 *  http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0428/7888.html
 */


public class BottomNavigationViewHelper {


    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView mBottomNavigationView = (BottomNavigationMenuView) view.getChildAt(0);
        try{
            Field shiftingMode = mBottomNavigationView
                    .getClass()
                    .getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(mBottomNavigationView,false);
            shiftingMode.setAccessible(false);
            for (int i = 0;i<mBottomNavigationView.getChildCount();i++){
                BottomNavigationItemView itemView = (BottomNavigationItemView) mBottomNavigationView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        }catch (NoSuchFieldException e){
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        }catch (IllegalAccessException e){
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }

    }
}
