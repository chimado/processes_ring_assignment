public class process {
    String processName;
    int estimatedExecutionTime;
    int cycle;

    public process(String processName, int estimatedExecutionTime) {
        this.cycle = 1;
        this.processName = processName;
        this.estimatedExecutionTime = estimatedExecutionTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getEstimatedExecutionTime() {
        return estimatedExecutionTime;
    }

    public void setEstimatedExecutionTime(int estimatedExecutionTime) {
        this.estimatedExecutionTime = estimatedExecutionTime;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}