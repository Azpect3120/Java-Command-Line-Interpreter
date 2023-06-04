import java.lang.reflect.Method;

public class ReflectionExample {

    public static void main(String[] args) {
        String className = "Main";

        Method[] methods = className.getClass().getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method.getName());

            if (method.getName().equals("targetMethod")) {
//                method.invoke(null);
                // or
//                Class classNameObject = new Class();
//                method.invoke(classNameObject);
            }
        }
    }
}
