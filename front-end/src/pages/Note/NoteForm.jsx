import { useEffect, useState } from "react";
import axios from "axios";

function NoteForm({ onCancel, onSuccess, noteId, userData }) {
  const [note, setNote] = useState(null);

  useEffect(() => {
    if (noteId) {
      // Nếu đang sửa
      axios.get(`http://localhost:8080/api/notes/${noteId}`)
        .then(res => setNote(res.data))
        .catch(err => console.error("Error:", err));
    } else if (userData && userData.userId) {
      // Nếu đang thêm mới
      setNote({
        content: "",
        userId: userData.userId,
        lessonId: 1
      });
    }
  }, [noteId, userData]);

  const handleChange = (e) => {
    setNote({ ...note, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const request = noteId
      ? axios.put(`http://localhost:8080/api/notes/${noteId}`, note)
      : axios.post("http://localhost:8080/api/notes", note);

    request
      .then(() => {
        if (onSuccess) onSuccess();
      })
      .catch(err => console.error("Error:", err));
  };

  if (!note) return <div>Loading...</div>;

  return (
    <div style={{ maxWidth: "500px", margin: "auto", padding: "20px" }}>
      <form onSubmit={handleSubmit}>
        <div className="form-group mb-3">
          <label style={{ fontWeight: "bold" }}>Note content:</label>
          <textarea
            name="content"
            value={note.content}
            onChange={handleChange}
            required
            className="form-control"
            rows="4"
            style={{ borderRadius: "8px" }}
          />
        </div>
        <div className="d-flex justify-content-between">
          <button type="submit" className="btn btn-primary">
            {noteId ? "Update" : "Save"}
          </button>
          <button type="button" className="btn btn-secondary" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default NoteForm;
