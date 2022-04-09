package service.savefile;

import dto.TextFieldDto;

public interface SaveFileService {
    TextFieldDto[] readSaveFileForTextField(int countTextField);
    void writeSaveFileForTextField(TextFieldDto[] textFieldDtos);
}
