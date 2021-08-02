package com.niv_angert.anydo.domain

import com.niv_angert.anydo.data.entities.Bag
import com.niv_angert.anydo.data.repositories.GroceriesRepository
import io.reactivex.rxjava3.core.Flowable

/**
 *Created by Niv Angert on 02/08/2021
 **/
class ObserveBagsUseCase(private val groceriesRepository: GroceriesRepository) {
    operator fun invoke(): Flowable<Bag> {
        return groceriesRepository.observeBags()
    }
}