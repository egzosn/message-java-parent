package in.egan.message.common.api;


import in.egan.message.common.api.exception.MessageErrorException;


/**
 *   PayErrorExceptionHandler处理器
 * @author  egan
 * @email egzosn@gmail.com
 * @date 2016-6-1 11:33:01
 */
public interface MessageErrorExceptionHandler {

    public void handle(MessageErrorException e);

}
