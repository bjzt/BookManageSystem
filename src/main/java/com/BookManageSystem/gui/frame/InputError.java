package com.BookManageSystem.gui.frame;

        import com.BookManageSystem.util.PanelUtil;

        import javax.swing.*;
        import java.awt.*;

public class InputError extends JDialog {
    JLabel str;
    public InputError(Frame index,String title,boolean modal){
        super(index,title,modal);
        str = new JLabel("账号密码输入错误！");
        str.setFont(new Font("宋体",Font.BOLD,16));

        this.setLayout(null);
        str.setBounds(20,10,160,50);
        this.setSize(200,100);
        this.add(str);
        PanelUtil.SetCenter(this);
        this.setResizable(false);
        this.setVisible(true);
    }
}
