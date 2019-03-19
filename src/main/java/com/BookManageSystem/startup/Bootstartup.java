package com.BookManageSystem.startup;

import com.BookManageSystem.gui.frame.Login;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstartup {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                Login.getInstance();
            }
        });
    }
}
