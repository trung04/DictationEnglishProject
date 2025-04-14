import React from "react";
import NoteList from "./NoteList"; // Đảm bảo NoteList hiển thị được
import NoteForm from "./NoteForm";

const NotePopup = ({ isOpen, onClose, curMode="NoteList" }) => {
  if (!isOpen) return null;

  return (
    <div
      className="modal show d-block"
      tabIndex="-1"
      style={{ backgroundColor: "rgba(0, 0, 0, 0.5)" }}
    >
      <div className="modal-dialog modal-lg">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">Your Notes</h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>
          <div className="modal-body">
            {curMode === "NoteList"? <NoteList />: <NoteForm/>}
            
          </div>
        </div>
      </div>
    </div>
  );
};

export default NotePopup;
