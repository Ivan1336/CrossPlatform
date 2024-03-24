package task1;

import java.io.*;

public class LibraryDriver {
    private static final File file = new File("src//Data//Task1.txt");

    public static void serializeObject(Library library){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(library);
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    public static Library deserializeObject(){
        Library library = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            library = (Library)in.readObject();
        } catch (Exception e){
           e.printStackTrace();
        }

        return library;
    }
}
