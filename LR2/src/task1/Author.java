package task1;

import java.io.Serializable;

public class Author extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    public Author(String name, String surname){
        setSurname(surname);
        setName(name);
    }

    public String toString(){
        return super.toString();
    }
}