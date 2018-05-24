package com.robertn.adhoc.service.report.impl.repository;

import com.robertn.adhoc.service.report.impl.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Robert Njenjic
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String>, JpaSpecificationExecutor {

}
