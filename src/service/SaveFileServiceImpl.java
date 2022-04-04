package service;

import dto.TextFieldDto;

import java.io.*;

public class SaveFileServiceImpl implements SaveFileService{
    private static final String saveFileTextFieldLocation = "/resource/savefile.txt";

    @Override
    public TextFieldDto[] readSaveFileForTextField(int countTextField) {
        return getSaveFileForTextField(countTextField);
    }

    private TextFieldDto[] getSaveFileForTextField(int countTextField){
        TextFieldDto[] textFieldDtos = new TextFieldDto[countTextField];
        try {
            File file = new File(saveFileTextFieldLocation);
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            int i=0;
            while((line = bufferedReader.readLine()) != null){
                textFieldDtos[i] = new TextFieldDto(line);
                ++i;
            }
        }catch (FileNotFoundException e){
            for(int i=0; i<countTextField; ++i) textFieldDtos[i]= new TextFieldDto("");
        }catch (IOException e){
            System.out.println(e);
            for(int i=0; i<countTextField; ++i) textFieldDtos[i]= new TextFieldDto("");
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
            if(file.isFile() && file.canWrite()){
                for(int i=0; i<textFieldDtos.length; ++i){
                    bufferedWriter.write(textFieldDtos[i].getTextValue());
                    if(i != textFieldDtos.length-1) bufferedWriter.newLine();
                }
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
