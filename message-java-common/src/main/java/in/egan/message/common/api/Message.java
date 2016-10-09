package in.egan.message.common.api;

import in.egan.message.common.enums.MessageEvent;
import in.egan.message.common.enums.MessageType;

import java.sql.Timestamp;
import java.util.List;

/**
 * 消息信息
 *  @author: egan
 *  @email egzosn@gmail.com
 *  @date 2016/10/8 9:08
 *
 */
public class Message {

    private String sender;
    private String receiver;
    private List<MessageType> msgTypes;
    private MessageEvent event;
    private String eventKey;
    private String title;
    private String subject;
    private String description;
    private String content;
    private Timestamp createTime;
    private Long msgId;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<MessageType> getMsgTypes() {
        return msgTypes;
    }

    public void setMsgTypes(List<MessageType> msgTypes) {
        this.msgTypes = msgTypes;
    }

    public MessageEvent getEvent() {
        return event;
    }

    public void setEvent(MessageEvent event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", msgTypes=" + msgTypes +
                ", event=" + event +
                ", eventKey='" + eventKey + '\'' +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", msgId=" + msgId +
                '}';
    }

    public Message() {
    }

    public Message(String sender, String receiver, List<MessageType> msgTypes, String title, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.msgTypes = msgTypes;
        this.title = title;
        this.content = content;
    }
}
