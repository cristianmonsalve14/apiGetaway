package cl.duoc.libroDigital.attendanceService.service.impl;

import cl.duoc.libroDigital.attendanceService.model.Annotation;
import cl.duoc.libroDigital.attendanceService.repository.AnnotationRepository;
import cl.duoc.libroDigital.attendanceService.service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnotationServiceImpl implements AnnotationService {

    private static final List<String> VALID_TYPES = List.of("POSITIVA", "NEGATIVA");

    @Autowired
    private AnnotationRepository annotationRepository;

    @Override
    public Annotation createAnnotation(Annotation annotation) {
        if (annotation.getType() != null) {
            annotation.setType(annotation.getType().toUpperCase());
        }
        validateType(annotation.getType());
        return annotationRepository.save(annotation);
    }

    @Override
    public List<Annotation> getAllAnnotations() {
        return annotationRepository.findAll();
    }

    @Override
    public Optional<Annotation> getAnnotationById(Long id) {
        return annotationRepository.findById(id);
    }

    @Override
    public Annotation updateAnnotation(Long id, Annotation annotation) {
        return annotationRepository.findById(id).map(existing -> {
            if (annotation.getStudentId() != null) {
                existing.setStudentId(annotation.getStudentId());
            }
            if (annotation.getTeacherId() != null) {
                existing.setTeacherId(annotation.getTeacherId());
            }
            if (annotation.getAnnotationDate() != null) {
                existing.setAnnotationDate(annotation.getAnnotationDate());
            }
            if (annotation.getType() != null) {
                String type = annotation.getType().toUpperCase();
                validateType(type);
                existing.setType(type);
            }
            if (annotation.getDescription() != null) {
                existing.setDescription(annotation.getDescription());
            }
            return annotationRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Anotación no encontrada con id " + id));
    }

    @Override
    public void deleteAnnotation(Long id) {
        annotationRepository.deleteById(id);
    }

    @Override
    public List<Annotation> getAnnotationsByStudent(Long studentId) {
        return annotationRepository.findByStudentId(studentId);
    }

    @Override
    public List<Annotation> getAnnotationsByTeacher(Long teacherId) {
        return annotationRepository.findByTeacherId(teacherId);
    }

    private void validateType(String type) {
        if (type != null && !VALID_TYPES.contains(type.toUpperCase())) {
            throw new RuntimeException(
                    "Tipo de anotación inválido. Valores permitidos: " + VALID_TYPES
            );
        }
    }
}
