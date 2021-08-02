package com.niv_angert.anydo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niv_angert.anydo.data.entities.Bag
import com.niv_angert.anydo.domain.ObserveBagsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *Created by Niv Angert on 02/08/2021
 **/
class MainViewModel(private val observeBagsUseCase: ObserveBagsUseCase) : ViewModel() {

    // Rx Params:
    private var observeBagsDisposable: Disposable? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    // Private MutableLiveData:
    private val bagsMutableLiveData: MutableLiveData<MutableList<Bag>> =
        MutableLiveData(mutableListOf())
    private val connectedMutableLiveData: MutableLiveData<Boolean> =
        MutableLiveData(true)

    // Public LiveData:
    val bagsLiveData: LiveData<MutableList<Bag>> = bagsMutableLiveData
    val connectedLiveData: LiveData<Boolean> = connectedMutableLiveData

    init {
        observeBags()
    }

    private fun observeBags() {

        observeBagsDisposable?.let {
            compositeDisposable.remove(it)
        }

        observeBagsDisposable =
            observeBagsUseCase()
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    // update feed:
                    val bags = bagsMutableLiveData.value ?: mutableListOf()
                    bags.add(0,it)
                    bagsMutableLiveData.value = bags
                }, {
                })

        observeBagsDisposable?.let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.takeUnless { it.isDisposed }?.clear()
    }

    // Public --------------------------------------------------------------------------------------

    fun onConnectionChanged(connected: Boolean) {

        // Set connectedMutableLiveData value:
        connectedMutableLiveData.value = connected

        // Handle connected state:
        if (!connected) {
            observeBagsDisposable?.let {
                compositeDisposable.remove(it)
            }
            observeBagsDisposable = null
        } else {
            observeBags()
        }
    }
}