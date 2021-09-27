package co.ghostnotes.sample.compose.tasklist.extensions

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(skeleton: String = "yyyyMMdd HH:mm:ss"): String {
    val format = DateFormat.getBestDateTimePattern(Locale.getDefault(), skeleton)
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}
