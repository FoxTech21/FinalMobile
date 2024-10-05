package android.bignerdranch.projectmanagement.Domain;

public class OngoingDomain_Search {
    private String endDate;
    private String title;
    private String date;
    private int progressPercent;
    private String picture;
    private String assignee;

    public OngoingDomain_Search(String title, String date, int progressPercent, String picture, String endDate, String assignee) {
        this.title = title;
        this.date = date;
        this.progressPercent = progressPercent;
        this.picture = picture;
        this.endDate = endDate;
        this.assignee = assignee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setEndDate(String picture) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setAssignee(String picture) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }
}
