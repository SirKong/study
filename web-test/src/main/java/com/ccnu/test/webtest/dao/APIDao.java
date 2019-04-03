package com.ccnu.test.webtest.dao;

import java.util.List;

import com.ccnu.test.webtest.model.APIDescriptor;
import com.ccnu.test.webtest.model.APIField;

public interface APIDao {

    void insertAPIDescriptor(APIDescriptor apiDescriptor);

    void insertAPIFields(List<APIField> apiFields);
}
