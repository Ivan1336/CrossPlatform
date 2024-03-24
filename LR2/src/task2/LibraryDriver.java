package task2;

import java.io.*;

public class LibraryDriver {
    private static final File file = new File("src//Data//Task2.txt");

    public static void serializeObject(Object object) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject() {
        Object object = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            object = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}