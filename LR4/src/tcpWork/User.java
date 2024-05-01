package tcpWork;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
        public static final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd.MM.yyyy");
        public static final String DEFAULT_BIRTHDAY = "12.12.1212";

        private String name;
        private String surname;
        private String sex;
        private Date birthday;

        public User(String name, String surname, String sex, String birthday){
            this.name = name;
            this.surname = surname;
            this.sex = sex;
            try {
                this.birthday = DATE_FORMATTER.parse(birthday);
            } catch (ParseException e) {
                this.birthday = new Date(1996, 9, 17);
            }
        }

        public User(){
            this("Untitled", "Untitled", "Untitled", DEFAULT_BIRTHDAY);
        }

        public String toString(){
            String birthdayString = DATE_FORMATTER.format(birthday);
            if (birthdayString.equals(DEFAULT_BIRTHDAY) && sex.equals("Untitled"))
                birthdayString = "Untitled";

            return  "   Ім'я:           " + name + "\n" +
                    "   Прізвище:       " + surname + "\n" +
                    "   Стать:           " + sex +"\n" +
                    "   Дата народження: " + birthdayString;
        }
}
