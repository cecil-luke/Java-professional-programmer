package com.etoak.common.util;

import com.etoak.common.exception.ParamException;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义校验工具类
 */
public class ValidatorUtil {

    /**
     * Java Bean的校验器
     */
    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 校验Java Bean
     */
    public static void validate(Object object, Class... clazz) {
        // 如果校验通过, 则集合为空
        Set<ConstraintViolation<Object>> violations = validator.validate(object, clazz);

        if (CollectionUtils.isNotEmpty(violations)) {
            String message = violations.stream()
                    .map(v -> v.getPropertyPath().toString() + v.getMessage())
                    .collect(Collectors.joining(";"));
            throw new ParamException(message);
        }
    }
}
