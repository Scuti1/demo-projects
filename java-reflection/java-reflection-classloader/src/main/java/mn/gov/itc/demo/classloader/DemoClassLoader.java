package mn.gov.itc.demo.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Өөрсдийн тодорхойлсон classloader.
 */
public class DemoClassLoader extends URLClassLoader {

    /**
     * @param urls параметерд тодорхойлсон jar file-с классуудыг load хийнэ.
     * @param parent параметерд эцэг classloader-г тодорхойлно. хэрэв load хийх
     *               класс нь олдоогүй бол эцэг classloader-с тухайн классыг
     *               хайна.
     */
    public DemoClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    /**
     * класс хайх, load хийх үед уг method-г дуудана.
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = super.loadClass(name, resolve);

        // Уг функийг ажиллаж буй үйлдэлийг харахын тулд хэвлэж үзэв.
        System.err.println("DemoCL: " + c.getName());
        return c;
    }
}
