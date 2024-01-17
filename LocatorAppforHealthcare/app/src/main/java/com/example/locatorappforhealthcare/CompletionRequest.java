package com.example.locatorappforhealthcare;

public class CompletionRequest {
    private String model;
    private String prompt;
    private int max_tokens;
    private int n;
    private double temperature;

    public CompletionRequest(String model, String prompt, int max_tokens, int n, double temperature) {
        this.model = model;
        this.prompt = prompt;
        this.max_tokens = max_tokens;
        this.n = n;
        this.temperature = temperature;
    }

    // Add getters and setters for each field
}
