package com.mech_serv_mng.services.specifications;

import com.mech_serv_mng.models.Car;
import org.springframework.data.jpa.domain.Specification;

public final class CarSpecifications extends Specs {
    public static Specification<Car> brandContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("brand"), preprocess(expression));
    }

    public static Specification<Car> modelContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("model"), preprocess(expression));
    }

    public static Specification<Car> colorContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("color"), preprocess(expression));
    }

    public static Specification<Car> regNumEquals(String expression) {
        return (root, query, builder) -> builder.equal(root.get("regNum"), expression);
    }
}
