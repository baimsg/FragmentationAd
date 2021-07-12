package db.daibai.basic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment

abstract class BaseFragment<VB : ViewBinding> : SwipeBackFragment() {
     lateinit var binding: VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        return binding.root
    }

    protected abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB


}