package cn.ibestcode.easiness.validation.constraints;


import cn.ibestcode.easiness.validation.validator.IDNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
  validatedBy = IDNumberValidator.class
)
public @interface IDNumber {

  String message() default "{com.easiness.validation.constraints.IDNumber.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
