package model;

public class Task {

    private String name;
    private int priority;
    private int isDone;

    public Task(String name, int priority, int isDone) {
        this.name = name;
        this.priority = priority;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", isDone=" + isDone +
                '}';
    }
}
