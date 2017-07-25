package me.alcruz.annotationsvalidation;

import org.junit.Test;

import java.util.Map;

import me.alcruz.annotationsvalidation.models.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidatorUnitTest {
    @Test
    public void validate_nullIsRequired() throws Exception {
        Validator requiredValidator = new RequiredValidator();
        boolean result = requiredValidator.isValid(null);

        assertEquals(false, result);
    }

    @Test
    public void validate_emptyStringShouldBeRequired() {
        Validator requiredValidator = new RequiredValidator();
        boolean result = requiredValidator.isValid(StringUtils.emptyString());

        assertEquals(false, result);
    }

    @Test
    public void validate_requiredValidatorInModel() {
        Person person = new Person(null, null, 55);

        ModelValidator modelValidator = new ModelValidator();
        boolean isValid  = modelValidator.isValid(person);
        Map<String, String> validationErrors = modelValidator.validate(person);

        assertEquals(false, isValid);
        assertTrue("firstName field should be valid", validationErrors.containsKey("firstName"));
        assertTrue("lastName field should be valid", validationErrors.containsKey("lastName"));
    }
}
