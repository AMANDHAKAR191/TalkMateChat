package com.talkmate.aman.messages;

public class UserPersonalChatList {
    String otherUserPublicUid, otherUserPublicUname,chatRoomID, commonEncryptionKey, commonEncryptionIv;
    boolean knowUser, isTyping;
    String LastMessage;

    public UserPersonalChatList() {
    }

    public UserPersonalChatList(String otherUserPublicUid, String otherUserPublicUname, String chatRoomID, String commonEncryptionKey, String commonEncryptionIv, boolean knowUser, boolean isTyping, String lastMessage) {
        this.otherUserPublicUid = otherUserPublicUid;
        this.otherUserPublicUname = otherUserPublicUname;
        this.chatRoomID = chatRoomID;
        this.commonEncryptionKey = commonEncryptionKey;
        this.commonEncryptionIv = commonEncryptionIv;
        this.knowUser = knowUser;
        this.isTyping = isTyping;
        LastMessage = lastMessage;
    }

    public String getOtherUserPublicUid() {
        return otherUserPublicUid;
    }

    public void setOtherUserPublicUid(String otherUserPublicUid) {
        this.otherUserPublicUid = otherUserPublicUid;
    }

    public String getOtherUserPublicUname() {
        return otherUserPublicUname;
    }

    public void setOtherUserPublicUname(String otherUserPublicUname) {
        this.otherUserPublicUname = otherUserPublicUname;
    }

    public String getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(String chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public String getCommonEncryptionKey() {
        return commonEncryptionKey;
    }

    public void setCommonEncryptionKey(String commonEncryptionKey) {
        this.commonEncryptionKey = commonEncryptionKey;
    }

    public String getCommonEncryptionIv() {
        return commonEncryptionIv;
    }

    public void setCommonEncryptionIv(String commonEncryptionIv) {
        this.commonEncryptionIv = commonEncryptionIv;
    }

    public boolean isKnowUser() {
        return knowUser;
    }

    public void setKnowUser(boolean knowUser) {
        this.knowUser = knowUser;
    }

    public boolean isTyping() {
        return isTyping;
    }

    public void setTyping(boolean isTyping) {
        this.isTyping = isTyping;
    }

    public String getLastMessage() {
        return LastMessage;
    }

    public void setLastMessage(String lastMessage) {
        LastMessage = lastMessage;
    }
}
