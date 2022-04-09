package service.mouseswitch;

import dto.EmailDto;
import dto.JPanelPackageDto;
import dto.TextFieldDto;
import service.email.EmailService;
import service.email.EmailServiceImpl;
import service.savefile.SaveFileService;
import service.savefile.SaveFileServiceImpl;
import service.timer.TimerService;
import service.timer.TimerServiceImpl;
import view.dialog.SearchDialog;
import javax.swing.*;
import java.util.LinkedList;

public class MouseSwitchServiceImpl implements MouseSwitchService{
    private volatile static MouseSwitchServiceImpl uniqueInstance;

    private SaveFileService saveFileService;
    private SearchDialog searchDialog;
    private TimerService timerService;

    private JPanelPackageDto jPanelPackageDto;
    private JLabel[] jLabels;
    private JTextField[] jTextFields;
    private JCheckBox[] jCheckBoxes;
    private JButton[] jButtons;

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
        this.jButtons = jPanelPackageDto.getjButtons();

        if(number == 1) searchLogFiles();
        else if(number == 2) loadSaveFile();
        else if(number == 3) playSendEmail();
        else if(number == 4) pauseSendEmail();
    }

    private void searchLogFiles(){
        searchDialog = SearchDialog.getInstance();
        String getOpenPathName = searchDialog.getOpenPath();
        jTextFields[0].setText(getOpenPathName);
    }


    private void loadSaveFile(){
        saveFileService = SaveFileServiceImpl.getInstance();
        TextFieldDto[] textFieldDtos = saveFileService.readSaveFileForTextField(jTextFields.length);
        for(int i=0; i<jTextFields.length; ++i) jTextFields[i].setText(textFieldDtos[i].getTextValue());
    }

    private void playSendEmail(){
        playButtonDisabled();
        timerService = TimerServiceImpl.getInstance();
        timerService.startEmailSend(jPanelPackageDto);
    }

    private void playButtonDisabled(){
        for(int i=0; i<jButtons.length-1; ++i)
            jButtons[i].setEnabled(false);
    }

    private void pauseButtonDisabled(){
        for(int i=0; i<jButtons.length-1; ++i)
            jButtons[i].setEnabled(true);
    }

    private void pauseSendEmail(){
        pauseButtonDisabled();
        saveSaveFile();
        timerService = TimerServiceImpl.getInstance();
        timerService.stopEmailSend();
    }

    private void saveSaveFile(){
        saveFileService = SaveFileServiceImpl.getInstance();
        TextFieldDto[] textFieldDtos = new TextFieldDto[jTextFields.length];
        for(int i=0; i<jTextFields.length; ++i) textFieldDtos[i] = new TextFieldDto(jTextFields[i].getText());
        saveFileService.writeSaveFileForTextField(textFieldDtos);
    }
}
