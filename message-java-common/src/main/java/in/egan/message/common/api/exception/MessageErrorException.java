package in.egan.message.common.api.exception;

/**
 *  消息异常
 *  @author: egan
 *  @email egzosn@gmail.com
 *  @date 2016/10/8 9:06
 */
public class MessageErrorException extends RuntimeException {

    private int code;
    private String message;

    public MessageErrorException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
