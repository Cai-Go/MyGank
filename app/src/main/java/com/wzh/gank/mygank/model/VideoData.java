package com.wzh.gank.mygank.model;

import java.util.List;

/**
 * Created by WZH on 2017/9/6.
 */

public class VideoData {

    /**
     * error : false
     * results : [{"_id":"59aac9eb421aa901c1c0a8d3","createdAt":"2017-09-02T23:10:35.104Z","desc":"【爆首映】感谢诺兰，到现在我的心仍然抖得发烫《敦刻尔克》牛逼","publishedAt":"2017-09-05T11:29:05.240Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av14066619/","used":true,"who":"LHF"},{"_id":"59a59510421aa901c85e5fdf","createdAt":"2017-08-30T00:23:44.513Z","desc":"男女之间有没有纯友谊？","publishedAt":"2017-09-01T12:55:52.582Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/f0d24a391c5243639778d5e203d58e21?fid=1034:f0d24a391c5243639778d5e203d58e21","used":true,"who":"lxxself"},{"_id":"59a5951d421aa901c1c0a8a9","createdAt":"2017-08-30T00:23:57.421Z","desc":"超甜蜜！日文版告白气球","publishedAt":"2017-08-31T08:22:07.982Z","source":"chrome","type":"休息视频","url":"http://www.miaopai.com/show/YXZX2UNJN~4yT10a~OlMwBwokkGY0ydFYm1ynw__.htm","used":true,"who":"lxxself"},{"_id":"599ee6ae421aa901c1c0a886","createdAt":"2017-08-24T22:46:06.30Z","desc":"【牛叔】几分钟看完奇幻片《黑夜传说：进化》混血狼人大战混血吸血鬼","publishedAt":"2017-08-29T12:19:18.634Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av13755573/","used":true,"who":"LHF"},{"_id":"599d9da3421aa901c1c0a87c","createdAt":"2017-08-23T23:22:11.239Z","desc":"【问舰】一部真实到让你胆颤的科幻灾难片！解读《科洛弗档案》 29","publishedAt":"2017-08-24T12:43:10.124Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av13690750/","used":true,"who":"LHF"},{"_id":"59943f99421aa9672f354dce","createdAt":"2017-08-16T20:50:33.576Z","desc":"【牛叔】几分钟看李连杰好莱坞动作片《宇宙追缉令》","publishedAt":"2017-08-23T12:12:15.166Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av13396416/","used":true,"who":"LHF"},{"_id":"5996ff73421aa967262e1c49","createdAt":"2017-08-18T22:53:39.224Z","desc":"盘点12个你无法相信存在的疯狂手表","publishedAt":"2017-08-22T12:02:15.769Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av6869452/","used":true,"who":"LHF"},{"_id":"59944134421aa96729c57251","createdAt":"2017-08-16T20:57:24.255Z","desc":"【谷阿莫】5分鐘看完2017不要一個人去拉屎的電影《异形：契约 Alien- Covenant》","publishedAt":"2017-08-21T11:38:57.363Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av13399068/","used":true,"who":"LHF"},{"_id":"5991b9d1421aa96729c57245","createdAt":"2017-08-14T22:55:13.926Z","desc":"搞笑GIF锦集101期 ：好喜欢这种污污的女生！","publishedAt":"2017-08-15T13:32:36.998Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av13356763/","used":true,"who":"LHF"},{"_id":"59906c33421aa90f4919c7e1","createdAt":"2017-08-13T23:11:47.518Z","desc":"DC最快的闪电侠，在你看标题时就已跑到银河尽头","publishedAt":"2017-08-14T17:04:50.266Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av13207512/","used":true,"who":"LHF"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59aac9eb421aa901c1c0a8d3
         * createdAt : 2017-09-02T23:10:35.104Z
         * desc : 【爆首映】感谢诺兰，到现在我的心仍然抖得发烫《敦刻尔克》牛逼
         * publishedAt : 2017-09-05T11:29:05.240Z
         * source : chrome
         * type : 休息视频
         * url : http://www.bilibili.com/video/av14066619/
         * used : true
         * who : LHF
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
