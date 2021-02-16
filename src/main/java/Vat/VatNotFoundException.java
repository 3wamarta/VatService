package Vat;

public class VatNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    public VatNotFoundException(String message) {

        this.message = message;
    }
}
