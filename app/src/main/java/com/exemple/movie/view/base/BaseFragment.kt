package com.exemple.movie.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getLayout(inflater,container)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentReady()
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onStop() {
        super.onStop()
        onFragmentClosed()
    }


    abstract fun onFragmentReady()

    abstract fun getLayout(inflater: LayoutInflater, container: ViewGroup?):View

    abstract fun onFragmentCreated()

    abstract fun onFragmentClosed()

//    abstract fun SettingsMovieAdapter(): Any
//    abstract fun PresenterSettings(settingsFragment: SettingsFragment): SettingsPresenter.Presenter?
}