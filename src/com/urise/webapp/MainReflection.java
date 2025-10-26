package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");
        System.out.println(r);

        System.out.println("Вызов метода - toString через Field ");
        Method toStringMethod = r.getClass().getMethod("toString");
        Object runStrMet = toStringMethod.invoke(r);
        System.out.println(runStrMet);

       /* String str = " ";
        Field[] fields = str.getClass().getDeclaredFields();
        System.out.println("Fields from String:");
        for (Field field2 : fields) {
            System.out.println(field2);
        }
        Method[] methods = str.getClass().getMethods();
        System.out.println("Methods from String:");
        for (Method method : methods){
            System.out.println(method);
        }
        Object stringHashCose = methods[3].invoke("WatsUP!");
        System.out.println(stringHashCose);*/
    }
}
