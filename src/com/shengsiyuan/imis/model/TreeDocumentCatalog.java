package com.shengsiyuan.imis.model;

import java.util.List;

public class TreeDocumentCatalog {

    private DataAttribute data;
    
    private String state;
    
    private List<TreeDocumentCatalog> children;

    public DataAttribute getData() {
        return data;
    }

    public void setData(DataAttribute data) {
        this.data = data;
    }
   
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TreeDocumentCatalog> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDocumentCatalog> children) {
        this.children = children;
    }
}
