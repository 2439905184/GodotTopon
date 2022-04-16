package com.littlesandbox.topongodot;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATSDK;
import com.anythink.core.api.AdError;
import com.anythink.core.api.DeviceInfoCallback;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialListener;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoListener;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.UsedByGodot;

//此版本聚合的是快手广告和百度广告
public class ToponGodot extends GodotPlugin
{
    public static Context ctx;
    public ToponGodot(Godot godot)
    {
        super(godot);
    }
    @UsedByGodot
    public void init(String appid,String appkey)
    {
        ctx = getActivity().getApplicationContext();
        ATSDK.init(getActivity().getApplicationContext(),appid,appkey);
        ATSDK.setNetworkLogDebug(true);
    }
    //激励广告
    @UsedByGodot
    public void showRewardVideoAd(String placementid)
    {
        String tag = this.getClass().toString();
        ATRewardVideoAd videoAd = new ATRewardVideoAd(ctx,placementid);
        videoAd.setAdListener(new ATRewardVideoListener()
        {
            @Override
            public void onRewardedVideoAdLoaded()
            {
                Log.d(tag,"广告已加载");
            }

            @Override
            public void onRewardedVideoAdFailed(AdError adError)
            {
                Log.e(tag, adError.getCode());
                Log.e(tag, adError.getFullErrorInfo());
            }

            @Override
            public void onRewardedVideoAdPlayStart(ATAdInfo atAdInfo)
            {
                Log.d(tag,"广告播放开始");
            }

            @Override
            public void onRewardedVideoAdPlayEnd(ATAdInfo atAdInfo)
            {
                Log.d(tag, "广告播放结束");
            }

            @Override
            public void onRewardedVideoAdPlayFailed(AdError adError, ATAdInfo atAdInfo)
            {
                Log.e(tag, "广告播放失败");
                Log.e(tag, adError.getCode());
                Log.d(tag, adError.getFullErrorInfo());
            }

            @Override
            public void onRewardedVideoAdClosed(ATAdInfo atAdInfo)
            {
                Log.d(tag, "广告关闭");
            }

            @Override
            public void onRewardedVideoAdPlayClicked(ATAdInfo atAdInfo)
            {
                Log.d(tag, "广告被点击");
            }

            @Override
            public void onReward(ATAdInfo atAdInfo)
            {
                Log.d(tag, "可以获得广告奖励");
            }
        });
    }
    //todo 开屏广告
    @UsedByGodot
    public void showSplash()
    {

    }
    //插屏图片广告
    @UsedByGodot
    public void showInterstitial(String placementid)
    {
        String tag = this.getClass().toString();
        ATInterstitial ad = new ATInterstitial(ctx, placementid);
        ad.setAdListener(new ATInterstitialListener()
        {
            @Override
            public void onInterstitialAdLoaded()
            {
                Log.d(tag,"广告播放开始");
            }

            @Override
            public void onInterstitialAdLoadFail(AdError adError)
            {
                Log.e(tag, "广告记载失败");
                Log.e(tag, adError.getCode());
                Log.d(tag, adError.getFullErrorInfo());
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo atAdInfo)
            {
                Log.d(tag, "广告被点击");
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo atAdInfo)
            {
                Log.d(tag, "广告被展示");
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo atAdInfo)
            {
                Log.d(tag, "广告关闭");
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo)
            {
                Log.d(tag, "视频广告播放开始");
            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo)
            {
                Log.d(tag, "视频广告播放结束");
            }

            @Override
            public void onInterstitialAdVideoError(AdError adError)
            {
                Log.e(tag, "广告播放失败");
                Log.e(tag, adError.getCode());
                Log.d(tag, adError.getFullErrorInfo());
            }
        });
        ad.show(getActivity());
    }
    //获取手机硬件信息 用于测试
    @UsedByGodot
    public void testDeviceId()
    {
        ATSDK.testModeDeviceInfo(getActivity().getApplicationContext(), new DeviceInfoCallback()
        {
            @Override
            public void deviceInfo(String s)
            {
                Log.d("deviceInfo",s);
            }
        });
    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "ToponGodot";
    }
}
