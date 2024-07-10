package edu.uncc.assignment06.models;

import java.io.Serializable;

public class Task implements Serializable {
    String name, category, priorityStr;
    int priority;


    public Task() {
    }

    public Task(String name, String category, String priorityStr, int priority) {
        this.name = name;
        this.category = category;
        this.priorityStr = priorityStr;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriorityStr() {
        return priorityStr;
    }

    public void setPriorityStr(String priorityStr) {
        this.priorityStr = priorityStr;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", priorityStr='" + priorityStr + '\'' +
                ", priority=" + priority +
                '}';
    }

    public int getPriority() {
        if (priorityStr.equals("Very High")) {
            return 5;
        } else if (priorityStr.equals("High")) {
            return 4;
        } else if (priorityStr.equals("Medium")) {
            return 3;
        } else if (priorityStr.equals("Low")) {
            return 2;
        } else if (priorityStr.equals("Very Low")) {
            return 1;
        }
        return 0;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
