package sae.semestre.six.entities.medicalrecord;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sae.semestre.six.entities.medicalrecord.dto.MedicalRecordRequest;

import java.lang.reflect.Field;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordControllerTest {

    @Test
    void createWithBill() throws Exception {
        MedicalRecordService service = Mockito.mock(MedicalRecordService.class);
        MedicalRecord record = new MedicalRecord();
        Mockito.when(service.createRecordWithBilling(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyList()))
                .thenReturn(record);

        MedicalRecordController controller = new MedicalRecordController();
        Field f = MedicalRecordController.class.getDeclaredField("medicalRecordService");
        f.setAccessible(true);
        f.set(controller, service);

        MedicalRecordRequest req = new MedicalRecordRequest();
        req.setPatientId(1L);
        req.setDoctorId(2L);
        req.setDiagnosis("x");
        req.setItems(Collections.emptyList());

        assertSame(record, controller.createWithBill(req));
        Mockito.verify(service).createRecordWithBilling(1L,2L,"x",Collections.emptyList());
    }
}
