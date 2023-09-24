package soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(serviceName = "AgeService")
public class AgeService {

    @WebMethod
    public String calculAge( @WebParam(name = "birthYear") int birthYear){
        LocalDateTime today = LocalDateTime.now();
        int age = today.getYear() - birthYear;
        return "Vous avez " + age + "ans !";
    }
}
