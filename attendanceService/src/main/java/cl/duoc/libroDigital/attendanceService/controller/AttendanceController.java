package cl.duoc.libroDigital.attendanceService.controller;

import cl.duoc.libroDigital.attendanceService.dto.AttendanceRecordDTO;
import cl.duoc.libroDigital.attendanceService.model.AttendanceRecord;
import cl.duoc.libroDigital.attendanceService.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceRecordService attendanceRecordService;

    private AttendanceRecordDTO toDTO(AttendanceRecord record) {
        AttendanceRecordDTO dto = new AttendanceRecordDTO();
        dto.setId(record.getId());
        dto.setSessionId(record.getSessionId());
        dto.setStudentId(record.getStudentId());
        dto.setStatus(record.getStatus());
        dto.setObservations(record.getObservations());
        dto.setCreatedAt(record.getCreatedAt());
        dto.setUpdatedAt(record.getUpdatedAt());
        return dto;
    }

    private AttendanceRecord toEntity(AttendanceRecordDTO dto) {
        AttendanceRecord record = new AttendanceRecord();
        record.setId(dto.getId());
        record.setSessionId(dto.getSessionId());
        record.setStudentId(dto.getStudentId());
        record.setStatus(dto.getStatus());
        record.setObservations(dto.getObservations());
        return record;
    }

    @PostMapping
    public AttendanceRecordDTO createAttendance(@RequestBody AttendanceRecordDTO dto) {
        return toDTO(attendanceRecordService.createAttendance(toEntity(dto)));
    }

    @GetMapping
    public List<AttendanceRecordDTO> getAllAttendances() {
        return attendanceRecordService.getAllAttendances()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AttendanceRecordDTO getAttendance(@PathVariable Long id) {
        return attendanceRecordService.getAttendanceById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @GetMapping("/session/{sessionId}")
    public List<AttendanceRecordDTO> getAttendancesBySession(@PathVariable Long sessionId) {
        return attendanceRecordService.getAttendancesBySession(sessionId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceRecordDTO> getAttendancesByStudent(@PathVariable Long studentId) {
        return attendanceRecordService.getAttendancesByStudent(studentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public AttendanceRecordDTO updateAttendance(@PathVariable Long id, @RequestBody AttendanceRecordDTO dto) {
        return toDTO(attendanceRecordService.updateAttendance(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceRecordService.deleteAttendance(id);
    }
}
