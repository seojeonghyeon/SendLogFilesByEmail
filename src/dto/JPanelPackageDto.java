package dto;

import javax.swing.*;

public class JPanelPackageDto {
    private JLabel[] jLabels;
    private JTextField[] jTextFields;
    private JCheckBox[] jCheckBoxes;
    private JButton[] jButtons;

    public JPanelPackageDto(JLabel[] jLabels, JTextField[] jTextFields, JCheckBox[] jCheckBoxes){
        this.jLabels = jLabels;
        this.jTextFields = jTextFields;
        this.jCheckBoxes = jCheckBoxes;
    }
    public void setjButtons(JButton[] jButtons){
        this.jButtons=jButtons;
    }
    public JButton[] getjButtons(){
        return jButtons;
    }

    public JLabel[] getjLabels(){
        return this.jLabels;
    }
    public JTextField[] getjTextFields(){
        return this.jTextFields;
    }
    public JCheckBox[] getjCheckBoxes(){
        return this.jCheckBoxes;
    }

}
