package com.xmlCreator.models;

import jakarta.persistence.Entity;


public enum NodeType {
    COLLECTION(1),
    DATETIME(2),
    DECIMAL(3),
    INTEGER(4),
    BOOLEAN(5),
    STRING(6),
    NODE(7);

    private final int index;

    public int getIndex() {
        return index;
    }

    NodeType(int index){
        this.index = index;
    }

    public static NodeType getNodeTypeById(int Id){
        return values()[Id-1];
    }
}
