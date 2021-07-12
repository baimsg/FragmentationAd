package db.daibai.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import db.daibai.basic.fragment.BaseFragment
import db.daibai.test.databinding.FragmentMainBinding
import db.daibai.test.viewmodel.MainViewModel

class HomeFragment : BaseFragment<FragmentMainBinding>() {
    lateinit var viewModel: MainViewModel
    lateinit var count: MutableLiveData<Int>

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
        start(SplashFragment())
    }

    companion object {
        val instance: HomeFragment by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            HomeFragment()
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }
}


