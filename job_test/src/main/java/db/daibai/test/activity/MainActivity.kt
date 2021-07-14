package db.daibai.test.activity

import android.os.Bundle
import db.daibai.basic.activity.BaseActivity
import db.daibai.test.R
import db.daibai.test.databinding.ActivityMainBinding
import db.daibai.test.fragment.HomeFragment
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator


class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultFragmentBackground(R.drawable.shape_white_bg)
        if (findFragment(HomeFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.instance)
            fragmentAnimator = DefaultVerticalAnimator()
        }
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}

