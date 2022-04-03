package view;

import model.TextLabel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private volatile static MainFrame uniqueInstance;
    private JFrame mainFrame;
    private Image logImage;
    private Container contentPane;
    private String defaultFontName = "Courier New";

    private MainFrame(){
        createMainFrame();
        createMainPanel();
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
        setTitle("Send Log Files By Email");
        setSize(800, 600);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        logImage = toolkit.getImage("/resource/profile.jpeg");
        setIconImage(logImage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMainPanel(){
        contentPane = getContentPane();
        contentPane.setLayout(null);

        Image titleImage = new ImageIcon("/resource/profile.jpeg").getImage();

        TextLabel[] textLabels = new TextLabel[]{
                new TextLabel("Location : ", 30, 150, 200, 20,
                        defaultFontName, 20, Color.BLACK),
                new TextLabel("송신 메일 주소 : ", 30, 180, 200, 20,
                        defaultFontName, 20, Color.BLACK),
                new TextLabel("Password : ", 30, 210, 200, 20,
                        defaultFontName, 20, Color.BLACK),
                new TextLabel("수신 메일 주소 : ", 30, 240, 200, 20,
                        defaultFontName, 20, Color.BLACK)
        };

        for (int i=0; i<textLabels.length; ++i){
            JLabel jLabel = createLabel(textLabels[i]);
            System.out.println(textLabels[i].toString());
            contentPane.add(jLabel);
        }


        setVisible(true);
    }
    private JLabel createLabel(TextLabel textLabel){
        JLabel jLabel = new JLabel(textLabel.getText());
        jLabel.setLocation(textLabel.getLocationX(), textLabel.getLocationY());
        jLabel.setSize(textLabel.getSizeWidth(), textLabel.getSizeHeight());
        jLabel.setFont(new Font(textLabel.getTextFont(), Font.PLAIN, textLabel.getTextFontSize()));
        return jLabel;
    }

}
