package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BookReportTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookReportTagId;

    @ManyToOne(targetEntity = BookReport.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_report_id")
    private BookReport bookReport;

    @ColumnDefault(value = "")
    private String tagName;

    @Builder
    public BookReportTag(BookReport bookReport, String tagName) {
        this.bookReport = bookReport;
        this.tagName = tagName;
    }

}
