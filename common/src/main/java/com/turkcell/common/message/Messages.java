package com.turkcell.common.message;

public class Messages {
    public static class BusinessErrors {
        public static final String DUPLICATE_NATIONALITY_ID_ERROR = "duplicateNationalityIdError";
        public static final String NO_STREET_AND_CITY_FOUND_ERROR = "noStreetAndCityFoundError";
        public static final String NO_CUSTOMER_FOUND = "noCustomerFound";
        public static final String NO_ADDRESS_FOUND = "noAddressFound";
        public static final String CUSTOMER_DOES_NOT_CONTAIN_ADDRESS = "customerDoesNotContainAddress";
        public static final String NO_ORDER_FOUND = "noOrderFound";
        public static final String CUSTOMER_HAS_ONLY_ONE_ADDRESS = "customerHasOnlyOneAddress";
        public static final String CANNOT_DELETE_PRIMARY_ADDRESS = "cannotDeletePrimaryAddress";
        public static final String ADDRESS_ALREADY_IS_PRIMARY = "addressAlreadyIsPrimary";
    }

    public static class ValidationErrors {
        public static final String NOT_BLANK = "{validation.notBlank}";
        public static final String NOT_NULL = "{validation.notNull}";
        public static final String NOT_EMPTY = "{validation.notEmpty}";
        public static final String MIN = "{validation.minValue}";
        public static final String EMAIL = "{validation.email}";
        public static final String NATIONALITY_ID = "{validation.nationalityId}";
    }
}
