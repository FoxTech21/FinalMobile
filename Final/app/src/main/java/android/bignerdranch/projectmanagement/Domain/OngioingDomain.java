package android.bignerdranch.projectmanagement.Domain;

public class OngioingDomain {
    private String taskName;
    private String startDate;
    private int progressPercent;
    private String picture;

    public OngioingDomain(String taskName, String startDate, int progressPercent, String picture) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.progressPercent = progressPercent;
        this.picture = picture;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public String getPicture() {
        return picture;
    }
}

