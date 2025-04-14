import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

function NoteForm() {
  const [note, setNote] = useState({
    content: "",
    userId: "",
    lessonId: ""
  });

  const navigate = useNavigate();
  const { id } = useParams(); // nếu có id thì là edit

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:8080/api/notes/${id}`)
        .then(res => setNote(res.data))
        .catch(err => console.error("Error:", err));
    }
  }, [id]);

  const handleChange = (e) => {
    setNote({ ...note, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (id) {
      axios.put(`http://localhost:8080/api/notes/${id}`, note)
        .then(() => navigate("/notes"))
        .catch(err => console.error("Errot", err));
    } else {
      axios.post("http://localhost:8080/api/notes", note)
        .then(() => navigate("/notes"))
        .catch(err => console.error("Error:", err));
    }
  };

  return (
    <div style={{ maxWidth: "500px", margin: "auto" }}>
      <h2>{id ? "Edit note" : "Create note"}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Note content:</label>
          <textarea
            name="content"
            value={note.content}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>User ID:</label>
          <input
            type="number"
            name="userId"
            value={note.userId}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Lesson ID:</label>
          <input
            type="number"
            name="lessonId"
            value={note.lessonId}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">{id ? "Update" : "Save"}</button>
        <button type="button" onClick={() => navigate("/notes")}>Cancel</button>
      </form>
    </div>
  );
}

export default NoteForm;
