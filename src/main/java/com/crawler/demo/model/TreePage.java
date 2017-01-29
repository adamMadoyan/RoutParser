package com.crawler.demo.model;

public class TreePage {

    private String id;
    private String parent;
    private String type;
    private String text;
    private State state;
    private Attribute a_attr;


    public TreePage(String id, String parent, String text) {
        this(id, parent, text, false);
    }

    public TreePage(String id, String parent, String text, boolean state) {
        this.id = id;
        this.parent = parent;
        this.type = id.equals("0") ? "folder" : "";
        this.text = text == null || text.isEmpty() ? "NULL" : text;
        this.state = new State(state);
        this.a_attr = new Attribute(text);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Attribute getA_attr() {
        return a_attr;
    }

    public void setA_attr(Attribute a_attr) {
        this.a_attr = a_attr;
    }
}

class State {

    private boolean opened;

    public State(boolean opened) {
        this.opened = opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isOpened() {
        return opened;
    }
}

class Attribute {

    private String href;

    public Attribute(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
