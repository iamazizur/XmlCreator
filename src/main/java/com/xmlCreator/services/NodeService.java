package com.xmlCreator.services;

import com.xmlCreator.models.XmlNodeDTO;
import com.xmlCreator.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NodeService {
    private final NodeRepository repository;
    @Autowired
    public NodeService(NodeRepository repository) {
        this.repository = repository;
    }

    public Iterable<XmlNodeDTO> findAll(){
        return this.repository.findAll();
    }
    public Optional<XmlNodeDTO> findById(Integer id){
        return this.repository.findById(id);
    }
}
