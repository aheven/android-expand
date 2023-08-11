package heven.holt.expand.ktx

import android.Manifest.permission.BLUETOOTH
import android.bluetooth.BluetoothAdapter
import androidx.annotation.RequiresPermission

@get:RequiresPermission(BLUETOOTH)
inline val isBluetoothEnabled: Boolean
    get() = BluetoothAdapter.getDefaultAdapter()?.isEnabled == true