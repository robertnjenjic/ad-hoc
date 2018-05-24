package com.robertn.adhoc.service.common;

/**
 * @author Robert Njenjic
 */
public enum Metric {
    IMPRESSIONS("impressions"),
    INTERACTIONS("interactions"),
    CLICKS("clicks"),
    SWIPES("swipes"),
    PINCHES("pinches"),
    TOUCHES("touches"),
    UNIQUE_USERS("uniqueUsers");

    public String field;

    Metric(String field) {
        this.field = field;
    }
}
