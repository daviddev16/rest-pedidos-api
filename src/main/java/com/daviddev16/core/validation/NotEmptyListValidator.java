package com.daviddev16.core.validation;

import com.daviddev16.core.annotation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 *  NotEmptyListValidator constraint validador para a anotação {@link NotEmptyList}.
 *  a regra para validação é: Uma lista não é válida quando é nula ou está vazia.
 *
 * @see NotEmptyList
 *
 **/
public class NotEmptyListValidator
        implements ConstraintValidator<NotEmptyList, List<?>> {

    @Override
    public boolean isValid(List<?> objects,
                           ConstraintValidatorContext constraintValidatorContext)
    {
        return objects != null && !objects.isEmpty();
    }
}
