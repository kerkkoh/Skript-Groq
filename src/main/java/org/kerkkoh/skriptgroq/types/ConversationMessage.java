package org.kerkkoh.skriptgroq.types;

public class ConversationMessage {

    public String role;
    public String content;

    @Override
    public String toString() {
        return "{\"role\": \"" + role + "\", \"content\": \"" + content + "\"}";
    }

}
