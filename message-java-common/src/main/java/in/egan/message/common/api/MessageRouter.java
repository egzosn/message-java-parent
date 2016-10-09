package in.egan.message.common.api;

import in.egan.message.common.api.bean.OutMessage;
import in.egan.message.common.utils.LogExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * <pre>
 * 支付消息路由器，通过代码化的配置，把来自支付的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link MessageRouterRule#next()}
 * 3. 规则的结束必须用{@link MessageRouterRule#end()}或者{@link MessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * MessageRouter router = new MessageRouter();
 * router
 *   .rule()
 *       .msgType("App").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将Message交给消息路由器
 * router.route(message);
 * </pre>
 * 消息路由
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/9/30 15:10
 */
public class MessageRouter {
    protected static final Logger log = LoggerFactory.getLogger(MessageRouter.class);

    /**
     * 路由规则
     */
    private final List<MessageRouterRule> rules = new ArrayList<MessageRouterRule>();


    private static final int DEFAULT_THREAD_POOL_SIZE = 100;

    private ExecutorService executorService;

    private MessageErrorExceptionHandler exceptionHandler;


    public MessageRouter() {
        this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
        this.exceptionHandler = new LogExceptionHandler();;
    }


    /**
     * <pre>
     * 设置自定义的 {@link ExecutorService}
     * 如果不调用该方法，默认使用 Executors.newFixedThreadPool(100)
     * </pre>
     * @param executorService
     */
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }



    /**
     * <pre>
     * 设置自定义的{@link MessageErrorExceptionHandler}
     * 如果不调用该方法，默认使用 {@link LogExceptionHandler}
     * </pre>
     * @param exceptionHandler
     */
    public void setExceptionHandler(MessageErrorExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    List<MessageRouterRule> getRules() {
        return this.rules;
    }

    /**
     * 开始一个新的Route规则
     * @return
     */
    public MessageRouterRule rule() {
        return new MessageRouterRule(this);
    }

    /**
     * 处理消息
     * @param message
     */
    public List<OutMessage> route(final Message message) {

        final List<MessageRouterRule> matchRules = new ArrayList<MessageRouterRule>();
        // 收集匹配的规则
        for (final MessageRouterRule rule : rules) {
            if (rule.test(message)) {
                matchRules.add(rule);
                if(!rule.isReEnter()) {
                    break;
                }
            }
        }

        if (matchRules.size() == 0) {
            return null;
        }

        List<OutMessage> res = null;
        final List<Future> futures = new ArrayList<Future>();
        for (final MessageRouterRule rule : matchRules) {
            // 返回最后一个非异步的rule的执行结果
            if(rule.isAsync()) {
                futures.add(
                        executorService.submit(new Runnable() {
                            public void run() {
                                rule.service(message, exceptionHandler);
//                                executorService.shutdown();
                            }
                        })

                );
            } else {
                res = rule.service(message, exceptionHandler);
                // 在同步操作结束，session访问结束
                log.debug("End session access: async=false, Sender=" + message.getSender());
            }
        }

        if (futures.size() > 0) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (Future future : futures) {
                        try {
                            future.get();
                            log.debug("End session access: async=false, Sender=" + message.getSender());
                        } catch (InterruptedException e) {
                            log.error("Error happened when wait task finish", e);
                        } catch (ExecutionException e) {
                            log.error("Error happened when wait task finish", e);
                        }
                    }
                }
            });
        }
        return res;
    }


}
