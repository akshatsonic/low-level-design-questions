package appenders;

public class ConsoleAppender implements LogAppenderStrategy{
    @Override
    public void append(String message) {
        System.out.println("Writing to console: " + message);
    }
}
