package com.turkcell.pair1.orderservice.core.service.constants;

public class Messages {
    public static final String UNKNOWN_ERROR = "Unknown error.";

    public static class BusinessErrors{
        public static final String NO_ORDER_FOUND_ERROR="noOrderFoundError";
    }

    public static class ValidationErrors{
        public static final String NOT_BLANK="{validation.notBlank}";
        public static final String NOT_NULL="{validation.notNull}";
        public static final String NOT_EMPTY = "{validation.notEmpty}";
        public static final String MIN = "{validation.minValue}";
    }
}
