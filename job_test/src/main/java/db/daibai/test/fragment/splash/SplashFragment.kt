package db.daibai.test.fragment.splash

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

    private fun initView() {
        val adSlot = AdSlot.Builder()
            .setCodeId("887516503")
            .setImageAcceptedSize(1080, 1920)
            .build()
        if (TTAdSdk.isInitSuccess()) {
            ttAdManager = TTAdSdk.getAdManager()
            mTTAdNative = ttAdManager.createAdNative(context)
        }
        mTTAdNative.loadSplashAd(adSlot, object : TTAdNative.SplashAdListener {
            override fun onError(p0: Int, p1: String?) {
                ToolsUtil.show(context!!, "$p0:$p1")
                pop()
            }

            override fun onTimeout() {
                ToolsUtil.show(context!!, "开屏广告获取超时！")
                pop()
            }

            override fun onSplashAdLoad(ad: TTSplashAd?) {
                if (ad == null) return
                val view: View = ad.splashView
                binding.flContain.removeAllViews()
                binding.flContain.addView(view)
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