package com.mech_serv_mng.services.specifications;

import com.mech_serv_mng.models.Car;
import com.mech_serv_mng.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public final class CarSpecifications {
    public static Specification<Car> brandContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("brand"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Car> modelContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("model"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Car> colorContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("color"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Car> regNumEquals(String expression) {
        return (root, query, builder) -> builder.equal(root.get("regNum"), expression);
    }
}
