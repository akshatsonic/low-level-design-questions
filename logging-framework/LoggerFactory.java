import loggers.DebugLogger;
import loggers.ErrorLogger;
import loggers.InfoLogger;
import loggers.LogLevel;
import loggers.Logger;
import loggers.LoggerConfig;

public class LoggerFactory {

    public static Logger getLogger(LogLevel logLevel, LoggerConfig loggerConfig) {
        return switch (logLevel) {
            case INFO -> InfoLogger.getInstance(loggerConfig);
            case ERROR -> ErrorLogger.getInstance(loggerConfig);
            case DEBUG -> DebugLogger.getInstance(loggerConfig);
            default -> null;
        };
    }
}
