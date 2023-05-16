package com.etoak.common.utils;

import com.etoak.common.exception.ParamException;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorUtil {

  private static final Validator validator;

  static {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public static void validate(Object object) {
    Set<ConstraintViolation<Object>> violations = validator.validate(object);

    // 集合不为空表示校验失败
    if (CollectionUtils.isNotEmpty(violations)) {
      String msg = violations.stream()
        .map(v -> v.getPropertyPath().toString() + v.getMessage())
        .collect(Collectors.joining("; "));
      throw new ParamException(msg);
    }
  }
}
