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



package in.egan.message.test.api.config;

import in.egan.message.common.api.MessageConfigStorage;
import in.egan.message.common.enums.MessageType;

/**
 * 网站消息的系统配置
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/10/09 14:56
 */
public class WebMessageConfigStorage implements MessageConfigStorage {

     private MessageType messageType;


    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String getAccount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getKey() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getMasterSecret() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getUri() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getNeedStatus() {
        return false;
    }

    @Override
    public String getExtno() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTest() {
        return false;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    
    
}
