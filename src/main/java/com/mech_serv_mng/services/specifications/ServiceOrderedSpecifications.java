package com.mech_serv_mng.services.specifications;

import com.mech_serv_mng.models.ServiceOrdered;
import org.springframework.data.jpa.domain.Specification;

public class ServiceOrderedSpecifications extends Specs {
    public static Specification<ServiceOrdered> priceBetween(Double low, Double high){
        return (root, query, builder) -> builder.between(root.get("price"),low,high);
    }
    public static Specification<ServiceOrdered> descriptionContains(String expression){
        return (root, query, builder) -> builder.like(root.get("description"),preprocess(expression));
    }
    public static Specification<ServiceOrdered> completedEquals(Boolean expression){
        return (root, query, builder) -> builder.equal(root.get("completed"),expression);

    }
}