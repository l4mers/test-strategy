-- Create Student table
CREATE TABLE Student (
                         student_id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255),
                         gender ENUM('Male', 'Female', 'Other')
);

-- Create Auth table
CREATE TABLE Auth (
                      student_id INT,
                      username VARCHAR(255),
                      password VARCHAR(255),
                      FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

-- Create Pii table
CREATE TABLE Pii (
                     pii_id INT PRIMARY KEY AUTO_INCREMENT,
                     student_id INT,
                     personnummer VARCHAR(13),
                     phone_number VARCHAR(15),
                     biometric_data BLOB,
                     FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

-- Create HealthRecord table
CREATE TABLE HealthRecord (
                              record_id INT PRIMARY KEY AUTO_INCREMENT,
                              student_id INT,
                              medical_record TEXT,
                              FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

-- Create Course table
CREATE TABLE Course (
                        course_id INT PRIMARY KEY AUTO_INCREMENT,
                        course_name VARCHAR(255)
);

-- Create Grades table
CREATE TABLE Grade (
                       grade_id INT PRIMARY KEY AUTO_INCREMENT,
                       student_id INT,
                       course_id INT,
                       grade VARCHAR(2),
                       FOREIGN KEY (student_id) REFERENCES Student(student_id),
                       FOREIGN KEY (course_id) REFERENCES Course(course_id)
);

-- Create Activity table
CREATE TABLE Activity (
                          activity_id INT PRIMARY KEY AUTO_INCREMENT,
                          activity_name VARCHAR(255),
                          student_id INT,
                          FOREIGN KEY (student_id) REFERENCES Student(student_id)
);