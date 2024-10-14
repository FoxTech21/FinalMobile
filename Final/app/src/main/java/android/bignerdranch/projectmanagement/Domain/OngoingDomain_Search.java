package android.bignerdranch.projectmanagement.Domain;

public class OngoingDomain_Search {
    private int taskId;  // Thêm thuộc tính taskId
    private String endDate;
    private String taskName;
    private String startDate;
    private int progressPercent;
    private String devName;

    // Constructor bao gồm taskId
    public OngoingDomain_Search(int taskId, String taskName, String startDate, int progressPercent, String endDate, String devName) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.startDate = startDate;
        this.progressPercent = progressPercent;
        this.endDate = endDate;
        this.devName = devName;
    }

    // Getter và setter cho taskId
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    // Getter và setter cho các thuộc tính còn lại
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

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevName() {
        return devName;
    }
}