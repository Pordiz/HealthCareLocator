package com.example.locatorappforhealthcare;
import java.util.List;
import java.util.ArrayList;


public class TreeNode {
    private String question;
    private String additionalQuestion;
    private List<TreeNode> children;

    public TreeNode(String question) {
        this.question = question;
        this.children = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
    public String getAdditionalQuestion() {
        return additionalQuestion;
    }

    public void setAdditionalQuestion(String additionalQuestion) {
        this.additionalQuestion = additionalQuestion;
    }
}
