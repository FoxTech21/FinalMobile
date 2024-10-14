package android.bignerdranch.projectmanagement.Domain;

public class OngioingDomain {
    private int taskID;               // Task ID
    private String devName;           // Dev Name
    private String taskName;          // Task Name
    private String startDate;         // Start Date
    private String endDate;           // End Date
    private int progressPercent;      // Progress Percent
    private int estimateDay;       // Estimate Day

    public OngioingDomain(int taskID, String taskName, String startDate, String endDate, int estimateDay, String devName,  int progressPercent) {
        this.taskID = taskID;
        this.devName = devName;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progressPercent = progressPercent;
        this.estimateDay = estimateDay;
    }

    // Getters and setters
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public int getEstimateDay() {
        return estimateDay;
    }

    public void setEstimateDay(int estimateDay) {
        this.estimateDay = estimateDay;
    }
}

