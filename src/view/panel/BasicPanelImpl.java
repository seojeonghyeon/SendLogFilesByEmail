package view.panel;

import dto.*;
import listener.basic.BasicMouseListener;

import javax.swing.*;
import java.awt.*;

public class BasicPanelImpl implements BasicPanel {

    private volatile static BasicPanelImpl uniqueInstance;
    private Container container;

    private static final int titleFontSize = 16;
    private static final String defaultFontName = "Courier New";
    private static final Color defaultFontColor = Color.BLACK;
    private static final int defaultFontSize = 12;
    private static final int countTextField = 5;
    private static final String versionName = "v0.0.1";

    private TextLabelDto[] textLabelDtos;
    private TextFieldDto[] textFieldDtos;
    private ButtonDto[] buttonDtos;
    private CheckBoxDto[] checkBoxDtos;

    private JTextField[] jTextFields;
    private JCheckBox[] jCheckBoxes;
    private JLabel[] jLabels;
    private JButton[] jButtons;
    private JPanelPackageDto jPanelPackageDto;

    public static BasicPanelImpl getInstance(Container container){
        if(uniqueInstance == null){
            synchronized (BasicPanelImpl.class){
                if(uniqueInstance == null) {
                    uniqueInstance = new BasicPanelImpl();
                    uniqueInstance.container = container;
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void createMainPanel() {
        container.setLayout(null);
        createLabel();
        createTextField();
        createCheckBox();
        createButton();
    }

    private void createCheckBox(){
        checkBoxDtos = new CheckBoxDto[]{
                new CheckBoxDto(true, 30, 250, 25, 20),
                new CheckBoxDto(false, 30, 300, 25, 20)
        };

        jCheckBoxes = new JCheckBox[checkBoxDtos.length];

        for (int i=0; i<checkBoxDtos.length; ++i){
            jCheckBoxes[i] =  createCheckBox(checkBoxDtos[i]);
            container.add(jCheckBoxes[i]);
        }
    }

    private JCheckBox createCheckBox(CheckBoxDto checkBoxDto){
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(checkBoxDto.isCheckBoxTrue());
        jCheckBox.setLocation(checkBoxDto.getLocationX(), checkBoxDto.getLocationY());
        jCheckBox.setSize(checkBoxDto.getSizeWidth(), checkBoxDto.getSizeHeight());
        return jCheckBox;
    }

    private void createButton(){
        buttonDtos = new ButtonDto[]{
                new ButtonDto("Search", 410, 50, 50, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new ButtonDto("Load", 100, 350, 50, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new ButtonDto("Play", 225, 350, 50, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new ButtonDto("Pause", 350, 350, 50, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
        };
        jButtons = new JButton[buttonDtos.length];

        for (int i=0; i<buttonDtos.length; ++i){
            jButtons[i] = createButton(buttonDtos[i]);
            container.add(jButtons[i]);
        }

        jPanelPackageDto = new JPanelPackageDto(jLabels, jTextFields, jCheckBoxes);
        jPanelPackageDto.setjButtons(jButtons);

        for (int i=0; i<jButtons.length; ++i)
            jButtons[i].addMouseListener(BasicMouseListener.getInstance(jPanelPackageDto));

    }
    private JButton createButton(ButtonDto buttonDto){
        JButton jButton = new JButton();
        jButton.setText(buttonDto.getButtonTextValue());
        jButton.setLocation(buttonDto.getLocationX(), buttonDto.getLocationY());
        jButton.setSize(buttonDto.getSizeWidth(), buttonDto.getSizeHeight());
        jButton.setFont(new Font(buttonDto.getTextFont(), Font.PLAIN, buttonDto.getTextFontSize()));
        return jButton;
    }

    private void createLabel(){
        textLabelDtos = new TextLabelDto[]{
                new TextLabelDto("Location    : ", 30, 50, 100, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("송신 메일 주소 : ", 30, 100, 100, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("Password    : ", 30, 150, 100, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("수신 메일 주소 : ", 30, 200, 100, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("시간마다 메일 전송", 100, 250, 100, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("이슈 발생시, 메일 전송(에러, 종료)", 60, 300, 200, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto(versionName, 450, 450, 50, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextLabelDto("다음 전송 시간은 ", 140, 400, 100, 20,
                        defaultFontName, defaultFontSize, Color.BLUE),
                new TextLabelDto("2022.04.05 00:00", 250, 400, 120, 20,
                        defaultFontName, defaultFontSize, Color.BLUE)
        };
        jLabels = new JLabel[textLabelDtos.length];

        for (int i=0; i<textLabelDtos.length; ++i){
            jLabels[i] = createLabel(textLabelDtos[i]);
            container.add(jLabels[i]);
        }
    }

    private JLabel createLabel(TextLabelDto textLabel){
        JLabel jLabel = new JLabel(textLabel.getText());
        jLabel.setOpaque(true);
        jLabel.setForeground(textLabel.getTextFontColor());
        jLabel.setLocation(textLabel.getLocationX(), textLabel.getLocationY());
        jLabel.setSize(textLabel.getSizeWidth(), textLabel.getSizeHeight());
        jLabel.setFont(new Font(textLabel.getTextFont(), Font.PLAIN, textLabel.getTextFontSize()));
        return jLabel;
    }

    private void createTextField(){
        textFieldDtos = new TextFieldDto[]{
                new TextFieldDto("", 150, 50, 250, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextFieldDto("", 150, 100, 250, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextFieldDto("", 150, 150, 250, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextFieldDto("", 150, 200, 250, 20,
                        defaultFontName, defaultFontSize, defaultFontColor),
                new TextFieldDto("", 60, 250, 40, 20,
                        defaultFontName, defaultFontSize, defaultFontColor)
        };

        jTextFields = new JTextField[textFieldDtos.length];

        for (int i=0; i<textFieldDtos.length; ++i){
            jTextFields[i] = createTextField(textFieldDtos[i]);
            container.add(jTextFields[i]);
        }
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
