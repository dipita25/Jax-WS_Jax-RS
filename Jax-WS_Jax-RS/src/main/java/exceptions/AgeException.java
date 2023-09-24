package exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "AgeException")
public class AgeException extends Exception{

    private AgeFault ageFault;

    public AgeException(String message, AgeFault ageFault) {
        super(message);
        this.ageFault = ageFault;
    }

    public AgeException(String message, Throwable cause, AgeFault ageFault) {
        super(message, cause);
        this.ageFault = ageFault;
    }

    public AgeFault getFaultInfo(){
        return ageFault;
    }
}
