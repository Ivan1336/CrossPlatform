import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

public class ArrayReflect {//task 4
    public  static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String... args) throws ClassNotFoundException, IOException {
        int[] dimension = initDimension(0);

        System.out.println("Введіть повне ім'я класу або примітивного типу:");
        System.out.print("=> ");
        String classString = scanLine();
        Object array;
        try {
            array = getArray(classString, dimension);
        } catch (ClassNotFoundException e) {
            System.out.println("Клас не знайдено.");
            return;
        }

        int choice = 0;
        while (choice != 3){
            menu();
            choice = scan();
            switch (choice){
                case 1:
                    dimension = initDimension(dimension.length);
                    array = setSize(array, dimension);
                    break;
                case 2:
                    System.out.println(showArray(array));
                    break;
            }
        }
        in.close();
    }

    public static void menu() {
        System.out.println("1. Змінити розмір");// змінити розмір
        System.out.println("2. Показати");// показати
        System.out.println("3. Вийти");// вийти
        System.out.println("--------------------------");
    }

        public static int getDimension(Object array){
        String classString = array.getClass().toString();
        int dimension = 0;

        for (int i = 0; i < classString.length(); i++) {
            if (classString.charAt(i) == '['){
                dimension++;
            }
        }
        return dimension;
    }

    public static Class<?> getClass(Object array) throws ClassNotFoundException {
        String s = array.getClass().toString();
        if (s.indexOf(';') != -1){
            s = s.substring(s.indexOf('L') + 1, s.indexOf(';'));
            return Class.forName(s);
        } else{
            return getSimpleClass(s.charAt(s.length() - 1));
        }

    }

    public static Object getArray (Class<?> cls, int [] dimension){
        return Array.newInstance(cls, dimension);
    }

    public static Object getArray(String cls, int [] dimension) throws ClassNotFoundException {
        Class<?> simpleClass;
        return getArray((simpleClass = getSimpleClass(cls)) == null ? Class.forName(cls) : simpleClass, dimension);
    }

    public static Class<?> getSimpleClass(String string){
        switch (string){
            case "int":
                return int.class;
            case "double":
                return double.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "short":
                return short.class;
            case "char":
                return char.class;
            case "boolean":
                return boolean.class;
        }

        return null;
    }

    public static Class<?> getSimpleClass(Character letter){
        switch (letter){
            case 'I':
                return Integer.class;
            case 'D':
                return Double.class;
            case 'J':
                return Long.class;
            case 'F':
                return Float.class;
            case 'S':
                return Short.class;
            case 'C':
                return Character.class;
            case 'Z':
                return Boolean.class;
        }

        return null;
    }

    public static Object setSize(Object array, int[] newDimension) throws ClassNotFoundException {
        // Отримуємо тип класу масиву
        String classString = getClass(array).getName();
        // Створюємо новий масив з новими розмірами
        Object newArray = getArray(Class.forName(classString), newDimension);

        // Визначаємо мінімальні розміри для копіювання значень
        int height = Math.min(newDimension[0], Array.getLength(array));
        int width = (getDimension(array) == 2) ? Math.min(newDimension[1], Array.getLength(Array.get(array, 0))) : 0;

        // Копіюємо значення зі старого масиву в новий
        for (int i = 0; i < height; i++) {
            if (getDimension(array) == 2) {
                // Якщо це матриця, копіюємо кожен рядок
                System.arraycopy(Array.get(array, i), 0, Array.get(newArray, i), 0, width);
            } else {
                // Якщо це одновимірний масив, копіюємо один рядок
                System.arraycopy(array, 0, newArray, 0, height);
            }
        }

        return newArray;
    }

    public static String showArray(Object array){//conversion to string
        StringBuilder stringBuilder = new StringBuilder();
        if (getDimension(array) == 1) {
            for (int i = 0; i < Array.getLength(array); i++){
                stringBuilder.append(Array.get(array, i).toString()).append(" ");
            }
            stringBuilder.append("\n");
        }
        else{
            for (int i = 0; i < Array.getLength(array); i++) {
                for (int j = 0; j < Array.getLength(Array.get(array, i)); j++){
                    stringBuilder.append(Array.get(Array.get(array, i), j)).append(" ");
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static int [] initDimension(int modifier){
        int [] dimension;
        if (modifier == 0) {
            System.out.print("Оберіть одномірний масив чи двомірний масив(матриця) Введіть - 1 або 2: ");
            dimension = new int[scan()];
        }
        else if (modifier == 1)
            dimension = new int[1];
        else
            dimension = new int[2];

        if (dimension.length > 2) {
            System.out.println("Не коректні дані.");
            System.exit(0);
        }

        for (int i = 0; i < dimension.length; i++) {
            System.out.print("  Введіть розмір " + (i + 1) + ": ");
            dimension[i] = scan();
        }

        return dimension;
    }

    public static int scan(){
        int choice = 0;
        boolean flag = true;
        while (flag){
            flag = false;
            try {
                choice = Integer.parseInt(in.readLine());
            } catch (NumberFormatException | IOException e){
                //e.printStackTrace();
                flag = true;
                System.err.println("Не коректні дані.");
            }
        }
        return choice;
    }

    public static String scanLine(){
        String inputChoice = "";
        try {
            inputChoice = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputChoice;
    }
}