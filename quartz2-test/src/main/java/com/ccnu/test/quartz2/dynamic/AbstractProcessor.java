package com.ccnu.test.quartz2.dynamic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by gongyb08837 on 2016/1/10.
 */
public abstract class AbstractProcessor implements ApplicationListener<ContextRefreshedEvent>{
    protected  static final Log log = LogFactory.getLog(AbstractProcessor.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug(String.format("开始执行spring容器刷新完成事件监听方法"));
        try {
            execute(event.getApplicationContext());
        }catch (Exception e){
            log.error(String.format("执行spring容器刷新完成事件监听方法遇到异常"+e));
        }finally {
            log.debug(String.format("结束执行spring容器刷新完成事件监听方法"));
        }
    }

    protected abstract void execute(ApplicationContext applicationContext) throws Exception;
}
