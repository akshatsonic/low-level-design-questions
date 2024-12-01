package loggers;

public class InfoLogger extends Logger{
    private static InfoLogger infoLogger;

    public InfoLogger(LoggerConfig loggerConfig){
        super(loggerConfig);
        this.level = LogLevel.INFO;
    }

    public static synchronized Logger getInstance(LoggerConfig loggerConfig) {
        if(infoLogger == null) {
            infoLogger = new InfoLogger(loggerConfig);
            return infoLogger;
        }
        return infoLogger;
    }

    @Override
    public void log(String message) {
        String infoMessage = "INFO: " + message;
        if(this.loggerConfig.getLogLevel().ordinal()<=this.level.ordinal()) {
            this.loggerConfig.getLogAppenderStrategy().append(infoMessage);
        }
    }
}
