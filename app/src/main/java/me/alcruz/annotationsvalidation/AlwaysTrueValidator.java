package me.alcruz.annotationsvalidation;

/**
 * Created by alvin on 7/25/2017.
 */

class AlwaysTrueValidator extends Validator {
    @Override
    public boolean isValid(Object object) {
        return true;
    }
}
