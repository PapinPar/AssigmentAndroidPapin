package com.chi.assigmentandroidpapin.base.activ

import android.util.Log
import com.chi.assigmentandroidpapin.R
import com.chi.assigmentandroidpapin.core.ApiProvider
import com.chi.assigmentandroidpapin.core.ResourcesRepository
import com.chi.assigmentandroidpapin.net.NetManager
import com.chi.assigmentandroidpapin.net.RestAPI
import com.woofy.ui.base.ErrorModel
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

abstract class BasePresenterImpl<T : BaseView> : BasePresenter<T>, CoroutineScope {

    var viewRef: WeakReference<T>? = null
    var job = SupervisorJob()
    val api: RestAPI by lazy { provideApiManager().getApi() }

    abstract fun provideApiManager(): ApiProvider


    override val coroutineContext: CoroutineContext
        get() = job +
                Dispatchers.IO +
                CoroutineExceptionHandler { _, throwable ->
                    handleDefaultNetError(throwable)
                }

    override fun bindView(view: T) {
        viewRef = WeakReference(view)

    }

    override fun unbindView() {
        viewRef = null
        job.cancel()
    }

    override fun onDestroy() {
        viewRef = null
    }

    private fun handleDefaultNetError(throwable: Throwable) {
        launch(Dispatchers.Main) {
            Log.e("Throwable", throwable.message)
            viewRef?.get()?.stopLoadingDialog()
            when (throwable) {
                is HttpException -> {
                    throwable.code()
                    try {
                        val jsonError = throwable.response().errorBody()?.string()
                        jsonError?.let {
                            val error = NetManager.getGson().fromJson(it, ErrorModel::class.java)
                            if (!error.errors.isNullOrEmpty()) {
                                viewRef?.get()?.showAlertDialog(error.errors[0].message!!)
                            } else if (error.message.isNotEmpty()) {
                                viewRef?.get()?.showAlertDialog(error.message)
                            } else {
                                viewRef?.get()?.showAlertDialog(it)
                            }
                        }
                    } catch (e: Exception) {
                        viewRef?.get()?.showAlertDialog("Unknown network error")
                        e.printStackTrace()
                    }
                }
                is SocketTimeoutException -> {
                    viewRef?.get()?.showAlertDialog("Socket error")
                    throwable.printStackTrace()
                }
                is UnknownHostException -> {
                    viewRef?.get()
                        ?.showAlertDialog(ResourcesRepository.resources.getString(R.string.no_internet_message))
                    throwable.printStackTrace()
                }
                else -> {
                    viewRef?.get()?.showAlertDialog("Unknown Error")
                    throwable.printStackTrace()
                }
            }
        }
    }
}