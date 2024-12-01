package appenders;

public class FileAppender implements LogAppenderStrategy{
    @Override
    public void append(String message) {
        System.out.println("Writing to file: " + message);
    }
}
