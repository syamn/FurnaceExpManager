/**
 * FurnaceExpManager - Package: syam.furnaceexpmanager.exception
 * Created: 2012/12/10 13:48:36
 */
package syam.furnaceexpmanager.exception;

/**
 * CommandException (CommandException.java)
 * 
 * @author syam(syamn)
 */
public class CommandException extends Exception {
    private static final long serialVersionUID = -1945371733591514553L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
