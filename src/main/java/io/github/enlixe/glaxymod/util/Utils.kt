package io.github.enlixe.glaxymod.util

import java.io.File
import java.io.IOException

object Utils {
    @Throws(IOException::class)
    fun getJavaRuntime(): String {
        val os = System.getProperty("os.name")
        val java = "${System.getProperty("java.home")}${File.separator}bin${File.separator}${
            if (os != null && os.lowercase().startsWith("windows")) "java.exe" else "java"
        }"
        if (!File(java).isFile) {
            throw IOException("Unable to find suitable java runtime at $java")
        }
        return java
    }
}