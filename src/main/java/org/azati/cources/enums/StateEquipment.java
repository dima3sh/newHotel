package org.azati.cources.enums;

public enum StateEquipment {
    NOVEL(1, "новый"),
    SMALLDEFECT(2, "небольшой дефект"),
    BROKEN(3, "сломан"),
    HUGEDEFECT(4, "большой дефект"),
    NEEDSREPLACE(5, "требуется замена");

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
