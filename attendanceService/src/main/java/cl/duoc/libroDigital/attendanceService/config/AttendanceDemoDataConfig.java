package cl.duoc.libroDigital.attendanceService.config;

import cl.duoc.libroDigital.attendanceService.model.Annotation;
import cl.duoc.libroDigital.attendanceService.model.AttendanceRecord;
import cl.duoc.libroDigital.attendanceService.model.ClassSession;
import cl.duoc.libroDigital.attendanceService.repository.AnnotationRepository;
import cl.duoc.libroDigital.attendanceService.repository.AttendanceRecordRepository;
import cl.duoc.libroDigital.attendanceService.repository.ClassSessionRepository;
import cl.duoc.libroDigital.attendanceService.service.AttendanceCatalogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Datos demo de asistencia/anotaciones alineados con academic (prof_castillo = teacherId 1,
 * curso 17, asignatura Matemáticas id 2, alumnos matriculados 6 y 2).
 */
@Configuration
public class AttendanceDemoDataConfig {

    private static final Long DEMO_TEACHER_ID = 1L;
    private static final Long DEMO_COURSE_ID = 17L;
    private static final Long DEMO_SUBJECT_ID = 2L;
    private static final Long DEMO_STUDENT_A = 6L;
    private static final Long DEMO_STUDENT_B = 2L;

    @Bean
    public CommandLineRunner seedAttendanceDemo(
            ClassSessionRepository sessionRepository,
            AttendanceRecordRepository attendanceRepository,
            AnnotationRepository annotationRepository,
            AttendanceCatalogService catalogs) {
        return args -> {
            if (sessionRepository.count() > 0) {
                return;
            }

            ClassSession session = new ClassSession();
            session.setCourseId(DEMO_COURSE_ID);
            session.setSubjectId(DEMO_SUBJECT_ID);
            session.setTeacherId(DEMO_TEACHER_ID);
            session.setSessionDate(LocalDate.now().minusDays(1));
            session.setTopic("Clase demo — números enteros");
            session.setSessionStatusId(catalogs.sessionStatusId("CERRADA"));
            session = sessionRepository.save(session);

            AttendanceRecord presente = new AttendanceRecord();
            presente.setSessionId(session.getId());
            presente.setStudentId(DEMO_STUDENT_A);
            presente.setAttendanceStatusId(catalogs.attendanceStatusId("PRESENTE"));
            presente.setObservations("Asistencia demo");
            attendanceRepository.save(presente);

            AttendanceRecord ausente = new AttendanceRecord();
            ausente.setSessionId(session.getId());
            ausente.setStudentId(DEMO_STUDENT_B);
            ausente.setAttendanceStatusId(catalogs.attendanceStatusId("AUSENTE"));
            ausente.setObservations("Ausencia demo");
            attendanceRepository.save(ausente);

            Annotation positiva = new Annotation();
            positiva.setStudentId(DEMO_STUDENT_A);
            positiva.setTeacherId(DEMO_TEACHER_ID);
            positiva.setAnnotationDate(LocalDate.now().minusDays(1));
            positiva.setAnnotationTypeId(catalogs.annotationTypeId("POSITIVA"));
            positiva.setDescription("Participación destacada en clase demo");
            annotationRepository.save(positiva);
        };
    }
}
