package com.xmlCreator.models;


import java.util.ArrayList;
import java.util.List;

public class XmlNode implements Comparable<XmlNode> {
    public Long Id;
    public Long Parent;
    public String Name;
    public NodeType Type;
    public NodeType collectionType;
    public List<XmlNode> children;

    public XmlNode(){
        this.children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "XmlNode{" +
                "Id=" + Id +
                ", Parent=" + Parent +
                ", Name='" + Name + '\'' +
                ", Type=" + Type +
                ", collectionType=" + collectionType +
                ", children=" + children +
                '}';
    }

    @Override
    public int compareTo(XmlNode o) {
        return (int) (this.Id - o.Id);
    }
}
