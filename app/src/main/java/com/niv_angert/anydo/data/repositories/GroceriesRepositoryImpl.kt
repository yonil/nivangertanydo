package com.niv_angert.anydo.data.repositories

import com.niv_angert.anydo.data.entities.Bag
import com.niv_angert.anydo.data.sources.remote.RemoteHelper
import io.reactivex.rxjava3.core.Flowable

/**
 *Created by Niv Angert on 02/08/2021
 **/
class GroceriesRepositoryImpl(
    private val remoteHelper: RemoteHelper
) : GroceriesRepository {

    override fun observeBags(): Flowable<Bag> {
        return remoteHelper
            .observeBags()
    }
}