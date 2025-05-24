import React, { useEffect, useState, useMemo } from "react";
import axios from "axios";
import NotePopup from "./NotePopup";

const NoteList = ({ userData }) => {
  const [notes, setNotes] = useState([]);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const [popupMode, setPopupMode] = useState("NoteList");
  const [selectedNoteId, setSelectedNoteId] = useState(null);

  const loadNotes = () => {
    axios.get("http://localhost:8080/api/notes")
      .then(res => setNotes(res.data))
      .catch(err => console.error("Lỗi khi gọi API:", err));
  };

  useEffect(() => {
    loadNotes();
  }, []);

  const handleSuccess = () => {
    loadNotes();
    closePopup();
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this?")) return;
    try {
      await axios.delete(`http://localhost:8080/api/notes/${id}`);
      setNotes(notes.filter(note => note.noteId !== id));
    } catch (err) {
      console.error("Lỗi khi xoá:", err);
    }
  };

  const openAddNote = () => {
    setSelectedNoteId(null);
    setPopupMode("NoteForm");
    setIsPopupOpen(true);
  };

  const openEditNote = (noteId) => {
    setSelectedNoteId(noteId);
    setPopupMode("NoteForm");
    setIsPopupOpen(true);
  };

  const closePopup = () => {
    setIsPopupOpen(false);
    setPopupMode("NoteList");
    setSelectedNoteId(null);
  };

  // Dùng useMemo để luôn cập nhật đúng danh sách lọc
  const filterNote = useMemo(() => {
    return notes.filter(note => note.userId === userData.userId);
  }, [notes, userData]);

  return (
    <div className="modal-body">
      <div className="mb-2 d-flex">
        <div className="flex-grow-1">
          <span>{filterNote.length} notes</span>
        </div>
        <div className="btn btn-sm btn-primary" onClick={openAddNote}>
          <i className="bi bi-plus-circle-fill"></i> Add note
        </div>
      </div>

      <ul className="list-group" style={{ maxHeight: "500px", overflowY: "scroll" }}>
        {filterNote.map((note) => (
          <li key={note.noteId} className="list-group-item pt-0">
            <div className="d-flex align-items-center mb-2">
              <div className="flex-grow-1">
              </div>
              <button
                className="btn btn-sm text-muted text-decoration-underline"
                onClick={() => openEditNote(note.noteId)}
              >
                Edit
              </button>
              <button
                className="btn btn-sm text-muted text-decoration-underline"
                onClick={() => handleDelete(note.noteId)}
              >
                Delete
              </button>
            </div>
            <div className="my-2" style={{ whiteSpace: "pre-line" }}>
              {note.content}
            </div>
          </li>
        ))}
      </ul>

      <NotePopup
        isOpen={isPopupOpen}
        onClose={closePopup}
        curMode={popupMode}
        noteId={selectedNoteId}
        userData={userData}
        onSuccess={handleSuccess}
      />
    </div>
  );
};

export default NoteList;
