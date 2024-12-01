package loggers;

public abstract class Logger {
    protected LoggerConfig loggerConfig;
    protected LogLevel level;

    public abstract void log(String message);

    public Logger(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }
}
