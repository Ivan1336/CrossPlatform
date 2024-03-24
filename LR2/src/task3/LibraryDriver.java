package task3;

import java.io.*;

public class LibraryDriver {
    private static final File file = new File("src//Data//Task3.txt");

    public static void serializeObject(Library library){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            library.writeExternal(out);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(){
        Externalizable library = new Library();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            library.readExternal(in);
        } catch (Exception e){
            e.printStackTrace();
        }
        return library;
    }
}