package com.chi.assigmentandroidpapin.ui.acitivity

import com.chi.assigmentandroidpapin.base.activ.BasePresenterImpl
import com.chi.assigmentandroidpapin.core.ApiProvider
import com.chi.assigmentandroidpapin.net.model.MappedData
import com.chi.assigmentandroidpapin.net.model.User
import com.chi.assigmentandroidpapin.utils.LIMIT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenterImpl(private val apiProvider: ApiProvider) : BasePresenterImpl<MainView>(), MainPresenter {


    override fun getImages(offset: Int) {
        viewRef?.get()?.startLoadingDialog()
        launch {
            val loginResponse = api.getImagesAsync(offset, LIMIT).await()
            withContext(Dispatchers.Main) {
                viewRef?.get()?.stopLoadingDialog()
                viewRef?.get()?.showImages(loginResponse.data?.users!!)
            }
        }
    }

    override fun provideApiManager(): ApiProvider {
        return apiProvider
    }

    private fun responeToMappedData(users: List<User>): ArrayList<MappedData> {
        val mapData: ArrayList<MappedData> = ArrayList()

        for (currentUser in users) {
            mapData.add(MappedData(true, currentUser.name!!, currentUser.image!!))
            mapData.add(MappedData(isHeader = false, url = currentUser.items))
        }

        return mapData
    }
}