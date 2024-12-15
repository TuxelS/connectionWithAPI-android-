package com.example.appwithsomeapijava.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Joke {

    @JsonProperty("error")
    private boolean error;

    @JsonProperty("category")
    private String category;

    @JsonProperty("type")
    private String type;

    @JsonProperty("setup")
    private String setup;

    @JsonProperty("delivery")
    private String delivery;

    @JsonProperty("flags")
    private Map<String,Boolean> flags;

    @JsonProperty("id")
    private int id;

    @JsonProperty("safe")
    private boolean safe;

    public Joke(boolean error, String category, String type, String setup, String delivery,
                Map<String, Boolean> flags, int id, boolean safe, String lang) {
        this.error = error;
        this.category = category;
        this.type = type;
        this.setup = setup;
        this.delivery = delivery;
        this.flags = flags;
        this.id = id;
        this.safe = safe;
        this.lang = lang;
    }

    @JsonProperty("lang")
    private String lang;

    public boolean isError() {
        return error;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public Map<String, Boolean> getFlags() {
        return flags;
    }

    public int getId() {
        return id;
    }

    public boolean isSafe() {
        return safe;
    }

    public String getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "error=" + error +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", setup='" + setup + '\'' +
                ", delivery='" + delivery + '\'' +
                ", flags=" + flags +
                ", id=" + id +
                ", safe=" + safe +
                ", lang='" + lang + '\'' +
                '}';
    }
}
