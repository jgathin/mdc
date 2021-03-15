package com.magnolia.mdc.data;

import com.magnolia.mdc.models.toolModels.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends CrudRepository<Tool, Integer> {
}
