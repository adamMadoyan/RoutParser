package com.crawler.demo.model;

public class TreePage {

    // page id
    private String id;
    // parent page id
    private String parent;
    // type of node
    private String type;
    // text of node
    private String text;
    // stat of node (opened)
    private State state;
    // attribute of node
    private Attribute a_attr;


    /**
     * Constructor
     */
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

    /**
     * Getter for id.
     *
     * @return doc id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id doc id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for parent doc id
     *
     * @return parent doc id
     */
    public String getParent() {
        return parent;
    }

    /**
     * Setter for parent doc id
     *
     * @param parent doc id
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * Getter for type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type tyoe
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for text
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text
     *
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for state
     *
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for state
     *
     * @param state state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter for attribute
     *
     * @return attribute
     */
    public Attribute getA_attr() {
        return a_attr;
    }

    /**
     * Setter for attribute
     *
     * @param a_attr attribute
     */
    public void setA_attr(Attribute a_attr) {
        this.a_attr = a_attr;
    }
}

/**
 * State of node
 */
class State {

    // state of node opened or closed
    private boolean opened;

    public State(boolean opened) {
        this.opened = opened;
    }

    /**
     * Setter for opened
     *
     * @param opened true if node will be opened
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    /**
     * Is opened node
     *
     * @return true if node is opened
     */
    public boolean isOpened() {
        return opened;
    }
}

/**
 * Attribute of node
 */
class Attribute {

    // href of node
    private String href;

    public Attribute(String href) {
        this.href = href;
    }

    /**
     * Getter for href
     *
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * Setter of href
     *
     * @param href href
     */
    public void setHref(String href) {
        this.href = href;
    }
}
