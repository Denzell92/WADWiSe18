package de.htw.ai.wad.findmefinder;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FindMeFinder {

    public static void main( String[] args ) throws Exception
    {
        if (args.length<=0)
        {
            System.out.println("Sie muessen einen Parameter uebergeben!");
        } else {
            String klassenName = args[0];
            System.out.println("Die untersuchte Klasse: " + klassenName);
            System.out.println();
            System.out.println("Mit findMe annotierte Methoden:");
            Finder.findAnnotatedMethods(klassenName);
            System.out.println();
            System.out.println("Mit findMe annotierte Felder:");
            Finder.findAnnotatedFields(klassenName);
        }




        /*if(args.length <= 0){
            System.out.print("Sie muessen einen Parameter uebergeben!");
            return;
        }

        String klassenName = args[0];

        if(klassenName == "" || klassenName == null || Class.forName(klassenName) == null){
            System.out.print("Der Name der Klasse ist nicht korrekt!");
            return;
        }

        System.out.println("Die untersuchte Klasse: " + klassenName);

        //Method[] methods = TestFindMeAnnotation.class.getMethods();
        //Field[]  fields = TestFindMeAnnotation.class.getFields();

        Method[] methods = Class.forName(klassenName).getMethods();
        Field[]  fields = Class.forName(klassenName).getFields();

        System.out.println("");
        System.out.print("Mit @FindMe annotierte Methoden:");
        for( Method m : methods ) {
            if( m.isAnnotationPresent( FindMe.class ) ) {

                System.out.println("");

                System.out.print(Modifier.toString(m.getModifiers()));

                Class[] pTypes = m.getParameterTypes();

                for(Class parameterType: pTypes){
                    System.out.print(" ");
                    System.out.print(parameterType.getName());

                }
                System.out.print(" ");
                System.out.print(m.getName());

            }
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Mit @FindMe annotierte Fields:");
        for(Field f : fields){
            if( f.isAnnotationPresent( FindMe.class ) ) {

                System.out.println("");

                System.out.print(Modifier.toString(f.getModifiers()));
                System.out.print(" ");
                System.out.print(f.getType());

                System.out.print(" ");
                System.out.print(f.getName());
            }
        }*/





    }

}
