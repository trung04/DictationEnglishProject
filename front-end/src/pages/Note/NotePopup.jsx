import React from "react";
import NoteForm from "./NoteForm";
import NoteList from "./NoteList";

const NotePopup = ({
  isOpen,
  onClose,
  curMode = "NoteList",
  noteId,
  userData,
  onSuccess
}) => {
  if (!isOpen) return null;

  return (
    <div className="modal show d-block" tabIndex="-1" style={{ backgroundColor: "rgba(0, 0, 0, 0.5)" }}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">
              {curMode === "NoteList" ? "Your Notes" : noteId ? "Edit Note" : "Add Note"}
            </h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>
          <div className="modal-body">
            {curMode === "NoteList" ? (
              <NoteList userData={userData} />
            ) : (
              <NoteForm
                onCancel={onClose}
                onSuccess={onSuccess}
                noteId={noteId}
                userData={userData}
              />
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default NotePopup;
