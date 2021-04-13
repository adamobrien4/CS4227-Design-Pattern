package main.framework.contexts;

public class ErrorContext extends Context {
    private final Exception exception;

    public ErrorContext(String message, Exception exception)
    {
        super(message);
        this.exception = exception;
    }

    public Exception getException()
    {
        return exception;
    }
}
