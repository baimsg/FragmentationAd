package db.daibai.test.fragment.video

import android.view.LayoutInflater
import android.view.ViewGroup
import db.daibai.basic.fragment.BaseFragment
import db.daibai.test.databinding.FragmentVideoListBinding

/**
 * Create by Baimsg on 2021/7/21
 * 文件描述：
 *
 **/
class VideoListFragment : BaseFragment<FragmentVideoListBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoListBinding {
        return FragmentVideoListBinding.inflate(inflater, container, false)
    }

}