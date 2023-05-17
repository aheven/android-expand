package heven.holt.expand.demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import heven.holt.expand.ktx.asActivity
import heven.holt.expand.ktx.doOnClick
import heven.holt.expand.logger.Logger

class MainAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val textView = findViewById<TextView>(R.id.text_view)
        Logger.d(textView.context.asActivity())
        textView.doOnClick {
            textView.context.asActivity()?.finish()
        }
    }
}