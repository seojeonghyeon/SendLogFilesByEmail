package service.mouseswitch;

import dto.JPanelPackageDto;
import dto.TextFieldDto;
import service.savefile.SaveFileService;
import service.savefile.SaveFileServiceImpl;

import javax.swing.*;

public class MouseSwitchServiceImpl implements MouseSwitchService{
    private volatile static MouseSwitchServiceImpl uniqueInstance;

    private SaveFileService saveFileService;

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
        jLabels = jPanelPackageDto.getjLabels();
        jTextFields = jPanelPackageDto.getjTextFields();
        jCheckBoxes = jPanelPackageDto.getjCheckBoxes();

        //1 : Search
        //2 : Load
        //3 : Play
        //4 : Pause
        if(number == 1){

        }else if(number == 2){
            loadSaveFile();
        }else if(number == 3){

        }else if(number == 4){
            saveSaveFile();
        }
    }

    private void loadSaveFile(){
        saveFileService = SaveFileServiceImpl.getInstance();
        TextFieldDto[] textFieldDtos = saveFileService.readSaveFileForTextField(jTextFields.length);
        for(int i=0; i<jTextFields.length; ++i) {
            System.out.println(textFieldDtos[i].toString());
            jTextFields[i].setText(textFieldDtos[i].getTextValue());
        }
    }

    private void saveSaveFile(){
        saveFileService = SaveFileServiceImpl.getInstance();
        TextFieldDto[] textFieldDtos = new TextFieldDto[jTextFields.length];
        for(int i=0; i<jTextFields.length; ++i) textFieldDtos[i] = new TextFieldDto(jTextFields[i].getText());
        saveFileService.writeSaveFileForTextField(textFieldDtos);
    }
}
