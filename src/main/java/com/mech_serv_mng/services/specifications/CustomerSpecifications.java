package com.mech_serv_mng.services.specifications;

import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public final class CustomerSpecifications {
    public static Specification<Customer> nameContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("name"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Customer> familyNameContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("familyName"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Customer> identNumContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("identNum"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Customer> phoneNumberContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("phoneNumber"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Customer> emailContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("email"), StringUtils.sqlLikePreprocess(expression));
    }

    public static Specification<Customer> birthDateEquals(String expression) {
        return (root, query, builder) -> builder.equal(root.get("birthDate"), expression);
    }

}
