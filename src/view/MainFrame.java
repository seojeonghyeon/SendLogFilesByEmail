package view;

import controller.FileController;
import dto.TextFieldDto;
import dto.TextLabelDto;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private volatile static MainFrame uniqueInstance;
    private JFrame mainFrame;
    private Image logImage;
    private Container contentPane;

    private FileController fileController;

    private static final int defaultWindowSizeWidth = 500;
    private static final int defaultWindowSizeHeight = 500;

    private static final String defaultFontName = "Courier New";
    private static final Color defaultFontColor = Color.BLACK;
    private static final int defaultFontSize = 12;
    private static final int titleFontSize = 16;

    private static final String titleName = "Send Log Files By Email";
    private static final String logoIconLocation = "/resource/profile.jpeg";

    private static final int countTextField = 5;

    private TextLabelDto[] textLabelDtos;
    private int[][] textFieldSet;
    private TextFieldDto[] textFieldDtos;

    private MainFrame(){
        try {
            fileController = new FileController();
            textFieldDtos = fileController.readSaveFile(countTextField);
            wait(1000);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("hi");
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

        textLabelDtos = new TextLabelDto[]{
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

        // locationX, locationY, sizeWidth, sizeHeight
        textFieldSet = new int[][]{
                new int[]{ 100, 50, 800, 20 },
                new int[]{ 100, 100, 800, 20 },
                new int[]{ 100, 150, 800, 20 },
                new int[]{ 100, 200, 800, 20 },
                new int[]{ 60, 250, 800, 20}
        };

        for(int i=0; i<countTextField; ++i){
            textFieldDtos[i].setTextFieldDtoExceptTextValue(
                    textFieldSet[i][0], textFieldSet[i][1], textFieldSet[i][2], textFieldSet[i][3],
                    defaultFontName, defaultFontSize, defaultFontColor);
            System.out.println(textFieldDtos[i].toString());
        }

        for (int i=0; i<textLabelDtos.length; ++i){
            JLabel jLabel = createLabel(textLabelDtos[i]);
            contentPane.add(jLabel);
        }

        for (int i=0; i<textFieldDtos.length; ++i){
            JTextField jTextField = createTextField(textFieldDtos[i]);
            contentPane.add(jTextField);
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

    private JTextField createTextField(TextFieldDto textFieldDto){
        JTextField jTextField = new JTextField();
        jTextField.setText(textFieldDto.getTextValue());
        jTextField.setLocation(textFieldDto.getLocationX(), textFieldDto.getLocationY());
        jTextField.setSize(textFieldDto.getSizeWidth(), textFieldDto.getSizeHeight());
        jTextField.setFont(new Font(textFieldDto.getTextFont(), Font.PLAIN, textFieldDto.getTextFontSize()));
        return jTextField;
    }

}
