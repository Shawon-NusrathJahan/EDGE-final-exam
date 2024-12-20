package com.example.examapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private List<Note> notesList; // Corrected the type to Note
    private FirebaseFirestore db;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList); // Corrected adapter initialization
        recyclerView.setAdapter(notesAdapter);

        // Fetch notes from Firestore
        fetchNotes();

        return view;
    }

    private void fetchNotes() {
        db.collection("notes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> notes = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot noteSnapshot : notes) {
                            Note note = noteSnapshot.toObject(Note.class); // Mapping Firestore doc to Note
                            notesList.add(note);
                        }
                        notesAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(e -> {
                    Snackbar.make(getView(), "Error fetching notes", Snackbar.LENGTH_SHORT).show();
                });
    }

    // Method to add a new note to Firestore
    public void addNote(String title, String content) {
        Note note = new Note(title, content);
        db.collection("notes").add(note)
                .addOnSuccessListener(documentReference -> {
                    notesList.add(note);
                    notesAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Snackbar.make(getView(), "Error adding note", Snackbar.LENGTH_SHORT).show();
                });
    }
}
