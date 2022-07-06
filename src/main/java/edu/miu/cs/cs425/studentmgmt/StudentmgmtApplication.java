package edu.miu.cs.cs425.studentmgmt;

import edu.miu.cs.cs425.studentmgmt.model.Classroom;
import edu.miu.cs.cs425.studentmgmt.model.Student;
import edu.miu.cs.cs425.studentmgmt.model.Transcript;
import edu.miu.cs.cs425.studentmgmt.repository.ClassroomRepository;
import edu.miu.cs.cs425.studentmgmt.repository.StudentRepository;
import edu.miu.cs.cs425.studentmgmt.repository.TranscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class StudentmgmtApplication implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TranscriptRepository transcriptRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentmgmtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");
        Student stu = new Student(null, "000-61-0001","Anna","Lynn","Smith",3.45, LocalDate.now());
        stu = studentRepository.save(stu);

        Transcript transcript = new Transcript(null, "BS Computer Science",stu);
        transcript.setStudent(stu);
        transcriptRepository.save(transcript);

        Optional<Student> student = studentRepository.findById(stu.getStudentId());
        stu = student.get();
        stu.setTranscript(transcript);
        studentRepository.save(stu);

        Classroom classroom = new Classroom(null, "McLaughlin building","M105");
        List<Student> classroomStudent = classroom.getStudent();
        classroomStudent.add(stu);
        classroom = classroomRepository.save(classroom);

        stu.setClassroom(classroom);
        studentRepository.save(stu);
    }
}
