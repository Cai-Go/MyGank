package com.wzh.gank.mygank.model;

/**
* @date 创建时间：2017/9/11
* @author 开发者：WZH
* @Description：
 * 收藏数据实体类
*/

public class Favorite {
    private int id;
    private String url;
    private String title;
    private String state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
