package project4;

public class Node {
    public int taskName;
    public double timeToCompleteTask;
    // earliest start time
    public double ES;
    // earliest finish time
    public double EF;
    // latest start time
    public double LS;
    // latest finish time
    public double LF;
    public double slack;

    public Node(int taskName, double timeToCompleteTask) {
        this.taskName = taskName;
        this.timeToCompleteTask = timeToCompleteTask;
        ES = 0;
        EF = 0;
        LS = 0;
        LF = 0;
        slack = 0;
    }

    public String toString() {
        return taskName + " " + timeToCompleteTask + " " + ES + " " + EF + " " + LS + " " + LF;
    }
}
