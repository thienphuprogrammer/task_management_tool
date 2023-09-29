package bussinesslayer.entity.submission;

import java.time.LocalDate;
import java.time.LocalTime;

public class SubmissionTask {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String content;
    private int taskId;

    public SubmissionTask(String content, int taskId) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.content = content;
        this.taskId = taskId;
    }

    public SubmissionTask() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
