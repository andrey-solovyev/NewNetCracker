package di;

import Contracts.DigitalTVContract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Injector {

    public static <T> T inject(T object){
        Class<?> classes=object.getClass();
        List<String> packages = getPackages(classes);
        List<Field> injectableFields = getFields(classes);
        for (Field injectableField : injectableFields) {

            Class<?> dependencyClass = getDependencyClass(injectableField);
            List<Class<?>> dependencySubClasses = null;
            try {
                 dependencySubClasses = FindAllClasses.getClasses(packages, dependencyClass);

            } catch (ClassNotFoundException e){
                System.err.println(e);
            }
            inject(injectableField, dependencySubClasses, object);
        }
        return object;
    }
    private static List<Field> getFields(Class<?> classes){
        Field[] declaredFields = classes.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        for (Field field : declaredFields) {
            try {

                Annotation annotation = field.getAnnotation(AutoInjectable.class);
                if (annotation==null){
                    continue;
                }
                if (field.getType().isPrimitive()) {
                    continue;
                }
                fields.add(field);
            } catch (Exception e){
                System.err.println(e);
            }
        }
        return fields;
    }


    private static List<String> getPackages(Class<?> classes) {
        Configuration configurationAnnotation = classes.getAnnotation(Configuration.class);
        return Arrays.asList(configurationAnnotation.packages());
    }

    private static Class<?> getDependencyClass(Field injectableField) {
        if (Collection.class.isAssignableFrom(injectableField.getType())) {
            ParameterizedType listType = (ParameterizedType) injectableField.getGenericType();
            return (Class<?>) listType.getActualTypeArguments()[0];
        } else {
            return injectableField.getType();
        }
    }


    private static void inject(Field injectableField, List<Class<?>> dependencySubClasses, Object obj) {
        Object value;
        if (Collection.class.isAssignableFrom(injectableField.getType())) {
            value = createNewInstances(dependencySubClasses);
        } else {
            if (dependencySubClasses.size() > 1) {
                throw new RuntimeException("Error with dependency");
            }
            value = createNewInstance(dependencySubClasses.get(0));
        }

        try {
            injectableField.setAccessible(true);
            injectableField.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("check inject field" + injectableField, e);
        }
    }

    /**
     * @param classes листы классов
     * @return {@link java.lang.Object} экземпляры переданных классов
     */
    private static List<Object> createNewInstances(List<Class<?>> classes) {
        List<Object> instances = new ArrayList<>();

        for (Class<?> classe : classes) {
            instances.add(createNewInstance(classe));
        }
        return instances;
    }
    /**
     * @param classes класс для инициализации
     * @return {@link java.lang.Object}
     */
    private static Object createNewInstance(Class<?> classes) {
        try {
            Constructor<?> constructor = classes.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("ERROR"+classes, e);
        }
    }
}
