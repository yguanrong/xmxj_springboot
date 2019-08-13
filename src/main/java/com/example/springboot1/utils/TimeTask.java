package com.example.springboot1.utils;

import com.example.springboot1.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TimeTask {

    @Autowired
    private Service service;


    /**
     * 完整字段：[秒] [分] [小时] [日] [月] [周] [年]
     *
     * 　　字段　　　　　　允许值　　　　　　　　　　　　允许特殊字符
     *
     *  　　秒　　　　　　　0-59　　　　　　　　　　　　　　, - * /
     *
     * 　　分　　　　　　　0-59　　　　　　　　　　　　　　, - * /
     *
     * 　　小时　　　　　　0-23　　　　　　　　　　　　　　, - * /
     *
     * 　　日　　　　　　　1-31　　　　　　　　　　　　　　, - * ? / L W C
     *
     * 　　月　　　　　　　1-12或JAN-DEC　　　　　　　　  , - * /
     *
     * 　　周　　　　　　　1-7或SUN-SAT　　　　　　　　　 , - *  ? / L C #
     *
     * 　　年　　　　　　　留空或1970-2099　　　　　　　 　, - * /
     * */

    //3.添加定时任务 一天
    @Scheduled(cron = "0 0 0 0/1 * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        service.queryDanger1();
        System.out.println(LocalDateTime.now()+"： 执行定时任务每天,检测是否超期 " );
    }

    //3.添加定时任务 一分
    @Scheduled(cron = "0 0/1 * * * ?")
    private void updateItemState(){
        service.updateItemState();
//        System.err.println(LocalDateTime.now()  +  "： 执行定时任务没分,判断item状态" );
    }

    //添加定时任务 每周五
    @Scheduled(cron = "0 0 0 * * 5")
    private void insertReport(){
        service.insertReports();
        System.out.println( LocalDateTime.now()+  "： 执行定时任务 每周五,创建周报 " );
    }

}
