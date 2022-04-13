package production.exception;

/**
 * Checked exception, is a subclass of Exception. Checked exceptions need to be declared in a
 * method or constructor's throws clause if they can be thrown by the execution of the method or
 * constructor and propagate outside the method or constructor boundary.
 */
public class DuplicatedItem extends Exception{

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message. The detail message is saved for later retrieval by the
     * Throwable.getMessage() method.
     */
    public DuplicatedItem(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     * @param cause  the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *                        (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DuplicatedItem(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of (cause==null ? null :
     * cause.toString()) (which typically contains the class and detail message of cause). This constructor is useful
     * for exceptions that are little more than wrappers for other throwables (for example, PrivilegedActionException).
     * @param cause  - the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *               (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DuplicatedItem(Throwable cause) {
        super(cause);
    }
}
