package db.daibai.test

import android.app.Application
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdConstant
import com.bytedance.sdk.openadsdk.TTAdSdk
import com.tmall.wireless.tangram.TangramBuilder
import com.tmall.wireless.tangram.util.IInnerImageSetter
import me.yokeyword.fragmentation.Fragmentation

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        TTAdSdk.init(
            this,
            TTAdConfig.Builder()
                .appId("5189002")
                .useTextureView(true) //默认使用SurfaceView播放视频广告,当有SurfaceView冲突的场景，可以使用TextureView
                .appName("楽")
                .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)//落地页主题
                .allowShowNotify(true) //是否允许sdk展示通知栏提示,若设置为false则会导致通知栏不显示下载进度
                .debug(true) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
                .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI) //允许直接下载的网络状态集合,没有设置的网络下点击下载apk会有二次确认弹窗，弹窗中会披露应用信息
                .supportMultiProcess(false) //是否支持多进程，true支持
                .asyncInit(true) //是否异步初始化sdk,设置为true可以减少SDK初始化耗时。3450版本开始废弃~~
                //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
                .build()
        )


        //fragmentation 悬浮球初始化！
        Fragmentation.builder().stackViewMode(Fragmentation.BUBBLE)
            .debug(true)
            .handleException {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
            .install()
        loadTanGram()
    }

    private fun loadTanGram() {
        TangramBuilder.init(this, object : IInnerImageSetter {
            override fun <IMAGE : ImageView?> doLoadImageUrl(view: IMAGE, url: String?) {
                Glide.with(this@App).load(url).into(view!!)
            }
        }, ImageView::class.java)

    }
}