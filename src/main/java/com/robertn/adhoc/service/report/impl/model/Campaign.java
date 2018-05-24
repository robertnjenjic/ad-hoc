package com.robertn.adhoc.service.report.impl.model;

import com.robertn.adhoc.service.common.StandardEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Robert Njenjic
 */
@Entity
@Getter
@Setter
public class Campaign extends StandardEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String campaignId;
    private String campaignName;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Advertisement> advertisements;
}
