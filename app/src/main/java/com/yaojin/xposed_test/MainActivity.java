package com.yaojin.xposed_test;


import android.os.Bundle;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MainActivity implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("handleLoadPackage执行啦!");
        if(loadPackageParam.packageName.equals("com.yaojin.myapplication"))
        {
            XposedBridge.log("开始hook测试程序");
            findAndHookMethod(TextView.class, "setText", CharSequence.class, new XC_MethodHook() {
                /**
                 *  OnCreate 之前回调,
                 *  @param param onCreate方法的信息,可以修改
                 *  @throws Throwable
                 **/
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("处理setText方法");
                    param.args[0]="我是被Xposed修改的";
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param)throws Throwable {

                }
                });
        }

    }
}
