package loggers;

import appenders.LogAppenderStrategy;

public class LoggerConfig {
    private final LogLevel logLevel;
    private final LogAppenderStrategy logAppenderStrategy;

    public LoggerConfig(LogLevel logLevel, LogAppenderStrategy logAppenderStrategy) {
        this.logLevel = logLevel;
        this.logAppenderStrategy = logAppenderStrategy;
    }

    public LogLevel getLogLevel(){
        return this.logLevel;
    }

    public LogAppenderStrategy getLogAppenderStrategy(){
        return this.logAppenderStrategy;
    }
}
