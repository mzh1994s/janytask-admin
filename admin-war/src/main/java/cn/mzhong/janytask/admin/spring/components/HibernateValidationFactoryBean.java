package cn.mzhong.janytask.admin.spring.components;

import org.hibernate.validator.HibernateValidator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class HibernateValidationFactoryBean extends SpringValidatorAdapter {

    private volatile Validator validator;

    public HibernateValidationFactoryBean() {
        super(getValidator());
    }

    public static Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
