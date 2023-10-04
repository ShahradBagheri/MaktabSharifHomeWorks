package secondQuestion;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import secondQuestion.model.Course;
import secondQuestion.model.ReviewStudent;
import secondQuestion.model.StudentCourseRating;
import secondQuestion.repository.CourseRepository;
import secondQuestion.repository.ReviewStudentRepository;
import secondQuestion.repository.StudentCourseRatingRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        ReviewStudentRepository reviewStudentRepository = new ReviewStudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        StudentCourseRatingRepository studentCourseRatingRepository = new StudentCourseRatingRepository();

        try (FileReader fileReader = new FileReader("file.txt");
             CSVReader csvReader = new CSVReader(new BufferedReader(fileReader))) {
            String[] line = csvReader.readNext();
            while ((line = csvReader.readNext()) != null){

                String[] metaData = csvReader.readNext();

                String courseName = line[0];
                Course course = courseRepository.findByName(courseName);
                if (course == null){
                    course = new Course();
                    course.setName(courseName);
                    course = courseRepository.save(course);
                }

                String studentName = line[1];
                ReviewStudent reviewStudent = reviewStudentRepository.findByName(studentName);
                if(reviewStudent == null){
                    reviewStudent = new ReviewStudent();
                    reviewStudent.setName(studentName);
                    reviewStudent = reviewStudentRepository.save(reviewStudent);
                }

                String date = line[2] + " " + metaData[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDate localDate = LocalDate.parse(date, formatter);

                float rating = Float.parseFloat(metaData[1]);
                String comment = metaData[2];

                StudentCourseRating studentCourseRating = new StudentCourseRating();
                studentCourseRating.setDate(localDate);
                studentCourseRating.setRating(rating);
                studentCourseRating.setComment(comment);
                studentCourseRating.setCourse(course);
                studentCourseRating.setStudent(reviewStudent);

                studentCourseRatingRepository.save(studentCourseRating);
            }

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
