package com.ccnu.test;

import com.ccnu.test.validation.ValidationUtils;
import com.ccnu.test.validation.model.FundAccountQueryParam;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//		System.out.println(ValidationUtils.validateEntity(new SimpleEntity("name", new Date(), "6784890@qq.com",
//				"132456", true,null,null)));
//		
//		System.out.println(ValidationUtils.validateProperty(new SimpleEntity("name", new Date(), "6784890@qq.com",
//				"132456", true,null,null),"password"));

        FundAccountQueryParam param = new FundAccountQueryParam();
        param.setClient_id("0200000000");
        param.setTa_acco("");
        param.setCust_type("1");
        param.setId_kind_gb("A");
        System.out.println(ValidationUtils.validateEntity(param));
    }
}
