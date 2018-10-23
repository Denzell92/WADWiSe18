import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FindMeFinder {

    public static void main( String[] args ) throws Exception
    {
        Method[] methods = TestFindMeAnnotation.class.getMethods();
        Field[]  fields = TestFindMeAnnotation.class.getFields();

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


        System.out.print("Mit @FindMe annotierte Fields:");
        for(Field f : fields){
            if( f.isAnnotationPresent( FindMe.class ) ) {

                System.out.println("");

                System.out.print(Modifier.toString(f.getModifiers()));
                System.out.print(f.getType());

                System.out.print(" ");
                System.out.print(f.getName());
            }
        }





    }

}
