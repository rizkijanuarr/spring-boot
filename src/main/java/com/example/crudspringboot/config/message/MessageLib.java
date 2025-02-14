package com.example.crudspringboot.config.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageLib {

    private final MessageConfig messageConfig;

    @Autowired
    public MessageLib(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    public String getMitraNotFound() {
        return messageConfig.get("mitra.type.not.found");
    }
}
