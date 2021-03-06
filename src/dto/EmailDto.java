package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class EmailDto {
    private String sendEmail;
    private String sendEmailPassword;
    private String receiveEmail;

    private String titleText;
    private String bodyText;
    private LinkedList<String> getPaths;
    private String logProperty;

    public EmailDto(String sendEmail, String sendEmailPassword, String receiveEmail, LinkedList<String> getPaths, String logProperty){
        this.sendEmail = sendEmail;
        this.sendEmailPassword = sendEmailPassword;
        this.receiveEmail = receiveEmail;
        this.getPaths = getPaths;
        this.logProperty = logProperty;

        LocalDate nowDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalTime nowTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");

        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(
                "["+logProperty+"] 로그정보("+ nowDate+" "+ nowTime.format(dateTimeFormatter)+")"
        );
        this.titleText = stringBuilderTitle.toString();

        StringBuilder stringBuilderBody = new StringBuilder();
        stringBuilderBody.append(
                "<h2>"+nowDate+" "+nowTime.format(dateTimeFormatter)+"(한국 시간 기준) Log Files<h2>"+
                        "<h4>[정기] : 정기적인 수신<h4>" +
                        "<h4>[이벤트] : 이벤트성 수신<h4>"
        );
        this.bodyText = stringBuilderBody.toString();

    }

    public String getSendEmail() {
        return sendEmail;
    }

    public String getBodyText() {
        return bodyText;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public String getSendEmailPassword() {
        return sendEmailPassword;
    }

    public String getTitleText() {
        return titleText;
    }

    public LinkedList<String> getGetPaths() {
        return getPaths;
    }

    public String getLogProperty() {
        return logProperty;
    }
}
