package www.yqc.com.advanced.IntentService

import android.app.IntentService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Message
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by yqc on 2019/2/17.
 */
class MyIntentService : IntentService("MyIntentService") {
    companion object {
        var mUpdateUi: iUpdateUi? = null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onHandleIntent(intent: Intent?) {
        val url = intent!!.getStringExtra("download_url")
        val flag = intent.getIntExtra("index_flag", 0)
        val bitmap = downloadUrlBitmap(url)
        val message = Message.obtain()
        message.what = flag
        message.obj = bitmap
        mUpdateUi!!.updateUi(message)
    }

    interface iUpdateUi {
        fun updateUi(msg: Message)
    }

    private fun downloadUrlBitmap(url: String): Bitmap? {
        var urlConnection: HttpURLConnection? = null
        var ins: BufferedInputStream? = null
        var result: Bitmap? = null
        try {
            val url1 = URL(url)
            urlConnection = url1.openConnection() as HttpURLConnection
            ins = BufferedInputStream(urlConnection.inputStream, 8 * 1024)
            result = BitmapFactory.decodeStream(ins)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            urlConnection!!.disconnect()
            ins!!.close()
        }
        return result
    }
}