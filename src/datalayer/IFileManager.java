package datalayer;

public interface IFileManager {
    void writeToFile(String content, String fileName);
    String readFromFile(String fileName);
}
