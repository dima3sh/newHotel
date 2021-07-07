package org.azati.cources.entity;

import java.util.Objects;

public class Request {
    private Integer request_id;
    private String text;
    private Boolean isReady;
    private Employee stuff;
    private Thing thing;

    public Request(){}

    public Request(Integer request_id, String text, Boolean isReady, Employee stuff, Thing thing) {
        this.request_id = request_id;
        this.text = text;
        this.isReady = isReady;
        this.stuff = stuff;
        this.thing = thing;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public void setStuff(Employee stuff) {
        this.stuff = stuff;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public String getText() {
        return text;
    }

    public Boolean getReady() {
        return isReady;
    }

    public Employee getStuff() {
        return stuff;
    }

    public Thing getThing() {
        return thing;
    }

    @Override
    public String toString() {
        return "Request{" +
                "number request=" + request_id +
                "text='" + text + '\'' +
                ", isReady=" + isReady +
                ", stuff=" + stuff +
                ", thing=" + thing +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return request_id.equals(request.request_id) && text.equals(request.text) && isReady.equals(request.isReady)
                && stuff.equals(request.stuff) && thing.equals(request.thing);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(request_id);
        result = 31 * result + text.hashCode();
        result = 31 * result + Boolean.hashCode(isReady);
        result = 31 * result + stuff.hashCode();
        result = 31 * result + thing.hashCode();
        return result;
    }
}
