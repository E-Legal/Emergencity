package epitech.emergencity.services;

import org.jooq.Field;
import org.jooq.SortField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface JOOQCrudUtils {

    Map<String, Field<?>> sortableField();

    default List<SortField<?>> sorts(Pageable pageable) {
        return pageable.getSort().stream().map(o -> {
            String sortFieldName = o.getProperty();
            Sort.Direction sortDirection = o.getDirection();
            Field<?> field = sortableField().get(sortFieldName);
            return field != null ? sortDirection.isDescending() ? field.desc() : field.asc() : null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
