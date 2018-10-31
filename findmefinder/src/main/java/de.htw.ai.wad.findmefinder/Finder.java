package de.htw.ai.wad.findmefinder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Finder {

    private static List<Method> methodList;
    private static List<Field> fieldList;

    public static boolean findAnnotatedMethods(String klassenName)
    {
        if(klassenName == null){
            return false;
        }


        try {
            methodList = new ArrayList<Method>();
            Method[] methods = Class.forName(klassenName).getDeclaredMethods();
            for( Method m : methods) {
                if( m.isAnnotationPresent( FindMe.class ) ) {
                    methodList.add(m);
                    System.out.print(Modifier.toString(m.getModifiers()));

                    Class[] pTypes = m.getParameterTypes();

                    for(Class parameterType: pTypes){
                        System.out.print(" ");
                        System.out.print(parameterType.getName());

                    }
                    System.out.print(" ");
                    System.out.print(m.getName());
                    System.out.println();
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Klasse konnte nicht gefunden werden");
            return false;
        }
    }


    public static boolean findAnnotatedFields(String klassenName)
    {
        if(klassenName == null){
            return false;
        }
        
        try {
            fieldList = new ArrayList<Field>();
            Field[]  fields = Class.forName(klassenName).getDeclaredFields();
            for(Field f : fields){
                if( f.isAnnotationPresent( FindMe.class ) ) {
                    fieldList.add(f);

                    System.out.print(Modifier.toString(f.getModifiers()));
                    System.out.print(" ");
                    System.out.print(f.getType());

                    System.out.print(" ");
                    System.out.print(f.getName());
                    System.out.println();
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Klasse konnte nicht gefunden werden");
            return false;
        }
    }

    public static List<Method> getMethodList() {
        return methodList;
    }

    public static List<Field> getFieldList() { return fieldList; }
}
