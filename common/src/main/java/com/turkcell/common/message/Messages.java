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
        public static final String NO_ACCOUNT_FOUND = "noAccountFound";
        public static final String ACCOUNT_DOES_NOT_CONTAIN_ADDRESS = "accountDoesNotContainAddress";
        public static final String ACCOUNT_HAS_ONLY_ONE_ADDRESS = "accountHasOnlyOneAddress";
        public static final String WRONG_USERNAME_OR_PASSWORD = "wrongUsernameOrPassword";
        public static final String NO_PRODUCT_FOUND = "noProductFound";
        public static final String INVALID_PRODUCT_TYPE = "Invalid configuration type for product type: %s";
    }

    public static class ValidationErrors {
        public static final String NOT_BLANK = "{validation.notBlank}";
        public static final String NOT_NULL = "{validation.notNull}";
        public static final String NOT_EMPTY = "{validation.notEmpty}";
        public static final String MIN = "{validation.minValue}";
        public static final String EMAIL = "{validation.email}";
        public static final String NATIONALITY_ID = "{validation.nationalityId}";
        public static final String NOT_WHOLE_NUMBER = "{validation.notWholeNumber}";
        public static final String NOT_ALPHABETICAL = "{validation.notAlphabetical}";
        public static final String INVALID_MOBILE_PHONE = "{validation.invalidMobilePhone}";
        public static final String LENGTH = "{validation.length}";
    }
}
