package com.example.sharagasystem.specification;

import com.example.sharagasystem.model.Request;
import com.example.sharagasystem.model.dto.filter.RequestFilterDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class RequestSpecification {

    public static Specification<Request> columnEqual(List<RequestFilterDto> filterDTOList) {
        return new Specification<Request>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Request> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                for (RequestFilterDto filter : filterDTOList) {
                    Predicate predicate = criteriaBuilder.equal(root.get(filter.getColumnName()), filter.getColumnValue());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
