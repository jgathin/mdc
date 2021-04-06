package com.magnolia.mdc.data;

import com.magnolia.mdc.models.Forms.PostMDCForm;
import org.springframework.data.repository.CrudRepository;

public interface FormRepository extends CrudRepository<PostMDCForm,Integer> {
}
