package soap;

import soap.data.Book;
import soap.exceptions.AgeException;
import soap.exceptions.AgeFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@WebService(serviceName = "TestService")
public class TestService {

    private static final Logger LOGGER = Logger.getLogger(TestService.class.getName());


    /*méthode servant à tester la gestion des erreurs dans un service de type Soap*/
    @WebMethod
    public String calculAge( @WebParam(name = "birthYear") Integer birthYear) throws AgeException {

        if (birthYear instanceof Integer && birthYear < LocalDateTime.now().getYear()
                && birthYear != null && birthYear!= 0){
            LocalDateTime today = LocalDateTime.now();
            int age = today.getYear() - birthYear;
            return "Vous avez " + age + " ans !";
        }

        AgeFault ageFault = new AgeFault();
        ageFault.setCode("400");
        ageFault.setMessage("il y a un souci avec la valeur saisie");

        throw new AgeException("valeur incorrecte", ageFault);
    }

    /*méthode servant à tester l'envoi et la réception d'objets dans un service de type SOAP*/
    @WebMethod
    public Book create(@WebParam Book book){
        return new Book(book.getTitle(), book.getPublication_date());
    }

    /*méthode servant à tester le mapping explicite fait entre la donnée de type date qui est envoyée
    dans l'objet book(champ publication_date) et le champ dans lequel on le place dans l'objet java
    (book->publication_date de type LocalDateTime). on a utilisé un adapteur (LocalDateTime)
    qu'on a placé sur le champ concerné dans le pojo Book java*/
    @WebMethod
    public String testDate(@WebParam Book book){
        LOGGER.info("MYLOGGER = "+ book.getPublication_date().getClass());
        LOGGER.info("MYLOGGER2 = "+ book.getPublication_date().getYear());
        return ""+book.getPublication_date().getYear();
    }
}
