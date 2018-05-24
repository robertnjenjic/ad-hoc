package com.robertn.adhoc.service.utils;

import com.robertn.adhoc.service.report.impl.model.Advertisement;
import com.robertn.adhoc.service.report.impl.model.Campaign;
import com.robertn.adhoc.service.report.impl.model.Impression;
import com.robertn.adhoc.service.report.impl.model.Impression.Type;
import com.robertn.adhoc.service.report.impl.model.User;
import com.robertn.adhoc.service.report.impl.repository.CampaignRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Robert Njenjic
 */
@Slf4j
public class DatabaseUtils {

    private static final Integer MIN = 0;
    private static final Integer HOURS = 504;

    /**
     * Generates random/fake data
     * @param campaignRepository
     */
    public static void fillDatabase(CampaignRepository campaignRepository) {
        log.info("H2 - Creating some data...");

        Random random = new Random();

        // Hours - 504 hours == 21 days
        IntStream.range(0, HOURS).forEach(i -> {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(Calendar.HOUR, -(HOURS - i));
            Date date = new Date(calendar.getTime().getTime());

            IntStream.range(MIN, random.nextInt(3) + 1).forEach(j -> {
                Campaign campaign = new Campaign();
                campaign.setDate(date);
                campaign.setCampaignName("Campaign name " + i);
                campaign.setAdvertisements(new ArrayList<>());

                IntStream.range(MIN, random.nextInt(5) + 1).forEach(k -> {
                    Advertisement advertisement = new Advertisement();
                    advertisement.setDate(date);
                    advertisement.setAdName("Ad name " + (i + j + k));
                    advertisement.setCampaign(campaign);
                    advertisement.setUsers(new ArrayList<>());

                    IntStream.range(MIN, random.nextInt(5) + 1).forEach(l -> {
                        User user = new User();
                        user.setDate(date);
                        user.setUserFirstName("FirstName " + (i + l));
                        user.setUserLastName("LastName" + (i + l));
                        user.setAdvertisements(new ArrayList<>());
                        user.setImpressions(new ArrayList<>());
                        user.getAdvertisements().add(advertisement);

                        IntStream.range(MIN, random.nextInt(50) + 1).forEach(m -> {
                            Impression impression = new Impression();
                            impression.setDate(date);

                            switch (random.nextInt(4) * 1) {
                                case 0:
                                    impression.setType(Type.CLICK);
                                    break;
                                case 1:
                                    impression.setType(Type.PINCH);
                                    break;
                                case 2:
                                    impression.setType(Type.SWIPE);
                                    break;
                                case 3:
                                    impression.setType(Type.TOUCH);
                                    break;
                                default:
                                    break;
                            }

                            user.getImpressions().add(impression);
                        });

                        advertisement.getUsers().add(user);
                    });

                    campaign.getAdvertisements().add(advertisement);
                });

                campaignRepository.save(campaign);

                log.info("{}", campaign);
            });
        });

        log.info("H2 - Data created");
    }

    private DatabaseUtils() {
    }
}
