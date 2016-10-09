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
 *  短信消息的系统配置
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/10/09 14:56
 */
public class SMSMessageConfigStorage implements MessageConfigStorage {
        private String uri;
        private String account;
        private String masterSecret;
        private String extno;
        private boolean test = false;
    private MessageType messageType;

    @Override
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public boolean getNeedStatus() {
        throw new UnsupportedOperationException();
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }

    @Override
    public String getKey() {
        throw new UnsupportedOperationException();
    }


    public boolean isTest() {
        return test;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public SMSMessageConfigStorage() {
    }

    public SMSMessageConfigStorage(String uri, String account, String masterSecret, String extno, boolean test, MessageType messageType) {
        this.account = account;
        this.masterSecret = masterSecret;
        this.uri = uri;
        this.extno = extno;
        this.test = test;
        this.messageType = messageType;
    }
}
