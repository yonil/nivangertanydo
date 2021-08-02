package com.niv_angert.anydo.data.sources.remote

import com.niv_angert.anydo.data.entities.Bag
import io.reactivex.rxjava3.core.Flowable


/**
 *Created by Niv Angert on 02/08/2021
 **/
interface RemoteHelper {
    fun observeBags(): Flowable<Bag>
}