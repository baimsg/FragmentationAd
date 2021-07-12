package db.daibai.test.view

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyChronometer(context: Context, attrs: AttributeSet) : Chronometer(context, attrs),
    LifecycleObserver {

    private var baseTime: Long = 0L

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun startMeter() {
        base = SystemClock.elapsedRealtime() - baseTime
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun stopMeter() {
        baseTime = SystemClock.elapsedRealtime() - base
        stop()
    }
}