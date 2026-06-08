package cl.duoc.libroDigital.attendanceService.service.impl;

import cl.duoc.libroDigital.attendanceService.model.ClassSession;
import cl.duoc.libroDigital.attendanceService.repository.ClassSessionRepository;
import cl.duoc.libroDigital.attendanceService.service.ClassSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Override
    public ClassSession createSession(ClassSession session) {
        return classSessionRepository.save(session);
    }

    @Override
    public List<ClassSession> getAllSessions() {
        return classSessionRepository.findAll();
    }

    @Override
    public Optional<ClassSession> getSessionById(Long id) {
        return classSessionRepository.findById(id);
    }

    @Override
    public ClassSession updateSession(Long id, ClassSession session) {
        return classSessionRepository.findById(id).map(existing -> {
            if (session.getCourseId() != null) {
                existing.setCourseId(session.getCourseId());
            }
            if (session.getSubjectId() != null) {
                existing.setSubjectId(session.getSubjectId());
            }
            if (session.getTeacherId() != null) {
                existing.setTeacherId(session.getTeacherId());
            }
            if (session.getSessionDate() != null) {
                existing.setSessionDate(session.getSessionDate());
            }
            if (session.getTopic() != null) {
                existing.setTopic(session.getTopic());
            }
            if (session.getSessionStatus() != null) {
                existing.setSessionStatus(session.getSessionStatus());
            }
            return classSessionRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Sesión no encontrada con id " + id));
    }

    @Override
    public void deleteSession(Long id) {
        classSessionRepository.deleteById(id);
    }

    @Override
    public List<ClassSession> getSessionsByCourse(Long courseId) {
        return classSessionRepository.findByCourseId(courseId);
    }

    @Override
    public List<ClassSession> getSessionsBySubject(Long subjectId) {
        return classSessionRepository.findBySubjectId(subjectId);
    }
}
