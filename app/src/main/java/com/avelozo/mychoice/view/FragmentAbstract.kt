package com.avelozo.mychoice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.avelozo.mychoice.R
import com.avelozo.mychoice.contract.BaseView
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein


open class FragmentAbstract : Fragment(), KodeinInjected, BaseView {

    override val injector = KodeinInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(appKodein())
        retainInstance = true
    }


    protected fun loadFragment(frag: FragmentAbstract) {
        fragmentManager
            ?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.fragmentContainer, frag)
            ?.commit()

    }

}