package com.xmlCreator.mapper;

import com.xmlCreator.models.NodeType;
import com.xmlCreator.models.XmlNode;
import com.xmlCreator.models.XmlNodeDTO;

import java.util.ArrayList;
import java.util.List;

public class XmlNodeMapper {

    public static List<XmlNode> mapToXmlNode(List<XmlNodeDTO> dtos){
        List<XmlNode> result = new ArrayList<>();
        for(XmlNodeDTO dto : dtos)
            result.add(mapToXmlNode(dto));

        return result;
    }

    public static XmlNode mapToXmlNode(XmlNodeDTO dto){
        XmlNode result = new XmlNode();
        result.Id = dto.Id;
        result.Name = dto.Name;
        result.Parent = dto.Parent;

        result.Type = NodeType.getNodeTypeById(dto.Type.intValue());
        if(result.Type == NodeType.COLLECTION){
            result.collectionType =
                    NodeType.getNodeTypeById(dto.collectionType.intValue());
        }
        return result;
    }


}
