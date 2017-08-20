package com.yoyiyi.soleil.event

import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment
import com.yoyiyi.soleil.bean.user.UpDetail

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/6/4 15:24
 *  描述:事件
 */
object Event {
    class RegionEntrancePositionEvent {
        var position: Int = -1
    }

    class StartNavigationEvent {
        var start: Boolean = false
    }

    class VideoDetailEvent {
        var videoDetail: VideoDetail.DataBean? = null
    }

    class VideoDetailCommentEvent {
        var videoDetailComment: VideoDetailComment.DataBean? = null
    }

    class UpDetailArchiveEvent {
        var archive: UpDetail.DataBean.ArchiveBean? = null
        var setting: UpDetail.DataBean.SettingBean? = null
        var favourite: UpDetail.DataBean.FavouriteBean? = null
        var live: UpDetail.DataBean.LiveBean? = null

    }

    class UpDetailSubmitedVideoEvent {
        var archivList: List<UpDetail.DataBean.ArchiveBean.ItemBean>? = null
    }

    class UpDetailFavourteEvent {
        var favouriteList: List<UpDetail.DataBean.FavouriteBean.ItemBeanX>? = null
    }

    /*class SearchArchiveEvent {
        var itemBean: Search.DataBean.ItemsBean? = null
        var seasonCount: Int = 0
        var movieCount: Int = 0
    }*/

}


/* public static class RegionEntrancePositionEvent {
        public int position;
    }

    public static class AllStationPositionEvent {
        public int position;
    }

    public static class StartNavigationEvent {
        public boolean start;
    }

    public static class VideoDetailEvent {
        public VideoDetail.DataBean videoDetail;
    }

    public static class VideoDetailCommentEvent {
        public VideoDetailComment.DataBean videoDetailComment;
    }

    public static class UpDetailArchiveEvent {
        public UpDetail.DataBean.ArchiveBean archive;
        public UpDetail.DataBean.SettingBean setting;
        public UpDetail.DataBean.FavouriteBean favourite;
        public UpDetail.DataBean.LiveBean live;

    }

    public static class UpDetailSubmitedVideoEvent {
        public List<UpDetail.DataBean.ArchiveBean.ItemBean> archivList;
    }

    public static class UpDetailFavourteEvent {
        public List<UpDetail.DataBean.FavouriteBean.ItemBeanX> favouriteList;
    }

    public static class SearchArchiveEvent {
        public Search.DataBean.ItemsBean itemBean;
        public int seasonCount;
        public int movieCount;
    }*/
