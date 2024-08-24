package com.github.kr328.clash

import android.app.Application
import android.content.Context
import com.github.kr328.clash.common.Global
import com.github.kr328.clash.common.compat.currentProcessName
import com.github.kr328.clash.common.log.Log
import com.github.kr328.clash.remote.Remote
import com.github.kr328.clash.service.util.sendServiceRecreated
import com.github.kr328.clash.util.clashDir
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


@Suppress("unused")
class MainApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        Global.init(this)
    }

    override fun onCreate() {
        super.onCreate()

        val processName = currentProcessName
        extractGeoFiles()

        Log.d("Process $processName started")

        if (processName == packageName) {
            Remote.launch()
        } else {
            sendServiceRecreated()
        }
    }

    private fun extractGeoFiles() {
        clashDir.mkdirs();
        
        val geositeFile = File(clashDir, "geosite.dat")
        if(!geositeFile.exists()) {
            FileOutputStream(geositeFile).use {
                assets.open("geosite.dat").copyTo(it);
            }
        }

        val geoipFile = File(clashDir, "country.mmdb")
        if(!geoipFile.exists()) {
            FileOutputStream(geoipFile).use {
                assets.open("country.mmdb").copyTo(it);
            }
        }
        
        val geoipFile = File(clashDir, "ASN.mmdb")
        if(!geoipFile.exists()) {
            FileOutputStream(geoipFile).use {
                assets.open("ASN.mmdb").copyTo(it);
            }
        }
    }

    fun finalize() {
        Global.destroy()
    }
}
