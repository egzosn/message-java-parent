/*
 * Copyright 2002-2017 the original huodull or egan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




package in.egan.message.common.api;

import in.egan.message.common.enums.MessageType;

/**
 * @date 2016/9/26 11:56
 * @author: egan
 */
public interface MessageConfigStorage {

    /**
     * 获取消息账号
     * 主要为 短信
     * @return
     */
    String getAccount();

    /**
     * 钥匙 与 {@link #getAccount()} 类似
     * app 消息推送
     * @return
     */
    String getKey();

    /**
     * 密码
     * @return
     */
    String getMasterSecret();

    /**
     * 请求地址
     * @return
     */
    String getUri();

    /**
     * 发送状态
      * @return
     */
    boolean getNeedStatus();

    /**
     * 扩展字段
     * @return
     */
    String getExtno();

    /**
     * 是否为测试
     * @return
     */
    boolean isTest();
    /**
     * 消息类型
     * @return
     */
    MessageType getMessageType();


}
