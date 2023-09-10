package com.xmlCreator.controllers;

import com.xmlCreator.XmlNodeManager;
import com.xmlCreator.mapper.XmlNodeMapper;
import com.xmlCreator.models.XmlNode;
import com.xmlCreator.models.XmlNodeDTO;
import com.xmlCreator.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/nodes")
public class NodeController {
    private final NodeService service;

    @Autowired
    public NodeController(NodeService service) {
        this.service = service;
    }

    @GetMapping(path = "getAll")
    public Iterable<XmlNodeDTO> getNodes(){
        Iterable<XmlNodeDTO> nodes = this.service.findAll();

        return nodes;
    }

    @GetMapping(path = "getXML")
    public XmlNode getXml() throws Exception {
        Iterable<XmlNodeDTO> dtos = getNodes();
        Iterator<XmlNodeDTO> iterator = dtos.iterator();
        List<XmlNode> nodes = new ArrayList<>();
        while (iterator.hasNext()){
            nodes.add(XmlNodeMapper.mapToXmlNode(iterator.next()));
        }
        XmlNodeManager manager = new XmlNodeManager(nodes);
        XmlNode root = manager.getXml();
        return root;
    }

    @GetMapping(path = "getsortednodes")
    public List<XmlNode> getSortedNodes(){
        Iterable<XmlNodeDTO> dtos = getNodes();
        Iterator<XmlNodeDTO> iterator = dtos.iterator();
        List<XmlNode> nodes = new ArrayList<>();
        while (iterator.hasNext()){
            nodes.add(XmlNodeMapper.mapToXmlNode(iterator.next()));
        }

        nodes.sort(Comparator.comparingInt(node -> node.Parent.intValue()));
        return nodes;
    }



}
