package android.bignerdranch.projectmanagement.Domain;

public class OngoingDomain_Search {
    private String endDate;
    private String taskName;
    private String startDate;
    private int progressPercent;
    private String devName;

    public OngoingDomain_Search(String taskName, String startDate, int progressPercent, String endDate, String devName) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.progressPercent = progressPercent;
        this.endDate = endDate;
        this.devName = devName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }


    public void setEndDate(String picture) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDevName(String picture) {
        this.devName = devName;
    }

    public String getDevName() {
        return devName;
    }
}
