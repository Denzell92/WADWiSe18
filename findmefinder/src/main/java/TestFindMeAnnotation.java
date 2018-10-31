import de.htw.ai.wad.findmefinder.FindMe;

import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy;

import java.lang.reflect.Field; import java.lang.reflect.Method;

import java.lang.reflect.Modifier;


    public class TestFindMeAnnotation {
        @FindMe
        public static int a;

        @FindMe
        public final String finalString = "Hello";

        @FindMe
        private double doubleValue;

        @FindMe
        public static void meineMethodeMitAnnotation(int a) {
            //...
        }

        @FindMe
        public void meineMdd(int a) {
            //...
        }

        @FindMe
        private String stringMethode(String beispielString){
            //..
            return null;
        }

        @FindMe
        public static void staticMethode()
        {
            //..
        }



    }




