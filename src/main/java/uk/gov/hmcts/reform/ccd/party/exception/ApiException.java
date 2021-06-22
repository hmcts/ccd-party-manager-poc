package uk.gov.hmcts.reform.ccd.party.exception;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -4L;

    public ApiException(Throwable exception) {
        super(exception);
    }

    public ApiException(String message) {
        super(message);
    }
}
