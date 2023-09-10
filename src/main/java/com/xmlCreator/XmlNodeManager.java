package com.xmlCreator;

import com.xmlCreator.models.XmlNode;
import com.xmlCreator.models.XmlNodeDTO;

import java.util.*;

public class XmlNodeManager {
    private final List<XmlNode> nodes;

    private HashSet<XmlNode> set;
    private XmlNode root;

    public XmlNodeManager(List<XmlNode> nodes) throws Exception {
        this.nodes = nodes;
        nodes.sort(Comparator.comparingInt(a -> a.Parent.intValue()));
        this.set = new HashSet<>();

        updateNodes();
        updateSet();

    }

    private void updateSet() {
        this.set.addAll(this.nodes);
    }

    public XmlNode getXml() throws Exception {
        return getNodeByParentId(-1L);

    }





    private void updateNodes() throws Exception {
        for (XmlNode node : this.nodes) {
            if(node.Parent.equals(-1L)) continue;
            XmlNode parent = getNodeById(node.Parent);
            parent.children.add(node);
        }
    }

    private XmlNode getNodeById(Long id) throws Exception {
        for (XmlNode node : this.nodes) {
            if (node.Id.equals(id))
                return node;
        }
        throw new Exception("No node with id : " + id + "is present");
    }

    private XmlNode getNodeByParentId(Long id) throws Exception {
        for (XmlNode node : this.nodes) {
            if (node.Parent == id)
                return node;
        }
        throw new Exception("No node with id : " + id + "is present");
    }
}
