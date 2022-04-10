package service.savefile;

import dto.TextFieldDto;
import java.io.*;

public class SaveFileServiceImpl implements SaveFileService {
    private static final String saveFileTextFieldLocation = "src/resource/savefile.txt";

    private volatile static SaveFileServiceImpl uniqueInstance;

    public static SaveFileServiceImpl getInstance(){
        if(uniqueInstance == null){
            synchronized (SaveFileServiceImpl.class){
                if(uniqueInstance == null) uniqueInstance = new SaveFileServiceImpl();
            }
        }
        return uniqueInstance;
    }

    @Override
    public TextFieldDto[] readSaveFileForTextField(int countTextField) {
        return getSaveFileForTextField(countTextField);
    }

    private TextFieldDto[] getSaveFileForTextField(int countTextField){
        TextFieldDto[] textFieldDtos = new TextFieldDto[countTextField];
        for(int i=0; i<countTextField; ++i) textFieldDtos[i]= new TextFieldDto("");
        try {
            File file = new File(saveFileTextFieldLocation);
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            int i=1;
            while((line = bufferedReader.readLine()) != null){
                textFieldDtos[i].setTextValue(line);
                ++i;
            }
        }catch (FileNotFoundException e){
        }catch (IOException e){
            System.out.println(e);
        }
        return textFieldDtos;
    }

    @Override
    public void writeSaveFileForTextField(TextFieldDto[] textFieldDtos) {
        setSaveFileForTextField(textFieldDtos);
    }

    private void setSaveFileForTextField(TextFieldDto[] textFieldDtos){
        try {
            File file = new File(saveFileTextFieldLocation);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            if(!file.exists()) file.createNewFile();

            if(file.isFile() && file.canWrite()){

                for(int i=1; i<textFieldDtos.length; ++i){
                    bufferedWriter.write(textFieldDtos[i].getTextValue());
                    if(i != textFieldDtos.length-1) bufferedWriter.newLine();
                }

                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
