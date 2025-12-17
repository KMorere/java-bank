import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CustomLogger {
    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    private static final Logger logger = Logger.getLogger(CustomLogger.class.getName());

    public CustomLogger() {
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColorFormatter());
        logger.addHandler(handler);

        /*logger.info("This is an INFO log.");
        logger.warning("This is an WARNING log.");
        logger.severe("This is a SEVERE log.");
        logger.fine("This is a FINE log.");*/
    }

    public Logger getLogger() { return logger; }

    static class ColorFormatter extends Formatter {
        @Override
        public String format(LogRecord log) {
            String color;
            switch (log.getLevel().getName()) {
                case "INFO":
                    color = GREEN;
                    break;
                case "WARNING":
                    color = YELLOW;
                    break;
                case "SEVERE":
                    color = RED;
                    break;
                case "FINE":
                    color = BLUE;
                    break;
                default:
                    color = RESET;
                    break;
            }
            return color + "[" + log.getLevel() + "] " + log.getMessage() + RESET + "\n";
        }
    }
}
