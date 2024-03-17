import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
public class Main {
    public  static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        // Створення об'єкта класу Student
        Student student = new Student("Name", 19, 2);

        // Виклик методів для виконання завдань
        System.out.println("Завдання 1:");
        System.out.println(task1("Student"));
        System.out.println("Завдання 2:");
        task2(student);
        System.out.println("Завдання 3:");
        task3(student, "setName","Ivan");
        task3(student, "setName","Victor");
        task3(student, "getYear",  null);
    }

    //Завдання 1
    public static String task1(String nameOfClass){
        Class<?> dclass = null;
        try {
            dclass = Class.forName(nameOfClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Entered name -> ").append(nameOfClass);


        Package pack1 = dclass.getPackage();

        stringBuilder.append("\nPackage: ").append(pack1);

        stringBuilder.append("\n\nInterfaces: " + "\n");
        Class<?>[] interf = dclass.getInterfaces();
        for (Class<?> aClass : interf) {
            stringBuilder.append(aClass.toGenericString()).append("\n");
        }
        stringBuilder.append("\n");


        stringBuilder.append("//Поля" + "\n");
        Field[] fields = dclass.getDeclaredFields();
        for (Field field : fields) {
            stringBuilder.append(field.toGenericString()).append("\n");
        }
        stringBuilder.append("\n");

        stringBuilder.append("//Конструктори" + "\n");
        Constructor<?>[] constructs = dclass.getConstructors();
        for (Constructor<?> construct : constructs) {
            stringBuilder.append(construct.toGenericString()).append("\n");
        }
        stringBuilder.append("\n");

        Method[] meth = dclass.getMethods();
        stringBuilder.append("//Методи" + "\n");
        for (Method method : meth) {
            stringBuilder.append(method.toGenericString()).append("\n");
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();

    }

    // Завдання 2
    public static void task2(Object object){
        AtomicInteger a = new AtomicInteger();
        int choice;
        List<Method> list = getMethodWithPublicMod(object);
        showFields(object);

        System.out.println("Всі публічні методи: ");
        list.forEach(method -> {
            System.out.println(method.toGenericString());
        });
        list = getMethodsWithoutParam(list);
        System.out.println("Всі публічні методи без параметрів: ");
        list.forEach(method -> {
            System.out.println((a.incrementAndGet() + ") ") + method.toGenericString());
        });

        System.out.println("Оберіть метод -> ");
        choice = scan();
        while (choice > 0 && choice <= list.size()) {//щоб вийти, введіть будь-яке число, яке не збігається з номером методу
            try {
                System.out.println(list.get(choice - 1) + "\n результат: " + list.get(choice - 1).invoke(object));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("(якщо ви хочете припинити введення методів, введіть число, яке не входить в діапазон)");
            System.out.println("Оберіть метод ->");
            choice = scan();
        }
    }

    //Завдання 3
    public static void task3(Object obj, String nameOfMethod, Object...parameters){
        Object result;
        Class<?> someClass = obj.getClass();
        Class<?>[] parameterTypes = Arrays.stream(Optional.ofNullable(parameters).orElse(new Object[0]))
                .map(param -> param.getClass())
                .collect(Collectors.toList())
                .toArray(new Class<?>[0]);
        try {
            Method method = someClass.getDeclaredMethod(nameOfMethod, parameterTypes);
            System.out.println("Стан методу до виклику: " + obj);
            result = method.invoke(obj, parameters);
            System.out.println("Результат = " + Optional.ofNullable(result).orElse("void"));
            System.out.println("Стан методу після виклику: " + obj);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    // Метод для виведення полів класу
    public static void showFields(Object object){
        Class<?> someClass = object.getClass();
        Field[] fields =  Optional.ofNullable(someClass.getDeclaredFields()).orElse(new Field[0]);
        System.out.println("Поля в класі " + someClass.getSimpleName());
        Arrays.stream(fields).forEach(field -> {
            try {
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                System.out.println(field.getType() + " " + field.getName() + " = " + field.get(object));
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    // Метод для отримання списку публічних методів
    private static List<Method> getMethodWithPublicMod(Object object) {
        Class<?> someClass = object.getClass();
        Method[] methods = Optional.ofNullable(someClass.getDeclaredMethods()).orElse(new Method[0]);

        List<Method> arrayList = Arrays.asList(methods);
        arrayList = arrayList.stream().filter(method -> Modifier.isPublic(method.getModifiers())).collect(Collectors.toList());
        return arrayList;
    }

    // Метод для отримання списку методів без параметрів
    private static List<Method> getMethodsWithoutParam(List<Method> list){
        list = list.stream().filter(method -> method.getParameterCount() == 0).collect(Collectors.toList());
        return list;
    }

    // Метод для зчитування введення з консолі
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
                System.err.println("Некоректний формат числа");
            }
        }
        return choice;
    }



    // Вкладений клас для створення графічного інтерфейсу користувача
    public static class FrameInspector extends JFrame {
        private static JPanel panel;
        public FrameInspector() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            createGUI();

            setSize(800, 600);
            setVisible(true);
            setResizable(true);
        }

        public static void main(String[] args) {
            new FrameInspector();
        }

        private void createGUI() {
            JTextField enter;
            TextArea textArea = new TextArea();
            panel = new JPanel();
            enter = new JTextField(40);

            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 30, 5));

            JButton buttonRun = new JButton("Аналіз");
            JButton buttonClean = new JButton("Очистити");
            JButton buttonExit = new JButton("Завершити");

            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(0, 3, 5,3));
            buttons.add(buttonRun);
            buttons.add(buttonClean);
            buttons.add(buttonExit);


            panel.add(new JLabel("Name of class(full path):"));
            panel.add(enter);
            panel.add(buttons);


            add(panel, BorderLayout.NORTH);

            add(textArea);


            buttonRun.addActionListener(e -> {
                String nameOfCLass;
                nameOfCLass = enter.getText();
                if (!nameOfCLass.isEmpty()) {
                    textArea.setText(task1(nameOfCLass));
                }
            });

            buttonClean.addActionListener(e -> {
                textArea.setText("");
                enter.setText("");
            });

            buttonExit.addActionListener(e -> {
                 dispose();
                 System.exit(0);
            });
        }
    }
}