package view.dialog;

import javax.swing.*;
import java.io.File;

public class SearchDialog extends JFrame {
    private volatile static SearchDialog uniqueInstance;

    private JFileChooser jFileChooser;
    private String openPath;

    public static SearchDialog getInstance(){
        if(uniqueInstance == null){
            synchronized (SearchDialog.class){
                if(uniqueInstance == null) uniqueInstance = new SearchDialog();
            }
        }
        return uniqueInstance;
    }

    public String getOpenPath(){
        openPath="";
        jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showDialog(this, null);

        File dir = jFileChooser.getSelectedFile();
        if(dir != null) openPath = dir.getPath();
        return openPath;
    }

}
