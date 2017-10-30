package Java8Book;

import java.beans.PropertyDescriptor;
import java.beans.*;

/**
 * Created by petec on 7/16/16.
 */

class ColorsBeanInfo extends SimpleBeanInfo {
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor rectangular = new
                    PropertyDescriptor("rectangular", Colors.class);
            PropertyDescriptor pd[] = { rectangular };
            return pd;
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
        return null;
    }
}

