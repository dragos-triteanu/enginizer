package com.startup.enginizer.datarest.repository;

import com.startup.enginizer.model.entities.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Dragos on 2/24/2016.
 */
@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer >{

    List<Item> findByName(@Param("name") String name);
}
