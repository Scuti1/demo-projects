package mn.gov.itc.demo.classloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Throwable {
        String workingDirectory = System.getProperty("user.dir");
        String jarFile = "file://" + workingDirectory + File.separator + "java-reflection" + File.separator + "java-reflection-proxy" + File.separator + "target" + File.separator + "java-reflection-proxy-1.0-SNAPSHOT.jar";
        System.out.println("jarFile = " + jarFile);

        Thread.currentThread().setContextClassLoader(new DemoClassLoader(new URL[]{new URL(jarFile)}, Thread.currentThread().getContextClassLoader()));

        Class<?> mainClass = Thread.currentThread().getContextClassLoader().loadClass("mn.gov.itc.demo.reflection.Main");
        System.out.println("mainClass = " + mainClass);
        Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
        System.out.println("mainMethod = " + mainMethod);

        mainMethod.invoke(mainClass, new Object[]{args});
    }
}
