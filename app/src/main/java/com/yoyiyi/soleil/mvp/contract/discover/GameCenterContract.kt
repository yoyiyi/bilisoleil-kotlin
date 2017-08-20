package com.yoyiyi.soleil.mvp.contract.discover

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.discover.GameCenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:游戏中心Contract
 */

interface GameCenterContract {

    interface View : BaseContract.BaseView {

        fun showGameCenter(gameCenter: GameCenter)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getGameCenterData()
    }
}
