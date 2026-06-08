package cl.duoc.libroDigital.attendanceService.service.impl;

import cl.duoc.libroDigital.attendanceService.model.AttendanceRecord;
import cl.duoc.libroDigital.attendanceService.repository.AttendanceRecordRepository;
import cl.duoc.libroDigital.attendanceService.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    private static final List<String> VALID_STATUSES = List.of(
            "PRESENTE", "AUSENTE", "ATRASADO", "JUSTIFICADO"
    );

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Override
    public AttendanceRecord createAttendance(AttendanceRecord record) {
        if (record.getStatus() != null) {
            record.setStatus(record.getStatus().toUpperCase());
        }
        validateStatus(record.getStatus());
        attendanceRecordRepository
                .findBySessionIdAndStudentId(record.getSessionId(), record.getStudentId())
                .ifPresent(existing -> {
                    throw new RuntimeException(
                            "Ya existe asistencia para el estudiante " + record.getStudentId()
                                    + " en la sesión " + record.getSessionId()
                    );
                });
        return attendanceRecordRepository.save(record);
    }

    @Override
    public List<AttendanceRecord> getAllAttendances() {
        return attendanceRecordRepository.findAll();
    }

    @Override
    public Optional<AttendanceRecord> getAttendanceById(Long id) {
        return attendanceRecordRepository.findById(id);
    }

    @Override
    public AttendanceRecord updateAttendance(Long id, AttendanceRecord record) {
        return attendanceRecordRepository.findById(id).map(existing -> {
            if (record.getSessionId() != null) {
                existing.setSessionId(record.getSessionId());
            }
            if (record.getStudentId() != null) {
                existing.setStudentId(record.getStudentId());
            }
            if (record.getStatus() != null) {
                String status = record.getStatus().toUpperCase();
                validateStatus(status);
                existing.setStatus(status);
            }
            if (record.getObservations() != null) {
                existing.setObservations(record.getObservations());
            }
            return attendanceRecordRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id " + id));
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public List<AttendanceRecord> getAttendancesBySession(Long sessionId) {
        return attendanceRecordRepository.findBySessionId(sessionId);
    }

    @Override
    public List<AttendanceRecord> getAttendancesByStudent(Long studentId) {
        return attendanceRecordRepository.findByStudentId(studentId);
    }

    private void validateStatus(String status) {
        if (status != null && !VALID_STATUSES.contains(status.toUpperCase())) {
            throw new RuntimeException(
                    "Estado de asistencia inválido. Valores permitidos: " + VALID_STATUSES
            );
        }
    }
}
