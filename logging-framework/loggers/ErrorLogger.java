package loggers;

public class ErrorLogger extends Logger{
    private static ErrorLogger errorLogger;

    public ErrorLogger(LoggerConfig loggerConfig) {
        super(loggerConfig);
        this.level = LogLevel.ERROR;
    }

    public static synchronized Logger getInstance(LoggerConfig loggerConfig) {
        if(errorLogger == null) {
            errorLogger = new ErrorLogger(loggerConfig);
            return errorLogger;
        }
        return errorLogger;
    }

    @Override
    public void log(String message) {
        String errorMsg = "ERROR: " + message;
        if(this.loggerConfig.getLogLevel().ordinal()<=this.level.ordinal()) {
            this.loggerConfig.getLogAppenderStrategy().append(errorMsg);
        }
    }
}
