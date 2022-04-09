package listener.basic;

import dto.JPanelPackageDto;
import service.mouseswitch.MouseSwitchService;
import service.mouseswitch.MouseSwitchServiceImpl;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BasicMouseListener implements MouseListener {

    private volatile static BasicMouseListener uniqueInstance;
    private JLabel[] jLabels;
    private JTextField[] jTextFields;
    private JCheckBox[] jCheckBoxes;
    private MouseSwitchService mouseSwitchService;
    private JPanelPackageDto jPanelPackageDto;

    public static BasicMouseListener getInstance(JPanelPackageDto jPanelPackageDto){
        if(uniqueInstance == null){
            synchronized (BasicMouseListener.class){
                if(uniqueInstance == null) uniqueInstance = new BasicMouseListener(jPanelPackageDto);
            }
        }
        return uniqueInstance;
    }

    private BasicMouseListener(JPanelPackageDto jPanelPackageDto){
        mouseSwitchService = MouseSwitchServiceImpl.getInstance();

        this.jPanelPackageDto = jPanelPackageDto;
        this.jLabels = jPanelPackageDto.getjLabels();
        this.jTextFields = jPanelPackageDto.getjTextFields();
        this.jCheckBoxes = jPanelPackageDto.getjCheckBoxes();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton jButton = (JButton) e.getSource();
        int mouseSwitch = switch (jButton.getText()){
            case "Search" -> 1;
            case "Load" -> 2;
            case "Play" -> 3;
            case "Pause" -> 4;
            default -> 5;
        };
        mouseSwitchService.switchService(mouseSwitch, jPanelPackageDto);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
