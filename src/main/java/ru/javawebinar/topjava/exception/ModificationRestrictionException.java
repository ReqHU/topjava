package ru.javawebinar.topjava.exception;

import ru.javawebinar.topjava.helper.ErrorType;

public class ModificationRestrictionException extends ApplicationException {
    public static final String EXCEPTION_MODIFICATION_RESTRICTION = "exception.user.modificationRestriction";

    public ModificationRestrictionException() {
        super(ErrorType.VALIDATION_ERROR, EXCEPTION_MODIFICATION_RESTRICTION);
    }
}