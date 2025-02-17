package com.example.crudspringboot.base.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageLib {

    private final MessageConfig messageConfig;

    @Autowired
    public MessageLib(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    public String getMitraNameCantNull() {
        return messageConfig.get("mitra.name.cannot.be.null");
    }

    public String getMitraAddressCantNull() {
        return messageConfig.get("mitra.address.cannot.be.null");
    }

    public String getMitraTypeCantNull() {
        return messageConfig.get("mitra.type.cannot.be.null");
    }

    public String getMitraNotFound() {
        return messageConfig.get("mitra.not.found");
    }

    public String getFarmerNotFound() {
        return messageConfig.get("farmer.not.found");
    }
}
