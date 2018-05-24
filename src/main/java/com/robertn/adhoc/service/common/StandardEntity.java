package com.robertn.adhoc.service.common;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Robert Njenjic
 */
@Getter
@Setter
@MappedSuperclass
public abstract class StandardEntity implements Serializable {

    protected Date date;

    protected StandardEntity(){}

    @PrePersist
    protected void onCreated() {
        if (date == null) {
            date = new Date(new java.util.Date().getTime());
        }
    }
}
