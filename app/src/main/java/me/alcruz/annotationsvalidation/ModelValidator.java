package me.alcruz.annotationsvalidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import me.alcruz.annotationsvalidation.annotations.Required;

class ModelValidator extends Validator {

    public Map<String, String> validate(Object model) {
        Map<Field, Validator> fieldValidators = getFieldValidatorMap(model);
        Map<String, String> validationErrors = new HashMap<>();
        for (Map.Entry<Field, Validator> entry :
                fieldValidators.entrySet()) {
            Field field = entry.getKey();
            Validator validator = entry.getValue();
            try {
                if (!validator.isValid(field.get(model))) {
                    validationErrors.put(field.getName(), "This is required.");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return validationErrors;
    }

    @Override
    public boolean isValid(Object model) {
        Map<Field, Validator> fieldValidator = getFieldValidatorMap(model);
        for (Map.Entry<Field, Validator> entry :
                fieldValidator.entrySet()) {
            Field field = entry.getKey();
            Validator validator = entry.getValue();
            try {
                if (!validator.isValid(field.get(model))) {
                   return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private Map<Field, Validator> getFieldValidatorMap(Object model) {
        Field[] fields = GetFields(model);
        return zipFieldsWithValidator(fields);
    }

    private Map<Field,Validator> zipFieldsWithValidator(Field[] fields) {
        Map<Field, Validator> fieldsValidators = new HashMap<>();
        for (Field field : fields) {
            Validator validator = GetValidator(field);
            fieldsValidators.put(field, validator);
        }
        return fieldsValidators;
    }

    private Validator GetValidator(Field field) {
        for (Annotation annotation :  field.getAnnotations()) {
            if (annotation instanceof Required) {
                return new RequiredValidator();
            }
        }

        return new AlwaysTrueValidator();
    }

    private Field[] GetFields(Object model) {
        return model.getClass().getFields();
    }
}
