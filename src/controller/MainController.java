package controller;

import view.MainFrame;

public class MainController {

    MainFrame mainFrame;

    public void access(){
        createGUI();
    }
    private void createGUI(){
        mainFrame = MainFrame.getInstance();
    }

}
