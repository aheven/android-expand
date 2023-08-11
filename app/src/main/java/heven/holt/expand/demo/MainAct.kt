package heven.holt.expand.demo

import android.os.Bundle
import androidx.core.net.toUri
import heven.holt.expand.demo.databinding.ActMainBinding
import heven.holt.expand.ktx.EXTERNAL_MEDIA_VIDEO_URI
import heven.holt.expand.ktx.activityresult.registerForPickContentResult
import heven.holt.expand.ktx.doOnClick
import heven.holt.expand.ktx.isActivityExistsInStack
import heven.holt.expand.logger.Logger
import heven.holt.expand.ktx.activityresult.launch
import heven.holt.expand.ktx.activityresult.registerForRequestMultiplePermissionsResult
import heven.holt.expand.ktx.activityresult.registerForRequestPermissionResult
import heven.holt.expand.ktx.activityresult.registerForStartActivityResult
import heven.holt.expand.ktx.activityresult.registerForTakeVideoResult
import heven.holt.expand.ktx.externalCacheDirPath

class MainAct : BaseBindingActivity<ActMainBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setHeaders(ToolbarViewDelegate("首页"), NormalHeaderDelegate())

        val launcher = registerForTakeVideoResult {
        }

        val permissLaunch = registerForRequestPermissionResult{
            if (!it) return@registerForRequestPermissionResult
            launcher.launchAndSaveToCache()
        }





        binding.button.doOnClick {
            permissLaunch.launch(android.Manifest.permission.CAMERA)
        }

        Logger.d(isActivityExistsInStack<MainAct>())
        Logger.d(isActivityExistsInStack<SecondAct>())
    }
}