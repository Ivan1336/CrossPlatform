package udpWork;


import java.io.Serializable;
import java.util.ArrayList;

public class ActiveUsers implements Serializable{
    private static final long serialVersionUID = 1;

    private ArrayList<User> registeredUsers;

    public ActiveUsers(){
        registeredUsers = new ArrayList<>();
    }

    public void registerUser(User user){
        registeredUsers.add(user);
    }

    public boolean isEmpty(){
        return registeredUsers.isEmpty();
    }

    public int getUsersCount(){
        return registeredUsers.size();
    }

    public boolean isUserRegistered(User user){
        boolean isRegistered = false;

        for (User userFromList: registeredUsers)
            if (userFromList.equals(user)){
                isRegistered = true;
                break;
            }

        return isRegistered;
    }

    public User getUser(int pos){
        return registeredUsers.get(pos);
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (User user: registeredUsers){
            stringBuilder.append("\t").append(user.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}

