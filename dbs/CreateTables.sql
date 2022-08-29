DELIMITER $$

CREATE PROCEDURE UniversityDB.CreateTables ( IN stud_id INT, stud_name VARCHAR(255), dept_id INT, email VARCHAR(255))

BEGIN
INSERT INTO Student (stud_id, stud_name, dept_id, email)
VALUES (stud_id, stud_name, dept_id, email);
INSERT INTO StudentDepartment (stud_id, dept_id)
VALUES (stud_id, dept_id);

END $$

DELIMITER ;


# call Create_Tables(101, 'Alan Alford', 10020, 'Alan_Alford63@hotmail.com')