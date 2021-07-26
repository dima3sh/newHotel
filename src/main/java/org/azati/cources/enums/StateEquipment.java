package org.azati.cources.enums;

public enum StateEquipment {
    novel(1, "новый"),
    smalldefect(2, "небольшой дефект"),
    broken(3, "сломан"),
    hugedefect(4, "большой дефект"),
    needsreplace(5, "требуется замена");

    private Integer index;
    private String messageText;

    StateEquipment(Integer index, String messageText) {
        this.index = index;
        this.messageText = messageText;
    }

    public Integer getIndex() {
        return index;
    }

    public String getMessageText() {
        return messageText;
    }
}
