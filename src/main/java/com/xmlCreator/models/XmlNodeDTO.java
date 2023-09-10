package com.xmlCreator.models;

import jakarta.persistence.*;

import java.util.List;


@Entity(name = "Node")
public class XmlNodeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    @Column(name = "Parent",nullable = false)
    public Long Parent;

    @Column(name = "Name",nullable = false)
    public String Name;

    @Column(name = "Type",nullable = false)
    public Long Type;

    @Column(name = "collection_type")
    public Long collectionType;




    @Override
    public String toString() {
        return "XmlNodeDTO{" +
                "Id=" + Id +
                ", Parent=" + Parent +
                ", Name='" + Name + '\'' +
                ", Type=" + Type +
                ", CollectionType=" + collectionType +
                '}';
    }
}
