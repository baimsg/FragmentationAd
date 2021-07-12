package db.daibai.test.activity

import db.daibai.basic.activity.BaseActivity
import db.daibai.test.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {


    override val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
//        ActivityMainBinding.inflate(
//            layoutInflater
//        )
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setDefaultFragmentBackground(R.drawable.shape_white_bg)
//        setContentView(binding.root)
//        if (findFragment(HomeFragment::class.java) == null) {
//            loadRootFragment(R.id.fl_container, HomeFragment.instance)
//            fragmentAnimator = DefaultVerticalAnimator()
//        }
//
//'
//    }

}

