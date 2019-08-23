package com.chi.assigmentandroidpapin.ui.acitivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chi.assigmentandroidpapin.R
import com.chi.assigmentandroidpapin.base.activ.BaseActivity
import com.chi.assigmentandroidpapin.net.model.User
import com.chi.assigmentandroidpapin.ui.adpter.ImageBaseAdapter
import com.chi.assigmentandroidpapin.utils.PagingListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter, MainView>(), MainView {

    companion object {
        fun start(context: Context) {
            Intent(context, MainActivity::class.java)
                .apply {
                    context.startActivity(this)
                }
        }
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val adapter = ImageBaseAdapter(this)
    private var pagingListener = ScrollListener()

    override fun providePresenter(): MainPresenter = MainPresenterImpl(getApiProvider())

    override fun provideLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        presenter.getImages(10)
    }

    private fun init() {
        linearLayoutManager = LinearLayoutManager(this)
        rvImages.layoutManager = linearLayoutManager
        rvImages.adapter = adapter
        pagingListener.attach(rvImages)
    }

    private inner class ScrollListener : PagingListener() {
        var page: Int = 10

        override fun lastItemVisible() {
            page += 10
            pagingListener.setPaused(true)
            presenter.getImages(page)
        }
    }

    override fun showImages(users: List<User>) {
        adapter.setData(users)
    }

    override fun buttonClickListener() {

    }
}
