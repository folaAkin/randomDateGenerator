package com.footballindex.tests.dategenerator.helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    public static boolean isDateFormatValid(String strDate,String dateFormat) {
        if (strDate.trim().equals("")) {
            return true;
        } else {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            //"MM/dd/yyyy";
            SimpleDateFormat sdfrmt = new SimpleDateFormat(dateFormat);
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date

             */
            try {
                Date date = sdfrmt.parse(strDate);
                LOG.info(strDate + ": is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e) {
                LOG.error(strDate+ ": is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

}


