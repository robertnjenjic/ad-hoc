package com.robertn.adhoc.service.report.impl.repository;

import static com.robertn.adhoc.service.common.Dimension.*;

import com.robertn.adhoc.service.common.Dimension;
import com.robertn.adhoc.service.common.Metric;
import com.robertn.adhoc.service.common.TimeRange;
import com.robertn.adhoc.service.report.impl.model.Advertisement;
import com.robertn.adhoc.service.report.impl.model.Campaign;
import com.robertn.adhoc.service.report.impl.model.Impression;
import com.robertn.adhoc.service.report.impl.model.Impression.Type;
import com.robertn.adhoc.service.report.impl.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import org.springframework.stereotype.Repository;

/**
 * @author Robert Njenjic
 */
@Repository
public class ReportRepositoryImpl implements ReportRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object> generateReport(Integer offset, Integer limit, List<Dimension> dimensions, List<Metric> metrics,
            TimeRange timeRange) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Root<Campaign> campaign = cq.from(Campaign.class);

        Join<Campaign, Advertisement> joinAdvertisements = null;
        Join<User, Impression> joinImpressions;
        Join<Advertisement, User> joinUsers;

        List<Selection<?>> selections = new ArrayList<>();
        List<Expression<?>> groupBy = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();

        // Dimensions
        if (dimensions != null && !dimensions.isEmpty()) {
            for (Dimension dimension : dimensions) {
                if (dimension != null) {
                    if (dimension == AD_ID || dimension == AD_NAME) {
                        if (joinAdvertisements == null) {
                            joinAdvertisements = campaign.joinList("advertisements", JoinType.LEFT);
                        }
                        selections.add(joinAdvertisements.get(dimension.field));
                    } else {
                        selections.add(campaign.get(dimension.field));
                    }
                }
            }
        }

        // Group by
        groupBy.addAll(selections.stream()
                . map(select -> (Expression<?>) select)
                .collect(Collectors.toList()));

        // Order by
        List<Order> orders = selections.stream()
                .map(select -> cb.asc((Expression<?>) select))
                .collect(Collectors.toList());


        // Metrics
        if (metrics != null && !metrics.isEmpty()) {
            if (joinAdvertisements == null) {
                joinAdvertisements = campaign.joinList("advertisements", JoinType.LEFT);
            }

            joinUsers = joinAdvertisements.joinList("users", JoinType.LEFT);
            joinImpressions = joinUsers.joinList("impressions", JoinType.LEFT);

            for (Metric metric : metrics) {
                if (metric != null) {
                    if (metric == Metric.CLICKS) {
                        selections.add(getType(cb, joinImpressions, Type.CLICK));
                    } else if (metric == Metric.SWIPES) {
                        selections.add(getType(cb, joinImpressions, Type.SWIPE));
                    } else if (metric == Metric.PINCHES) {
                        selections.add(getType(cb, joinImpressions, Type.PINCH));
                    } else if (metric == Metric.TOUCHES) {
                        selections.add(getType(cb, joinImpressions, Type.TOUCH));
                    } else if (metric == Metric.UNIQUE_USERS) {
                        selections.add(cb.countDistinct(joinUsers.get("userId")));
                    } else if (metric == Metric.IMPRESSIONS) {
                        selections.add(cb.countDistinct(joinImpressions));
                    } else if (metric == Metric.INTERACTIONS) {
                        Expression<Integer> typeTouch = getType(cb, joinImpressions, Type.TOUCH);
                        Expression<Integer> typePinch = getType(cb, joinImpressions, Type.PINCH);
                        Expression<Integer> typeSwipe = getType(cb, joinImpressions, Type.SWIPE);

                        selections.add(cb.sum(typePinch, cb.sum(typeSwipe, typeTouch)));
                    }
                }
            }
        }

        // Where
        if (timeRange != null)
        {
            Date from = timeRange.getFrom();
            Date to = timeRange.getTo();

            if (from != null && to != null) {
                predicates.add(cb.between(campaign.get("date"), from, to));
            }
        }

        // Creating query
        cq.multiselect(selections);
        cq.where(predicates.toArray(new Predicate[]{}));
        cq.groupBy(groupBy);
        cq.orderBy(orders);

        TypedQuery<Object> tq = em.createQuery(cq);

        if (offset != null) {
            tq.setFirstResult(offset);
        }

        if (limit != null) {
            tq.setMaxResults(limit);
        }

        return tq.getResultList();
    }

    private Expression<Integer> getType(CriteriaBuilder cb, Join<User, Impression> joinImpressions, Type type) {
        return cb.sum(cb.<Integer>selectCase()
                .when(cb.equal(joinImpressions.get("type"), type), 1)
                .otherwise(0));
    }
}
