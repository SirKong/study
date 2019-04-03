package com.ccnu.test.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import com.ccnu.test.validation.constraints.CheckRule;
import com.ccnu.test.validation.constraints.CheckRule.Rule;
import com.ccnu.test.validation.constraints.DefaultValue;
import com.ccnu.test.validation.constraints.Option;
import com.ccnu.test.validation.constraints.OptionIf;

public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static ExpressionParser parser = new SpelExpressionParser();

    public static <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Class<?> clazz = obj.getClass();
        if (AnnotationUtils.isAnnotationDeclaredLocally(CheckRule.class, clazz)) {
            CheckRule checkRule = obj.getClass().getAnnotation(CheckRule.class);
            Rule[] rules = checkRule.rules();
            EvaluationContext context = new StandardEvaluationContext(obj);
            for (Rule rule : rules) {
                boolean flag = parser.parseExpression(rule.exp()).getValue(context, Boolean.class);
                if (!flag) {
                    result.setHasErrors(true);
                    result.addErrorMsg(rule.message());
                }
            }
        }

        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            for (ConstraintViolation<T> cv : set) {
                result.addErrorMsg(cv.getMessage());
            }
        }

        BeanWrapper wrapper = new BeanWrapperImpl(obj);

        for (Field field : clazz.getDeclaredFields()) {
//			Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : field.getAnnotations()) {
                Class<?> annotationClazz = annotation.getClass();
                //deal with field annotated by Option
                String value = (String) wrapper.getPropertyValue(field.getName());
                if (Option.class.isAssignableFrom(annotationClazz)) {
                    if (!StringUtils.hasText(value)) {
                        continue;
                    }
                    String[] options = ((Option) annotation).value();
                    boolean flag = false;
                    for (String option : options) {
                        if (value.equals(option)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        result.setHasErrors(true);
                        result.addErrorMsg(field.getName() + "取值超出范围{" + options + "}");
                    }
                }

                //deal with field annotated by DefaultValue
                if (DefaultValue.class.isAssignableFrom(annotationClazz)) {
                    if (!StringUtils.hasText(value)) {
                        String defaultValue = ((DefaultValue) annotation).value();
                        wrapper.setPropertyValue(field.getName(), defaultValue);
                    }
                }
                //deal with field annotated by OptionIf
                if (OptionIf.class.isAssignableFrom(annotationClazz)) {
                    EvaluationContext context = new StandardEvaluationContext(obj);
                    for (OptionIf.OptionItem optionItem : ((OptionIf) annotation).optionItems()) {
                        String condition = optionItem.condition();
                        //
                        if (parser.parseExpression(condition).getValue(context, Boolean.class)) {
                            String[] options = optionItem.options();
                            boolean flag = false;
                            for (String option : options) {
                                if (value.equals(option)) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                result.setHasErrors(true);
                                result.addErrorMsg(field.getName() + "取值超出范围{" + options + "}");
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            for (ConstraintViolation<T> cv : set) {
                result.addErrorMsg(cv.getMessage());
            }
        }
        return result;
    }
}