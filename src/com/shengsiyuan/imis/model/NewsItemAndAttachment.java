package com.shengsiyuan.imis.model;

import java.util.List;

public class NewsItemAndAttachment {

    public long newsId;
    
    public String newsName;
    
    public String newsContent;
    
    public List<NewsAttachment> list;

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public List<NewsAttachment> getList() {
        return list;
    }

    public void setList(List<NewsAttachment> list) {
        this.list = list;
    }
    
    
}
