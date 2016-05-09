package com.ccnu.test.webtest.dao;

import com.ccnu.test.webtest.model.APIDescriptor;
import com.ccnu.test.webtest.model.APIField;

import java.util.List;

public interface APIDao {

	void insertAPIDescriptor(APIDescriptor apiDescriptor);

	void insertAPIFields(List<APIField> apiFields);
}
