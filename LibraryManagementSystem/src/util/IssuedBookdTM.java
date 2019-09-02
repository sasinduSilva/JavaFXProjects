package util;

import java.time.LocalDateTime;

public class IssuedBookdTM {
    private String issueID;
    private String memberId;
    private String bookId;
    private String date;
    private LocalDateTime returningDay;

    public IssuedBookdTM() {
    }

    public IssuedBookdTM(String issueID, String memberId, String bookId, String date, LocalDateTime returningDay) {
        this.issueID = issueID;
        this.memberId = memberId;
        this.bookId = bookId;
        this.date = date;
        this.returningDay = returningDay;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDateTime getReturningDay() {
        return returningDay;
    }

    public void setReturningDay(LocalDateTime returningDay) {
        this.returningDay = returningDay;
    }

    @Override
    public String toString() {
        return "IssuedBookdTM{" +
                "issueID='" + issueID + '\'' +
                ", memberId='" + memberId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", date='" + date + '\'' +
                ", returningDay='" + returningDay + '\'' +
                '}';
    }
}
