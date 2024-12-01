import appenders.ConsoleAppender;
import appenders.FileAppender;
import loggers.LogLevel;
import loggers.Logger;
import loggers.LoggerConfig;

public class LoggingFrameworkDemo {
    public static void main(String[] args) {
        LoggerConfig loggerConfig = new LoggerConfig(LogLevel.DEBUG, new ConsoleAppender());
        Logger infoLogger = LoggerFactory.getLogger(LogLevel.INFO, loggerConfig);
        Logger errorLogger = LoggerFactory.getLogger(LogLevel.ERROR, loggerConfig);
        Logger debugLogger = LoggerFactory.getLogger(LogLevel.DEBUG, loggerConfig);

        infoLogger.log("This is an info message");
        errorLogger.log("This is an error message");
        debugLogger.log("This is a debug message");
    }
}
