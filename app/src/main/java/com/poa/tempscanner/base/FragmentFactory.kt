package com.poa.tempscanner.base

import android.os.Bundle

object FragmentFactory {
    @Throws(Exception::class)
    fun createFragment(tag: String, bundle: Bundle) = createFragment(tag).apply {
        arguments = bundle
    }

    @Throws(Exception::class)
    private fun createFragment(tag: String): androidx.fragment.app.Fragment {
        try {
            val tags = tag.split("_")
            return Class.forName(tags[0]).newInstance() as androidx.fragment.app.Fragment
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        throw Exception("Fragment name is not exist")
    }
}
