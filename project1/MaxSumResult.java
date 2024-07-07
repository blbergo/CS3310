package project1;

public class MaxSumResult {
    public int maxSum;
    public int startIndex;
    public int endIndex;
    public long timeElapsed;
    public String methodName;

    public MaxSumResult(int maxSum, int startIndex, int endIndex, String methodName) {
        this.maxSum = maxSum;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.methodName = methodName;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String toString() {
        return String.format("--------------------\nMethod: %s\nMax Sum: %d\nStart Index: %d\nEnd Index: %d\nTime Elapsed: %d ns",
                methodName, maxSum, startIndex, endIndex, timeElapsed);
    }
}
