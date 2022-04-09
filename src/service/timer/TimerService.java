package service.timer;


import dto.CheckBoxDto;
import dto.JPanelPackageDto;

public interface TimerService {
    public void startEmailSend(JPanelPackageDto jPanelPackageDto);
    public void stopEmailSend();
}
