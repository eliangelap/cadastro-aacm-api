package br.org.amigosdoautista.cadastroautista.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class LogUtil {

    private LogUtil() {
    }

    public static <T> void infoLog(Class<?> clazz, T object) {
        ObjectMapper o = new ObjectMapper();
        o.registerModule(new JavaTimeModule());
        try {
            String message = o.writeValueAsString(object);
            Logger.getLogger(clazz.getName()).log(Level.INFO, clazz.getName(), message);
        } catch (IOException e) {
            Logger.getLogger(clazz.getName()).log(Level.SEVERE, getStackTrace(e));
        }
    }

    public static void infoLog(Class<?> clazz, String message) {
        Logger.getLogger(clazz.getName()).log(Level.INFO, clazz.getName(), message);
    }

    public static String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        return sw.toString();
    }

}
