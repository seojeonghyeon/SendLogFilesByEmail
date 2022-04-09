package service.timer;

import dto.EmailDto;
import dto.JPanelPackageDto;
import service.email.EmailService;
import service.email.EmailServiceImpl;
import service.timer.TimerService;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerServiceImpl implements TimerService {

    private volatile static TimerServiceImpl uniqueInstance;

    private EmailService emailService;

    private JPanelPackageDto jPanelPackageDto;
    private JLabel[] jLabels;
    private JTextField[] jTextFields;
    private JCheckBox[] jCheckBoxes;
    private JButton[] jButtons;

    private TimerTask timerTask;
    private Timer timer;
    private LocalDate nextDate;
    private LocalTime nextTime;
    private DateTimeFormatter dateTimeFormatter;

    public static TimerServiceImpl getInstance(){
        if(uniqueInstance == null){
            synchronized (TimerServiceImpl.class){
                if(uniqueInstance == null) uniqueInstance = new TimerServiceImpl();
            }
        }
        return uniqueInstance;
    }

    public TimerServiceImpl(){
        emailService = EmailServiceImpl.getInstance();
    }

    @Override
    public void startEmailSend(JPanelPackageDto jPanelPackageDto) {
        this.jPanelPackageDto = jPanelPackageDto;
        this.jLabels = jPanelPackageDto.getjLabels();
        this.jTextFields = jPanelPackageDto.getjTextFields();
        this.jCheckBoxes = jPanelPackageDto.getjCheckBoxes();
        this.jButtons = jPanelPackageDto.getjButtons();
        nextDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");

        if(jCheckBoxes[0].isSelected() && jCheckBoxes[1].isSelected()) {

        }else if(jCheckBoxes[0].isSelected() && !jCheckBoxes[1].isSelected()){
            nextTime = LocalTime.now().plusMinutes(5);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(nextTime.format(dateTimeFormatter));
            jLabels[jLabels.length-1].setText(stringBuilder.toString());

            timerTask = new TimerTask() {
                @Override
                public void run() {
                    sendEmail();

                    nextTime = LocalTime.now();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(nextTime.format(dateTimeFormatter));
                    jLabels[jLabels.length-1].setText(stringBuilder.toString());
                }
            };
            timer = new Timer("Timer");
            int delay = 300000;
            int period = 3600000*Integer.parseInt(jTextFields[jTextFields.length-1].getText());
            timer.schedule(timerTask, delay, period);
        }else if(!jCheckBoxes[0].isSelected() && jCheckBoxes[1].isSelected()){

        }
    }

    private void sendEmail(){
        LinkedList<String> filePaths = new LinkedList<>();
        filePaths.add(jTextFields[0].getText());
        EmailDto emailDto = new EmailDto(
                jTextFields[1].getText(), jTextFields[2].getText(),
                jTextFields[3].getText(), filePaths, "Regular"
        );
        emailService = EmailServiceImpl.getInstance();
        emailService.sendEmail(emailDto);
    }

    @Override
    public void stopEmailSend() {
        timer.cancel();
    }
}
