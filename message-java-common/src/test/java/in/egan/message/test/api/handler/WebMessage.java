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





package in.egan.message.test.api.handler;


import in.egan.message.common.api.Message;
import in.egan.message.common.api.bean.OutMessage;
import in.egan.message.common.api.exception.MessageErrorException;
import in.egan.message.common.api.handler.BaseMessageHandler;

import java.util.Map;

/**
 *  网站观察者
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/10/09 14:56
 */
public class WebMessage extends BaseMessageHandler {
    @Override
    public OutMessage handle(Message message, Map<String, Object> context) throws MessageErrorException {
        System.out.println("Web 推送:" + message);
        return null;
    }


}
