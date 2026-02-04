# ManageStudentInfomation

Simple Java console application to manage student records using a MySQL database.

**Project**: Basic student information manager with add, edit, course assignment and search capabilities.

**Location**: [src/main/java/com/student/infomation/Main.java](src/main/java/com/student/infomation/Main.java)

**Requirements**

- Java 23 (project compiled with source/target 23 per `pom.xml`).
- Maven 3.x
- MySQL server (or compatible) and credentials.

## Quick start

1. Create a database in MySQL for the application, e.g. `student_db`.
2. Add a `.env` file to the project root with DB connection values (DO NOT commit this file):

```
DB_HOST=localhost:3306
DB_NAME=student_db
DB_USERNAME=root
DB_PASSWORD=your_password
```

3. Build and copy dependencies

```powershell
mvn clean package
mvn dependency:copy-dependencies
```

4. Run the application (Windows PowerShell)

```powershell
# run from project root
java -cp target/classes;target/dependency/* com.student.infomation.Main
```

Notes:

- The application will create `students` and `courses` tables automatically if they do not exist.
- The code uses plain JDBC and concatenates SQL strings; avoid using this on untrusted inputs (SQL injection risk).

## Files of interest

- Main console UI: [src/main/java/com/student/infomation/Main.java](src/main/java/com/student/infomation/Main.java)
- DB connection loader: [src/main/java/com/student/infomation/SingletonConnection.java](src/main/java/com/student/infomation/SingletonConnection.java)
- Environment loader: [src/main/java/com/student/infomation/EnvLoader.java](src/main/java/com/student/infomation/EnvLoader.java)
- Student model: [src/main/java/com/student/infomation/Student.java](src/main/java/com/student/infomation/Student.java)

## Recommendations and next steps

- Replace raw SQL string concatenation with `PreparedStatement` to prevent SQL injection.
- Add logging instead of `System.out.println` for production readiness.
- Add a small integration test suite (in `test/`) to validate DB operations.

## License

This repository contains no explicit license. Add one if you want to open-source the project.
