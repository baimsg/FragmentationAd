package db.daibai.basic.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseActivity<VB : ViewBinding> : SupportActivity(), ISupportActivity {
    private var isAppAlreadyStart = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isAppAlreadyStart) return
        if (!isTaskRoot) {
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == intent.action) {
                finish()
                isAppAlreadyStart = true
                return
            }
        } else {
            setContentView(binding.root)
        }
        setStateBar()
    }

    open var stateBackcolor = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

    protected abstract val binding: VB

    private fun setStateBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or stateBackcolor)
            window.statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
    }


}