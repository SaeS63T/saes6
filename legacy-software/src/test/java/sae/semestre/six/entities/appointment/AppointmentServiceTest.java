package sae.semestre.six.entities.appointment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    @Test
    void basicOperations() {
        AppointmentDao dao = Mockito.mock(AppointmentDao.class);
        AppointmentService service = new AppointmentService(dao);
        Appointment app = new Appointment();
        List<Appointment> list = List.of(app);
        Mockito.when(dao.findByDoctorId(1L)).thenReturn(list);
        Mockito.when(dao.save(app)).thenReturn(app);

        assertEquals(list, service.getByDoctorId(1L));
        assertSame(app, service.save(app));
        Mockito.verify(dao).save(app);
    }
}
