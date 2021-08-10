package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.BookReport;

public interface BookReportRepository extends JpaRepository<BookReport, Long> {
} // Interface 끝
