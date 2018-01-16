package com.shengsiyuan.imis.model;

import java.util.List;

public class DocumentCatalogTreeModel {

    private String data;

    private List<DocumentCatalogTreeModel> children;
    
    private Attributes attributes;
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<DocumentCatalogTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<DocumentCatalogTreeModel> children) {
        this.children = children;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
    
}
