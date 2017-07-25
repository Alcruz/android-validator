package me.alcruz.annotationsvalidation;

public class RequiredValidator extends Validator {
    @Override
    public boolean isValid(Object value) {
        if (value == null) return false;
        else if (value instanceof String) return ValidateString((String)value);
        return true;
    }

    private boolean ValidateString(String value) {
        return value.compareTo(StringUtils.emptyString()) != 0;
    }
}
