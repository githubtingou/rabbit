package com.ting.rabbit;

import com.ting.rabbit.Config.RabbitConfig;
import com.ting.rabbit.Config.SnowflakeIdWorker;
import com.ting.rabbit.common.RabbitConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitApplicationTests {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Test
    void contextLoads() {
        // 消息发送
        this.rabbitConfig.sendMsg(
                RabbitConstant.Ting.TING_EXCHANGE,
                RabbitConstant.Ting.TING_KEY,
                String.valueOf(SnowflakeIdWorker.getId()));
    }

}
