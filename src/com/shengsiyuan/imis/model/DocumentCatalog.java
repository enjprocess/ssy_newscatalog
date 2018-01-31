package com.shengsiyuan.imis.model;

public class DocumentCatalog {

    private long id;
    
    private String name;
    
    //1-5标示5种文档，添加文档的时候，需要type和parentId才知道添加到哪里
    private long type;
    
    private boolean leaf;
    
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    private long parentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
    
    
    
}
