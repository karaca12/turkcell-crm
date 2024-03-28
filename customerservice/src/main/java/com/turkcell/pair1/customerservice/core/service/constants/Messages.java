package com.turkcell.pair1.customerservice.core.service.constants;

public class Messages {
    public static class BusinessErrors{
        public static final String NO_CUSTOMER_FOUND_ERROR="noCustomerFoundError";
        public static final String DUPLICATE_NATIONALITY_ID_ERROR="duplicateNationalityIdError";
        public static final String NO_STREET_AND_CITY_FOUND_ERROR = "noStreetAndCityFoundError";
        public static final String NO_CITY_FOUND_ERROR = "noCityFoundError";
    }

    public static class ValidationErrors{
        public static final String NOT_BLANK="{validation.notBlank}";
        public static final String NOT_NULL="{validation.notNull}";
        public static final String NOT_EMPTY = "{validation.notEmpty}";
        public static final String MIN = "{validation.minValue}";
        public static final String EMAIL = "{validation.email}";
    }
}
