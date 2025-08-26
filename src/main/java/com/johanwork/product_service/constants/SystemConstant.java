package com.johanwork.product_service.constants;

public class SystemConstant {

    private SystemConstant() {}

    public static final class Validation {
        public static final String TITLE_400 = "VALIDATION ERROR";
        public static final String MESSAGE_400 = "Bad request make sure data is valid";
        private Validation() {}
    }

    public static final class Server {
        public static final String TITLE_500 = "SERVER ERROR";
        public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
        private Server() {}
    }

}
