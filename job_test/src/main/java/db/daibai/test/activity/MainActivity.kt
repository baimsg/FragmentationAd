package db.daibai.test.activity

import android.os.Bundle
import android.view.View
import com.bytedance.sdk.openadsdk.*
import com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
import db.daibai.basic.activity.BaseActivity
import db.daibai.test.R
import db.daibai.test.databinding.ActivityMainBinding
import db.daibai.test.fragment.HomeFragment
import db.daibai.test.utils.ToolsUtil
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultFragmentBackground(R.drawable.shape_white_bg)
        if (findFragment(HomeFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.instance)
            fragmentAnimator = DefaultVerticalAnimator()
        }
        binding.tvTest.text = "TEXT MODE"
        val adSlot = AdSlot.Builder()
            .setCodeId("946381760") //广告位id
            .setSupportDeepLink(true)
            .setAdCount(1) //请求广告数量为1到3条
            .setExpressViewAcceptedSize(640f, 50f) //期望模板广告view的size,单位dp
            .build()

        TTAdSdk.getAdManager().createAdNative(this)
            .loadBannerExpressAd(adSlot, object : TTAdNative.NativeExpressAdListener {
                override fun onError(p0: Int, p1: String?) {
                    ToolsUtil.show(this@MainActivity, "$p0 : $p1")
                    binding.flBanner.removeAllViews()
                }

                override fun onNativeExpressAdLoad(ads: MutableList<TTNativeExpressAd>?) {
                    val mTTNativeExpressAd = ads!![0]
                    mTTNativeExpressAd.setSlideIntervalTime(30 * 1000)
                    mTTNativeExpressAd.render()
                    binding.flBanner.removeAllViews()
                    binding.flBanner.addView(mTTNativeExpressAd.expressAdView)
                }
            })
    }

    private fun bindAdListener(ad: TTNativeExpressAd) {
        ad.setExpressInteractionListener(object : ExpressAdInteractionListener {
            override fun onAdClicked(view: View, type: Int) {
                ToolsUtil.show(this@MainActivity, "广告被点击")
            }

            override fun onAdShow(view: View, type: Int) {
                ToolsUtil.show(this@MainActivity, "广告展示")
                binding.flBanner.removeAllViews()
                binding.flBanner.addView(view)
            }

            override fun onRenderFail(view: View, msg: String, code: Int) {
                ToolsUtil.show(this@MainActivity, "$msg code:$code")
            }

            override fun onRenderSuccess(view: View, width: Float, height: Float) {
            }
        })

    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}

