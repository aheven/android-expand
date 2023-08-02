package heven.holt.expand.architecture.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import skin.support.widget.SkinCompatSupportable

/**
 * <pre>
 *     author : HevenHolt
 *     e-mail : hyt0302@qq.com
 *     time   : 2023/08/01
 *     desc   : Activity的基类
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity : AppCompatActivity(), SkinCompatSupportable {

    override fun getDelegate(): AppCompatDelegate {
        return SkinAppCompatDelegateImpl.get(this, this)
    }

    override fun applySkin() {

    }
}