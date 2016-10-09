package in.egan.message.common.api;

import in.egan.message.common.api.exception.MessageErrorException;

import java.util.Map;

/**
 * 消息服务拦截器
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/9/28 18:07
 */
public interface MessageInterceptor {
    /**
     * 拦截微信消息
     *
     * @param message
     * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @return true代表OK，false代表不OK
     */
    public boolean intercept(Message message,
                             Map<String, Object> context
    ) throws MessageErrorException;

}
