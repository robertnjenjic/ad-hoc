package com.robertn.adhoc.service.report.impl.model;

import com.robertn.adhoc.service.common.StandardEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Robert Njenjic
 */
@Entity
@Getter
@Setter
public class Impression extends StandardEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    protected String impressionId;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        SWIPE,
        PINCH,
        TOUCH,
        CLICK
    }
}
