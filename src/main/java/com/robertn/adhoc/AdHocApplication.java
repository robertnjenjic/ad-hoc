package com.robertn.adhoc;

import com.robertn.adhoc.service.report.impl.repository.CampaignRepository;
import com.robertn.adhoc.service.utils.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Robert Njenjic
 */
@Slf4j
@SpringBootApplication
public class AdHocApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdHocApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CampaignRepository campaignRepository) {
        return args -> DatabaseUtils.fillDatabase(campaignRepository);
    }
}
