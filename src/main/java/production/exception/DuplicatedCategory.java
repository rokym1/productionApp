package production.exception;

/** Unchecked exception, is a subclass of RuntimeException
 *  Unchecked exceptions do not need to be declared in a method or constructor's throws clause
 *  if they can be thrown by the execution of the method or constructor and propagate outside
 *  the method or constructor boundary.
 */
public class DuplicatedCategory extends RuntimeException{

    /** Constructs a new runtime exception with the specified detail message.
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                Throwable.getMessage() method.
     */
    public DuplicatedCategory(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     * @param message -the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause -the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DuplicatedCategory(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message of
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     * @param cause -the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value
     *              is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DuplicatedCategory(Throwable cause) {
        super(cause);
    }
}
