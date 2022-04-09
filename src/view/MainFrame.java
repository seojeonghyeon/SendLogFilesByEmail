package view;

import dto.ButtonDto;
import dto.CheckBoxDto;
import dto.TextFieldDto;
import dto.TextLabelDto;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private volatile static MainFrame uniqueInstance;
    private MainPanel mainPanel;
    private Image logImage;

    private static final int defaultWindowSizeWidth = 500;
    private static final int defaultWindowSizeHeight = 500;

    private static final String titleName = "Send Log Files By Email";
    private static final String logoIconLocation = "/resource/profile.jpeg";

    private MainFrame(){
        createMainFrame();
        mainPanel = MainPanelImpl.getInstance(getContentPane());
        mainPanel.createMainPanel();

        setVisible(true);
    }

    public static MainFrame getInstance(){
        if(uniqueInstance == null){
            synchronized (MainFrame.class){
                if(uniqueInstance == null) uniqueInstance = new MainFrame();
            }
        }
        return uniqueInstance;
    }

    private void createMainFrame(){
        setTitle(titleName);
        setSize(defaultWindowSizeWidth, defaultWindowSizeHeight);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        logImage = toolkit.getImage(logoIconLocation);
        setIconImage(logImage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
