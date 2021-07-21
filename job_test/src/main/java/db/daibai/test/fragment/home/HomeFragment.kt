package db.daibai.test.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bytedance.sdk.openadsdk.*
import db.daibai.basic.fragment.BaseFragment
import db.daibai.test.databinding.FragmentHomeBinding
import db.daibai.test.fragment.splash.SplashFragment
import db.daibai.test.fragment.video.VideoListFragment
import db.daibai.test.utils.ToolsUtil
import db.daibai.test.viewmodel.MainViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    lateinit var viewModel: MainViewModel
    lateinit var count: MutableLiveData<Int>
    lateinit var mtAdNative: TTAdNative
    var mTTFullScreenVideoAd: TTFullScreenVideoAd? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(
                MainViewModel::class.java
            )
        count = viewModel.count

        viewModel.count.observe(viewLifecycleOwner, {
            binding.textView.text = it.toString()
        })
        lifecycle.addObserver(binding.chronometer)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.btnAdd.setOnClickListener {
            count.value = (count.value ?: 0) + 1
        }
        binding.btnLess.setOnClickListener {
            count.value = (count.value ?: 0) - 1
        }

        binding.btnNative.setOnClickListener {
            start(VideoListFragment())
        }

        binding.btnInteraction.setOnClickListener {
            val mTTAdNative = TTAdSdk.getAdManager().createAdNative(_mActivity)
            val adSlot = AdSlot.Builder()
                .setCodeId("946385779") //广告位id
                .setSupportDeepLink(true)
                .setOrientation(TTAdConstant.VERTICAL)
                .setExpressViewAcceptedSize(
                    600f,
                    200f
                ) //期望模板广告view的size,单位dp
                .build()
            mTTAdNative.loadFullScreenVideoAd(
                adSlot,
                object : TTAdNative.FullScreenVideoAdListener {

                    override fun onError(code: Int, message: String?) {
                        ToolsUtil.show(requireContext(), "$code: $message")
                    }

                    override fun onFullScreenVideoAdLoad(ad: TTFullScreenVideoAd?) {
                        mTTFullScreenVideoAd = ad
                        ToolsUtil.show(
                            requireContext(),
                            "FullVideoAd loaded  广告类型：" + getAdType((ad?.fullVideoAdType ?: 0))
                        )

                        ad?.setFullScreenVideoAdInteractionListener(object :
                            TTFullScreenVideoAd.FullScreenVideoAdInteractionListener {
                            override fun onAdShow() {
                                ToolsUtil.show(requireContext(), "FullVideoAd show")
                            }

                            override fun onAdVideoBarClick() {
                                ToolsUtil.show(requireContext(), "FullVideoAd bar click")
                            }

                            override fun onAdClose() {
                                ToolsUtil.show(requireContext(), "FullVideoAd close")
                            }

                            override fun onVideoComplete() {
                                ToolsUtil.show(requireContext(), "FullVideoAd complete")
                            }

                            override fun onSkippedVideo() {
                                ToolsUtil.show(requireContext(), "FullVideoAd skipped")
                            }

                        })
                    }

                    override fun onFullScreenVideoCached() {
                        ToolsUtil.show(requireContext(), "ok")
                        mTTFullScreenVideoAd?.showFullScreenVideoAd(
                            requireActivity(),
                            TTAdConstant.RitScenes.GAME_GIFT_BONUS,
                            null
                        )
                    }

                })
        }
        start(SplashFragment())
    }

    private fun getAdType(type: Int): String {
        when (type) {
            TTAdConstant.AD_TYPE_COMMON_VIDEO -> return "普通全屏视频，type=$type"
            TTAdConstant.AD_TYPE_PLAYABLE_VIDEO -> return "Playable全屏视频，type=$type"
            TTAdConstant.AD_TYPE_PLAYABLE -> return "纯Playable，type=$type"
        }
        return "未知类型+type=$type"
    }

    companion object {
        val instance: HomeFragment by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            HomeFragment()
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
}


