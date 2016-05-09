package com.ccnu.webx.test.app.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.ccnu.webx.test.app.Visitor;


/**
 * Created by 龚元宝 on 2016/5/6.
 */
public class LoginAction {
    public void doCheck(@FormGroup("login") Visitor param, Navigator nav, Context context) {
        String name = param.getName();
        String passwd = param.getPasswd();
        if ("taobao".equals(name) && "hello123".equals(passwd)) {
            nav.redirectTo("appLink").withTarget("form/welcome").withParameter("name", name);
        } else {
            context.put("errorMsg", "Name or Password is invalid.");
        }
    }
}
