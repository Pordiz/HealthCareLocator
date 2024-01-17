package com.example.locatorappforhealthcare;

public class ChatMessage {
    public enum Type { NORMAL, OPTION, USER, }

    private String message;
    private Type type;

    public ChatMessage(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    // Getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

}
