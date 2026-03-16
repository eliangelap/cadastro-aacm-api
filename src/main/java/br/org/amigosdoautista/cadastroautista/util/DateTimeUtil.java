package br.org.amigosdoautista.cadastroautista.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static ZonedDateTime getCurrentDateTime() {
        ZoneId zone = ZoneId.of(ZoneId.SHORT_IDS.get("BET"));
        return Instant.now().atZone(zone);
    }

}
