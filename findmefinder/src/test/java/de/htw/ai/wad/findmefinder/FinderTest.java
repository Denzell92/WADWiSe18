package de.htw.ai.wad.findmefinder;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class FinderTest {

    @org.junit.Test
    public void findAnnotatedMethodsLengthTest() {
        Finder.findAnnotatedMethods("de.htw.ai.wad.findmefinder.TestFindMeAnnotation");
        List<Method> methods = Finder.getMethodList();
        int actual = methods.size();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void findAnnotatedFieldsLengthTest() {
        Finder.findAnnotatedFields("de.htw.ai.wad.findmefinder.TestFindMeAnnotation");
        List<Field> fields = Finder.getFieldList();
        int actual = fields.size();
        int expected = 3;
        assertEquals(expected,actual);
    }

    @Test
    public void findAnnotatedMethodsClassNotLoadedTest() {
        boolean actual = Finder.findAnnotatedMethods(null);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void findAnnotatedMethodsWrongClassLoadedTest() {
        boolean actual = Finder.findAnnotatedMethods("de.htw.ai.wad.findmefinder.WrongFile");
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void findAnnotatedMethodsSucsessfully() {
        boolean actual = Finder.findAnnotatedMethods("de.htw.ai.wad.findmefinder.TestFindMeAnnotation");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void findAnnotatedFieldsClassNotLoadedTest() {
        boolean actual = Finder.findAnnotatedMethods(null);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void findAnnotatedFieldsWrongClassLoadedTest() {
        boolean actual = Finder.findAnnotatedFields("de.htw.ai.wad.findmefinder.WrongFile");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void findAnnotatedFieldsSucsessfully() {
        boolean actual = Finder.findAnnotatedMethods("de.htw.ai.wad.findmefinder.TestFindMeAnnotation");
        boolean expected = true;
        assertEquals(expected, actual);
    }
}