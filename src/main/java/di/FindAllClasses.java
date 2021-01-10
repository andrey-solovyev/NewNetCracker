package di;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindAllClasses {
    public static List<Class<?>> loadClasses(String packageName)
            throws ClassNotFoundException {
        List<Class<?>> classes = new LinkedList<>();
        URL resource = Thread.currentThread()
                .getContextClassLoader()
                .getResource(packageName.replace('.', '/'));
        File directory
                = new File(resource.getFile());
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return classes;
        }
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                classes.add(Class
                        .forName(String.format("%s.%s",
                                packageName,
                                file.getName().substring(0, file.getName().indexOf(".")))));
            }
        }
        return classes;
    }

    public static boolean checkAllParents(Class<?> type, Class<?> hasParentType) {
        Class<?> parent = type.getSuperclass();
        if (parent == Object.class) {
            return false;
        }
        if (parent.getClass() == hasParentType.getClass()) {
            return true;
        }
        return checkAllParents(parent, hasParentType);
    }

    public static List<Class<?>> getClasses(List<String> packages, Class<?> classs) throws ClassNotFoundException {
        List<Class<?>> result =new LinkedList<>();
        for (String packagee:packages){
            result.addAll(loadClasses(packagee).stream()
                    .filter(i -> checkAllParents(i, classs.getClass()))
                    .collect(Collectors.toList()));
        }
        return result;
    }
}
