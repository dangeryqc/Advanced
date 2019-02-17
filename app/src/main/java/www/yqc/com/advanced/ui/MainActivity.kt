package www.yqc.com.advanced.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import www.yqc.com.advanced.IntentService.MyIntentService
import www.yqc.com.advanced.R

class MainActivity : AppCompatActivity() {

    private val mHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            image.setImageBitmap(msg!!.obj as Bitmap)
        }
    }

    var res = arrayOf(
            "https://img-blog.csdn.net/20160903083245762",
            "https://img-blog.csdn.net/20160903083252184",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083311972",
            "https://img-blog.csdn.net/20160903083319668",
            "https://img-blog.csdn.net/20160903083326871")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this@MainActivity, MyIntentService::class.java)
        btn1.setOnClickListener {
            for (index in res.indices) {
                intent.putExtra("download_url", res[index])
                intent.putExtra("index_flag", index)
                startService(intent)
            }
        }

        MyIntentService.mUpdateUi = object : MyIntentService.iUpdateUi {
            override fun updateUi(msg: Message) {
                mHandler.sendMessageDelayed(msg,msg.what*1000.toLong())
            }
        }
    }
}
