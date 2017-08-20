package com.yoyiyi.soleil.module.app.video

import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.app.video.CommentAdapter
import com.yoyiyi.soleil.base.BaseInjectFragment
import com.yoyiyi.soleil.bean.app.video.MulComment
import com.yoyiyi.soleil.mvp.contract.app.video.CommentContract
import com.yoyiyi.soleil.mvp.presenter.app.video.CommentPresenter
import kotlinx.android.synthetic.main.common_recycler.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/14 15:58
 * * 描述:评论
 */

class CommentFragment : BaseInjectFragment<CommentPresenter>(), CommentContract.View {


    private var mAdapter: CommentAdapter? = null
    private val mList = mutableListOf<MulComment>()

    override fun getLayoutId(): Int = R.layout.fragment_comment

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        mPresenter.getCommentData()
    }


    override fun initWidget() {
        initRecyclerView()
    }

    override fun initRecyclerView() {
        mAdapter = CommentAdapter(mList)
        recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext)
        recycler.layoutManager = layoutManager
        recycler.adapter = mAdapter
    }

    override fun showComment(mulComments: List<MulComment>) {
        mList.addAll(mulComments)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): CommentFragment {
            return CommentFragment()
        }
    }
}
