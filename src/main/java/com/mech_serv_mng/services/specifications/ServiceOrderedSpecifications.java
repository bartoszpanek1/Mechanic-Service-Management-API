package com.mech_serv_mng.services.specifications;

import com.mech_serv_mng.models.ServiceOrdered;
import com.mech_serv_mng.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ServiceOrderedSpecifications {
    public static Specification<ServiceOrdered> priceBetween(Double low, Double high) {
        return (root, query, builder) -> builder.between(root.get("price"), low, high);
    }

    public static Specification<ServiceOrdered> descriptionContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("description"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<ServiceOrdered> completedEquals(Boolean expression) {
        return (root, query, builder) -> builder.equal(root.get("completed"), expression);

    }
}