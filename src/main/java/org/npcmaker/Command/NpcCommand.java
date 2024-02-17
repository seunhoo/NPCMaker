package org.npcmaker.Command;

import java.util.HashMap;
import java.util.Map;

public enum NpcCommand {
    COMMAND_REGEX("/"),
    COMMAND_NM("nm");
    private final String message;


    NpcCommand(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    private static final Map<String, NpcCommand> messageInfoMap = new HashMap<>();

    static {
        for (NpcCommand npcCommand : NpcCommand.values()) {
            messageInfoMap.put(npcCommand.message, npcCommand);
        }
    }
    public static NpcCommand getByMessage(String message) {
        return messageInfoMap.getOrDefault(message,null);
    }
}
