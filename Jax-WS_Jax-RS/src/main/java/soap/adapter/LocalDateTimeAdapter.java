package soap.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm:ss");
    private static final Logger LOGGER = Logger.getLogger(LocalDateTimeAdapter.class.getName());

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        LocalDateTime localDateTime = null;

        try {
            localDateTime = LocalDateTime.parse(v);
            localDateTime.format(DATE_FORMAT);
        }catch (Exception e){
            LOGGER.info("error during unmarshalling : "+ e);
        }
        return localDateTime;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        String localDateTime = null;

        try {
           localDateTime = v.format(DATE_FORMAT);
        }catch (Exception e){
            LOGGER.info("error during marshalling : "+ e);
        }
        return localDateTime;
    }
}
