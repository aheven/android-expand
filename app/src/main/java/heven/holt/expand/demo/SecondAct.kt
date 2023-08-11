package heven.holt.expand.demo

import android.content.Intent
import android.os.Bundle
import heven.holt.expand.demo.databinding.ActMainBinding
import heven.holt.expand.ktx.doOnClick

class SecondAct : BaseBindingActivity<ActMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.doOnClick {
            val intent = Intent()
            intent.putExtra("data", "HevenHolt")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}