package com.example.springsecuritytest.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUtils {

    private static final Set<String> IGNORED_FIELDS = new HashSet<>(Arrays.asList(
            "id", "createdAt", "updatedAt", "password", "serialVersionUID"
    ));

    public static <T> void copyNonNullProperties(T source, T target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (IGNORED_FIELDS.contains(field.getName())) {
                continue;
            }
            try {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (Exception e) {
                throw new RuntimeException("Alan kopyalanamadı: " + field.getName(), e);
            }
        }
    }


}
