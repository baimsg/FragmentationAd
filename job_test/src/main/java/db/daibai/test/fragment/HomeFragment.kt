package db.daibai.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bytedance.sdk.openadsdk.*
import db.daibai.basic.fragment.BaseFragment
import db.daibai.test.databinding.FragmentHomeBinding
import db.daibai.test.utils.ToolsUtil
import db.daibai.test.viewmodel.MainViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    lateinit var viewModel: MainViewModel
    lateinit var count: MutableLiveData<Int>
    lateinit var mtAdNative: TTAdNative

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
        binding.btnInteraction.setOnClickListener {
            val mTTAdNative = TTAdSdk.getAdManager().createAdNative(_mActivity)
            val adSlot = AdSlot.Builder()
                .setCodeId("946385779") //广告位id
                .setSupportDeepLink(true)
                .setOrientation(TTAdConstant.VERTICAL)
                .setExpressViewAcceptedSize(
                    600f,
                    400f
                ) //期望模板广告view的size,单位dp
                .build()
            mTTAdNative.loadFullScreenVideoAd(
                adSlot,
                object : TTFullScreenVideoAd.FullScreenVideoAdInteractionListener,
                    TTAdNative.FullScreenVideoAdListener {


                    override fun onError(code: Int, message: String?) {
                        ToolsUtil.show(requireContext(), "$code: $message")
                        binding.flContainer.removeAllViews()
                    }

                    override fun onFullScreenVideoAdLoad(ad: TTFullScreenVideoAd?) {
                        binding.flContainer.removeAllViews()
//                        binding.flContainer.addView(ad.)
                    }

                    override fun onFullScreenVideoCached() {
                        TODO("Not yet implemented")
                    }

                    override fun onAdShow() {
                        TODO("Not yet implemented")
                    }

                    override fun onAdVideoBarClick() {
                    }

                    override fun onAdClose() {
                        TODO("Not yet implemented")
                    }

                    override fun onVideoComplete() {
                        TODO("Not yet implemented")
                    }

                    override fun onSkippedVideo() {
                        TODO("Not yet implemented")
                    }
                })

        }
        start(SplashFragment())

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


