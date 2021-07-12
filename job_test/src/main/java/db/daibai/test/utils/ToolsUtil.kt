package db.daibai.test.utils

import android.content.Context
import android.widget.Toast

object ToolsUtil {
    fun show(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}