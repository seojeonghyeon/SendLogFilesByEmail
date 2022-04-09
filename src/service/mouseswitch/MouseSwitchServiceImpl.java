package service.mouseswitch;

import dto.EmailDto;
import dto.JPanelPackageDto;
import dto.TextFieldDto;
import service.email.EmailService;
import service.email.EmailServiceImpl;
import service.savefile.SaveFileService;
import service.savefile.SaveFileServiceImpl;

import javax.swing.*;

public class MouseSwitchServiceImpl implements MouseSwitchService{
    private volatile static MouseSwitchServiceImpl uniqueInstance;

    private SaveFileService saveFileService;
    private EmailService emailService;

    private JPanelPackageDto jPanelPackageDto;
    private JLabel[] jLabels;
    private JTextField[] jTextFields;
    private JCheckBox[] jCheckBoxes;

    public static MouseSwitchServiceImpl getInstance(){
        if(uniqueInstance == null){
            synchronized (MouseSwitchServiceImpl.class){
                if(uniqueInstance == null) uniqueInstance = new MouseSwitchServiceImpl();
            }
        }
        return uniqueInstance;
    }

    @Override
    public void switchService(int number, JPanelPackageDto jPanelPackageDto) {
        this.jPanelPackageDto = jPanelPackageDto;
        this.jLabels = jPanelPackageDto.getjLabels();
        this.jTextFields = jPanelPackageDto.getjTextFields();
        this.jCheckBoxes = jPanelPackageDto.getjCheckBoxes();

        //1 : Search
        //2 : Load
        //3 : Play
        //4 : Pause
        if(number == 1){
            searchLogFiles();
        }else if(number == 2){
            loadSaveFile();
        }else if(number == 3){
            playSendEmail();
        }else if(number == 4){
            saveSaveFile();
        }
    }

    private void searchLogFiles(){

    }

    private void loadSaveFile(){
        saveFileService = SaveFileServiceImpl.getInstance();
        TextFieldDto[] textFieldDtos = saveFileService.readSaveFileForTextField(jTextFields.length);
        for(int i=0; i<jTextFields.length; ++i) jTextFields[i].setText(textFieldDtos[i].getTextValue());
    }

    private void playSendEmail(){
        String[] filePaths = new String[1];
        filePaths[0] = jTextFields[0].getText();
        EmailDto emailDto = new EmailDto(
                jTextFields[1].getText(), jTextFields[2].getText(),
                jTextFields[3].getText(), filePaths, "Regular"
        );
        emailService = EmailServiceImpl.getInstance(emailDto);
    }

    private void saveSaveFile(){
        saveFileService = SaveFileServiceImpl.getInstance();
        TextFieldDto[] textFieldDtos = new TextFieldDto[jTextFields.length];
        for(int i=0; i<jTextFields.length; ++i) textFieldDtos[i] = new TextFieldDto(jTextFields[i].getText());
        saveFileService.writeSaveFileForTextField(textFieldDtos);
    }
}
