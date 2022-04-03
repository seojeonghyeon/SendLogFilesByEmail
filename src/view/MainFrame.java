package view;

import dto.TextLabelDto;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private volatile static MainFrame uniqueInstance;
    private JFrame mainFrame;
    private Image logImage;
    private Container contentPane;

    private static final int defaultWindowSizeWidth = 500;
    private static final int defaultWindowSizeHeight = 500;

    private static final String defaultFontName = "Courier New";
    private static final Color defaultFontColor = Color.BLACK;
    private static final int defaultFontSize = 12;
    private static final int titleFontSize = 16;

    private static final String titleName = "Send Log Files By Email";
    private static final String logoIconLocation = "/resource/profile.jpeg";

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
        setTitle(titleName);
        setSize(defaultWindowSizeWidth, defaultWindowSizeHeight);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        logImage = toolkit.getImage(logoIconLocation);
        setIconImage(logImage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMainPanel(){
        contentPane = getContentPane();
        contentPane.setLayout(null);

        TextLabelDto[] textLabels = new TextLabelDto[]{
                new TextLabelDto("Location    : ", 30, 50, 800, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("송신 메일 주소 : ", 30, 100, 800, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("Password    : ", 30, 150, 800, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("수신 메일 주소 : ", 30, 200, 800, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("시간마다 메일 전송", 100, 250, 800, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("이슈 발생시, 메일 전송(에러, 종료)", 100, 300, 800, 20,
                        defaultFontName, defaultFontSize, defaultFontColor)
        };

        for (int i=0; i<textLabels.length; ++i){
            JLabel jLabel = createLabel(textLabels[i]);
            System.out.println(textLabels[i].toString());
            contentPane.add(jLabel);
        }


        setVisible(true);
    }
    private JLabel createLabel(TextLabelDto textLabel){
        JLabel jLabel = new JLabel(textLabel.getText());
        jLabel.setLocation(textLabel.getLocationX(), textLabel.getLocationY());
        jLabel.setSize(textLabel.getSizeWidth(), textLabel.getSizeHeight());
        jLabel.setFont(new Font(textLabel.getTextFont(), Font.PLAIN, textLabel.getTextFontSize()));
        return jLabel;
    }

}
