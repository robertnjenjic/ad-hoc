package com.robertn.adhoc.service.report.impl.repository;

import com.robertn.adhoc.service.report.impl.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Robert Njenjic
 */
public interface ReportRepository extends JpaRepository<Campaign, String>, ReportRepositoryCustom {

}
