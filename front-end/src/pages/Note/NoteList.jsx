import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import NotePopup from "./NotePopup";

const NoteList = () => {
  const [notes, setNotes] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/notes")
      .then(res => setNotes(res.data))
      .catch(err => console.error("Lỗi khi gọi API:", err));
  }, []);

  const handleDelete = async (id) => {
    const confirm = window.confirm("Are you sure you want to delete this?");
    if (!confirm) return;

    try {
      await axios.delete(`http://localhost:8080/api/notes/${id}`);
      setNotes(notes.filter(note => note.noteId !== id));
    } catch (err) {
      console.error("Lỗi khi xoá:", err);
    }
  };

  return (
    <div className="modal-body">
      <div className="mb-2 d-flex">
        <div className="flex-grow-1">
          <span>{notes.length} notes</span>
        </div>
        <div className="btn btn-sm btn-primary" onClick={() => <NotePopup />}>
          <i className="bi bi-plus-circle-fill"></i> Add note
        </div>
      </div>

      <ul className="list-group" style={{ maxHeight: "500px", overflowY: "scroll" }}>
        {notes.map((note) => (
          <li key={note.noteId} className="list-group-item pt-0">
            <div className="d-flex align-items-center mb-2">
              <div className="flex-grow-1">
                <small className="text-secondary">
                  Note#{note.noteId} {" - "}
                  {note.lessonId === 0 ? "" : 
                   <a href="" className="text-secondary">Lesson #{note.lessonId}</a>
                   }
                </small>
              </div>
              <Link to={`./edit/${note.noteId}`} className="btn btn-sm text-muted text-decoration-underline">
                Edit
              </Link>
              <button className="btn btn-sm text-muted text-decoration-underline" onClick={() => handleDelete(note.noteId)}>
                Delete
              </button>
            </div>

            <div className="my-2" style={{ whiteSpace: "pre-line" }}>
              {note.content}
            </div>
          </li>
        ))}
      </ul>
    </div>

  );
};

export default NoteList;
