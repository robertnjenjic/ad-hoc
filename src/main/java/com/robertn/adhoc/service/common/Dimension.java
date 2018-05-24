package com.robertn.adhoc.service.common;

/**
 * @author Robert Njenjic
 */
public enum Dimension {
    DATE("date"),
    CAMPAIGN_ID("campaignId"),
    CAMPAIGN_NAME("campaignName"),
    AD_ID("adId"),
    AD_NAME("adName");

    public String field;

    Dimension(String field) {
        this.field = field;
    }
}
