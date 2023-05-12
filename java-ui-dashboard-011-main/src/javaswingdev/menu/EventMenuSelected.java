package javaswingdev.menu;

import javax.mail.MessagingException;
import java.awt.*;

public interface EventMenuSelected {

    public void menuSelected(int index, int indexSubMenu) throws MessagingException, AWTException;
}
