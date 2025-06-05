package sae.semestre.six.entities.email;

import org.junit.jupiter.api.Test;

public class EmailServiceTest {

    @Test
    void sendEmailDoesNotThrow() {
        EmailService service = new EmailService("host",25,"u","p","from@example.com");
        service.sendEmail("to@example.com","sub","body");
    }
}
