package cl.duoc.libroDigital.attendanceService.controller;

import cl.duoc.libroDigital.attendanceService.dto.AnnotationDTO;
import cl.duoc.libroDigital.attendanceService.model.Annotation;
import cl.duoc.libroDigital.attendanceService.service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/annotations")
public class AnnotationController {

    @Autowired
    private AnnotationService annotationService;

    private AnnotationDTO toDTO(Annotation annotation) {
        AnnotationDTO dto = new AnnotationDTO();
        dto.setId(annotation.getId());
        dto.setStudentId(annotation.getStudentId());
        dto.setTeacherId(annotation.getTeacherId());
        dto.setAnnotationDate(annotation.getAnnotationDate());
        dto.setType(annotation.getType());
        dto.setDescription(annotation.getDescription());
        dto.setCreatedAt(annotation.getCreatedAt());
        dto.setUpdatedAt(annotation.getUpdatedAt());
        return dto;
    }

    private Annotation toEntity(AnnotationDTO dto) {
        Annotation annotation = new Annotation();
        annotation.setId(dto.getId());
        annotation.setStudentId(dto.getStudentId());
        annotation.setTeacherId(dto.getTeacherId());
        annotation.setAnnotationDate(dto.getAnnotationDate());
        annotation.setType(dto.getType());
        annotation.setDescription(dto.getDescription());
        return annotation;
    }

    @PostMapping
    public AnnotationDTO createAnnotation(@RequestBody AnnotationDTO dto) {
        return toDTO(annotationService.createAnnotation(toEntity(dto)));
    }

    @GetMapping
    public List<AnnotationDTO> getAllAnnotations() {
        return annotationService.getAllAnnotations()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AnnotationDTO getAnnotation(@PathVariable Long id) {
        return annotationService.getAnnotationById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @GetMapping("/student/{studentId}")
    public List<AnnotationDTO> getAnnotationsByStudent(@PathVariable Long studentId) {
        return annotationService.getAnnotationsByStudent(studentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/teacher/{teacherId}")
    public List<AnnotationDTO> getAnnotationsByTeacher(@PathVariable Long teacherId) {
        return annotationService.getAnnotationsByTeacher(teacherId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public AnnotationDTO updateAnnotation(@PathVariable Long id, @RequestBody AnnotationDTO dto) {
        return toDTO(annotationService.updateAnnotation(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteAnnotation(@PathVariable Long id) {
        annotationService.deleteAnnotation(id);
    }
}
