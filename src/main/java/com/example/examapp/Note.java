package com.example.examapp;

import com.google.firebase.firestore.PropertyName;

public class Note {

    private String title;
    private String content;

    // Default constructor for Firestore
    public Note() {
        // Required for Firestore serialization
    }

    // Constructor that accepts title and content
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter and Setter for Title
    @PropertyName("note_title")  // Optional, if you want to map Firestore field to a custom name
    public String getTitle() {
        return title;
    }

    @PropertyName("note_title")
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for Content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}