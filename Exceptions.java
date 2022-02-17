public class Exceptions {
    public static class InvalidDenominatorException extends ArithmeticException {
        public InvalidDenominatorException(String message) {
            super(message);
        }
    }
}