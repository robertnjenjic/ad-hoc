package com.robertn.adhoc.service.report.impl.model;

import com.robertn.adhoc.service.common.StandardEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Robert Njenjic
 */
@Entity
@Getter
@Setter
public class Advertisement extends StandardEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String adId;
    private String adName;

    @ManyToOne
    private Campaign campaign;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;
}
