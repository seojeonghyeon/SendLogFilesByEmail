package service.timer;

import dto.JPanelPackageDto;

public interface TimerService {
    public void startEmailSend(JPanelPackageDto jPanelPackageDto);
    public void stopEmailSend();
}
