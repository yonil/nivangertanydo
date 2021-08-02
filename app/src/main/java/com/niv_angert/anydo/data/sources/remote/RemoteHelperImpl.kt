package com.niv_angert.anydo.data.sources.remote

import com.futuremind.rxwebsocket.RxWebSocket
import com.futuremind.rxwebsocket.SocketState
import com.google.gson.Gson
import com.niv_angert.anydo.data.Consts
import com.niv_angert.anydo.data.entities.Bag
import io.reactivex.rxjava3.core.Flowable
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

/**
 *Created by Niv Angert on 02/08/2021
 **/
class RemoteHelperImpl() : RemoteHelper {

    override fun observeBags(): Flowable<Bag> {

        val okHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder().url(Consts.REMOTE_WS_BAGS).build()
        val rxSocket = RxWebSocket(okHttpClient, request)

        val socketConnection: Flowable<SocketState> = rxSocket
            .connect()
            .retryWhen { it.delay(3, TimeUnit.SECONDS) }
            .replay(1)
            .autoConnect()

        return socketConnection
            .ofType(SocketState.Connected::class.java)
            .flatMap {
                it.messageFlowable().map {
                    Gson().fromJson(it, Bag::class.java)
                }
            }
    }
}