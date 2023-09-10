package com.xmlCreator.repositories;

import com.xmlCreator.models.XmlNodeDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NodeRepository extends CrudRepository<XmlNodeDTO,Integer> {

}
