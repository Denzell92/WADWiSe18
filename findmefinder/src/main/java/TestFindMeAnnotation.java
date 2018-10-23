import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy;

import java.lang.reflect.Field; import java.lang.reflect.Method;

import java.lang.reflect.Modifier;


    public class TestFindMeAnnotation {
        @FindMe
        public int a;

        @FindMe
        public static void meineMethodeMitAnnotation(int a) {
            //...
        }

        @FindMe
        public void meineMdd(int a) {
            //...
        }
    }




