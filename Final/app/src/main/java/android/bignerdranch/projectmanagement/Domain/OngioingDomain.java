package android.bignerdranch.projectmanagement.Domain;

public class OngioingDomain {
    private String taskName;
    private String startDate;
    private int progressPercent;

    public OngioingDomain(String taskName, String startDate, int progressPercent) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.progressPercent = progressPercent;
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
}

