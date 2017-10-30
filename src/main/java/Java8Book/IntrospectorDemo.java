package Java8Book;

/**
 * Created by petec on 7/16/16.
 */

import java.beans.*;


public class IntrospectorDemo {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("Java8Book.Colors");
            BeanInfo beanInfo = Introspector.getBeanInfo(c);
            System.out.println("Properties:" );
            PropertyDescriptor propertyDescriptor[] =
                    beanInfo.getPropertyDescriptors();
            for (int i=0; i < propertyDescriptor.length; i++) {
                System.out.println("\t" + propertyDescriptor[i].getName());
            }

            System.out.println("Events: ");
            EventSetDescriptor eventSetDecriptor[] =
                    beanInfo.getEventSetDescriptors();
            for (int i=0; i < eventSetDecriptor.length; i++) {
                System.out.println("\t" + eventSetDecriptor[i].getName());
            }

        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }

}
