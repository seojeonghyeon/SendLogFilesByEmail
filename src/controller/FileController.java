package controller;

import dto.TextFieldDto;
import service.savefile.SaveFileService;
import service.savefile.SaveFileServiceImpl;

public class FileController {
    SaveFileService saveFileService;

    public FileController(){
        saveFileService = new SaveFileServiceImpl();
    }

    public TextFieldDto[] readSaveFile(int countTextField){
        return saveFileService.readSaveFileForTextField(countTextField);
    }

    public void writeSaveFile(TextFieldDto[] textFieldDtos){
        saveFileService.writeSaveFileForTextField(textFieldDtos);
    }
}
