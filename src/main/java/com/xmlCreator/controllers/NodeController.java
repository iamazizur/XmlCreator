package com.xmlCreator.controllers;

import com.xmlCreator.XmlNodeManager;
import com.xmlCreator.mapper.XmlNodeMapper;
import com.xmlCreator.models.XmlNode;
import com.xmlCreator.models.XmlNodeDTO;
import com.xmlCreator.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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




    @PostMapping(path = "setData")
    public String[] setData(@RequestBody Getter value){

        Optional<XmlNodeDTO> dto = this.service.findById(value.nodeId );
        XmlNode node =
                XmlNodeMapper.mapToXmlNode(dto.get());

        Class<?> cl1 = value.nodeId.getClass();
        Class<?> cl2 = value.value.getClass();
        System.out.println(value.nodeId.getClass());
        return new String[]{
                value.nodeId.getClass().toString(),
                value.value.getClass().toString(),
                node.toString()
        };
    }

}
class Getter{
    public Integer nodeId;
    public Object value;
}