package in.egan.message.common.utils;

import in.egan.message.common.api.MessageErrorExceptionHandler;
import in.egan.message.common.api.exception.MessageErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * LogExceptionHandler 日志处理器
 * @author  egan
 * @email egzosn@gmail.com
 * @date 2016-6-1 11:28:01
 */
public class LogExceptionHandler implements MessageErrorExceptionHandler {

    protected static final Logger log = LoggerFactory.getLogger(LogExceptionHandler.class);

    @Override
    public void handle(MessageErrorException e) {
        log.error("Error happens", e);

    }

}
