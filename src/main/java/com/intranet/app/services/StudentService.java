package com.intranet.app.services;

import com.intranet.app.clients.AddressServiceWebClient;
import com.intranet.app.db.models.Student;
import com.intranet.app.db.repositories.StudentRepository;
import com.intranet.app.models.AddressResponse;
import com.intranet.app.models.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AddressServiceWebClient addressServiceWebClient;

    @Autowired
    public StudentService(StudentRepository studentRepository, AddressServiceWebClient addressServiceWebClient) {
        this.studentRepository = studentRepository;
        this.addressServiceWebClient = addressServiceWebClient;
    }

    public Flux<Student> findAllStudents() {
        return Flux.fromStream(studentRepository.findAll().stream());
    }

    public Mono<Student> findStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return Mono.justOrEmpty(student);
    }

    public Mono<StudentDTO> findStudentDTOById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return addressServiceWebClient.fetchAddress(student.getDni()).flatMap(r -> buildStudentDTO(r, student));
    }

    private Mono<StudentDTO> buildStudentDTO(AddressResponse addressResponse, Student student) {
        StudentDTO studentDTO = StudentDTO.builder()
                .id(student.getId())
                .code(student.getCode())
                .name(student.getName())
                .dni(student.getDni())
                .address(AddressResponse.builder()
                        .id(addressResponse.getId())
                        .street(addressResponse.getStreet())
                        .city(addressResponse.getCity())
                        .postalCode(addressResponse.getPostalCode())
                        .country(addressResponse.getCountry())
                        .dni(addressResponse.getDni())
                        .build())
                .build();
        return Mono.justOrEmpty(studentDTO);
    }
}
