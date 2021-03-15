package com.magnolia.mdc.data;

import com.magnolia.mdc.models.partModels.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends CrudRepository<Part, Integer> {
}
