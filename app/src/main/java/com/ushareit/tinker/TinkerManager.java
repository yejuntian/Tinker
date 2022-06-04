package com.ushareit.tinker;

import android.content.Context;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * 对Tinker所有api做一层封装
 */
public class TinkerManager {
    private static boolean isInstalled = false;
    private static ApplicationLike mAppLike;

    /**
     * 由外部调用完成Tinker初始化
     *
     * @param applicationLike
     */
    public static void installTinker(ApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled) {
            return;
        }
        //完成Tinker初始化
        TinkerInstaller.install(mAppLike);
        isInstalled = true;

    }

    /**
     * 完成patch文件加载
     *
     * @param path
     */
    public static void loadPatch(String path) {
        if (Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    /**
     * 通过ApplicationLike获取Context
     */
    public static Context getApplicationContext() {
        if (mAppLike != null) {
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }
}
