package soap;

import exceptions.AgeException;
import exceptions.AgeFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(serviceName = "AgeService")
public class AgeService {

    @WebMethod
    public String calculAge( @WebParam(name = "birthYear") Integer birthYear) throws AgeException {

        if (birthYear instanceof Integer && birthYear < LocalDateTime.now().getYear()){
            LocalDateTime today = LocalDateTime.now();
            int age = today.getYear() - birthYear;
            return "Vous avez " + age + " ans !";
        }

        AgeFault ageFault = new AgeFault();
        ageFault.setCode("400");
        ageFault.setMessage("il y a un souci avec la valeur saisie");

        throw new AgeException("année trop grande", ageFault);

    }
}
