
package com.student;

import java.util.List;

public class Page {
    private int totalCount;
    private List<Student> result;

    public Page(int totalCount, List<Student> result) {
        this.totalCount = totalCount;
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Student> getResult() {
        return result;
    }

    public String toString() {
        return "{totalCount:" + totalCount + ",result:" + result + "}";
    }
}
