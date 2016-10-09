package in.egan.message.common.api.handler;

import in.egan.message.common.api.MessageConfigStorage;
import in.egan.message.common.api.MessageHandler;

import java.util.List;

/**
 *  网站观察者
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/10/9 10:37
 */
public abstract class BaseMessageHandler implements MessageHandler{
    /**
     * 配置信息
     */
   private List<MessageConfigStorage> configStorages;

    public void configStorage(List<MessageConfigStorage> configStorages){
        this.configStorages = configStorages;
    }

    public List<MessageConfigStorage> getConfigStorages() {
        return configStorages;
    }
}
