package loggers;

public class DebugLogger extends Logger{
    private static DebugLogger debugLogger;

    public DebugLogger(LoggerConfig loggerConfig) {
        super(loggerConfig);
        this.level = LogLevel.DEBUG;
    }

    public static synchronized Logger getInstance(LoggerConfig loggerConfig) {
        if(debugLogger == null) {
            debugLogger = new DebugLogger(loggerConfig);
            return debugLogger;
        }
        return debugLogger;
    }


    @Override
    public void log(String message) {
        String debugMessage = "DEBUG: " + message;
        if(this.loggerConfig.getLogLevel().ordinal()<=this.level.ordinal()) {
            this.loggerConfig.getLogAppenderStrategy().append(debugMessage);
        }
    }
}
