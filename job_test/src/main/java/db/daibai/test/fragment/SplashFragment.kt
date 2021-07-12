package db.daibai.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bytedance.sdk.openadsdk.*
import db.daibai.basic.fragment.BaseFragment
import db.daibai.test.databinding.FragmentSplashBinding
import db.daibai.test.utils.ToolsUtil

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    lateinit var mTTAdNative: TTAdNative
    lateinit var ttAdManager: TTAdManager

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    companion object {
        val instance: SplashFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SplashFragment()
        }
    }

    private fun initView() {
        val adSlot = AdSlot.Builder()
            .setCodeId("887503881")
            .setImageAcceptedSize(1080, 1920)
            .build()
        if (TTAdSdk.isInitSuccess()) {
            //一定要在初始化后才能调用，否则为空
            ttAdManager = TTAdSdk.getAdManager()
            mTTAdNative = ttAdManager.createAdNative(context)
        }
        mTTAdNative.loadSplashAd(adSlot, object : TTAdNative.SplashAdListener {
            override fun onError(p0: Int, p1: String?) {
                ToolsUtil.show(context!!, "$p0:$p1")
            }

            override fun onTimeout() {
                ToolsUtil.show(context!!, "获取超时！")
            }

            override fun onSplashAdLoad(ad: TTSplashAd?) {
                if (ad == null) return
                val view: View = ad.splashView
                binding.flContain.removeAllViews()
                //把SplashView 添加到ViewGroup中,注意开屏广告view：width =屏幕宽；height >=75%屏幕高
                binding.flContain.addView(view)
                //设置SplashView的交互监听器
                ad.setSplashInteractionListener(object : TTSplashAd.AdInteractionListener {
                    override fun onAdClicked(view: View, type: Int) {
                    }

                    override fun onAdShow(view: View, type: Int) {
                    }

                    override fun onAdSkip() {
                        pop()
                    }

                    override fun onAdTimeOver() {
                        pop()
                    }
                })
            }
        }, 4000)

    }
}