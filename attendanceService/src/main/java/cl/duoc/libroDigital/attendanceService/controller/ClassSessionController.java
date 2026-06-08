package cl.duoc.libroDigital.attendanceService.controller;

import cl.duoc.libroDigital.attendanceService.dto.ClassSessionDTO;
import cl.duoc.libroDigital.attendanceService.model.ClassSession;
import cl.duoc.libroDigital.attendanceService.service.ClassSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessions")
public class ClassSessionController {

    @Autowired
    private ClassSessionService classSessionService;

    private ClassSessionDTO toDTO(ClassSession session) {
        ClassSessionDTO dto = new ClassSessionDTO();
        dto.setId(session.getId());
        dto.setCourseId(session.getCourseId());
        dto.setSubjectId(session.getSubjectId());
        dto.setTeacherId(session.getTeacherId());
        dto.setSessionDate(session.getSessionDate());
        dto.setTopic(session.getTopic());
        dto.setSessionStatus(session.getSessionStatus());
        dto.setCreatedAt(session.getCreatedAt());
        dto.setUpdatedAt(session.getUpdatedAt());
        return dto;
    }

    private ClassSession toEntity(ClassSessionDTO dto) {
        ClassSession session = new ClassSession();
        session.setId(dto.getId());
        session.setCourseId(dto.getCourseId());
        session.setSubjectId(dto.getSubjectId());
        session.setTeacherId(dto.getTeacherId());
        session.setSessionDate(dto.getSessionDate());
        session.setTopic(dto.getTopic());
        session.setSessionStatus(dto.getSessionStatus());
        return session;
    }

    @PostMapping
    public ClassSessionDTO createSession(@RequestBody ClassSessionDTO dto) {
        return toDTO(classSessionService.createSession(toEntity(dto)));
    }

    @GetMapping
    public List<ClassSessionDTO> getAllSessions() {
        return classSessionService.getAllSessions()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClassSessionDTO getSession(@PathVariable Long id) {
        return classSessionService.getSessionById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @GetMapping("/course/{courseId}")
    public List<ClassSessionDTO> getSessionsByCourse(@PathVariable Long courseId) {
        return classSessionService.getSessionsByCourse(courseId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/subject/{subjectId}")
    public List<ClassSessionDTO> getSessionsBySubject(@PathVariable Long subjectId) {
        return classSessionService.getSessionsBySubject(subjectId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ClassSessionDTO updateSession(@PathVariable Long id, @RequestBody ClassSessionDTO dto) {
        return toDTO(classSessionService.updateSession(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        classSessionService.deleteSession(id);
    }
}
