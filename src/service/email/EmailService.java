package service.email;

import dto.EmailDto;

public interface EmailService {
    public void sendEmail(EmailDto emailDto);
}
