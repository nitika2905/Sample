package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

    static Logger log = LoggerFactory.getLogger(CommonUtils.class);
    private CommonUtils() {
        //do nothing
    }
    public static void pause(final long time) {
        try {
            Thread.sleep(time);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    public static void sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception error) {
            log.error("The exception occurred is : " + error);
        }
    }

}