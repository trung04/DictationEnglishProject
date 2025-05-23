import axios from "axios";
import React, { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
dayjs.extend(relativeTime);

const Comment = () => {
  const { id } = useParams();
  const user = JSON.parse(localStorage?.getItem("user"));
  const [comments, setComments] = useState([]);
  const [comment, setComment] = useState(null);
  const [index, setIndex] = useState(3);
  const [isEdit, setIsEdit] = useState(null);
  const [commentEdit, setCommentEdit] = useState(null);

  const fetchData = async () => {
    try {
      const res2 = await axios.get(`http://localhost:8080/api/lessons/${id}`);
      setComments(res2.data.comments);
      console.log(res2.data);
    } catch (e) {
      console.log(e);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
  const handleDeleteComment = async (id) => {
    try {
      const res = await axios.delete(
        `http://localhost:8080/api/comment/delete/${id}`
      );
      fetchData();
    } catch (e) {
      console.log(e);
    }
  };
  const handleEdit = async (comment) => {
    setIsEdit(comment?.commentId);
    setCommentEdit(comment.content);
  };
  const handleSubmitEdit = async (commentId) => {
    try {
      const res = await axios.put(
        `http://localhost:8080/api/comment/update/${commentId}`,

        {
          content: commentEdit,
        }
      );
      setIsEdit(null);
      setCommentEdit(null);
      fetchData();
    } catch (e) {
      console.log(e);
    }
  };
  const handleSubmit = async (e) => {
    try {
      const res = await axios.post(
        "http://localhost:8080/api/comment/addComment",
        {
          content: comment,
          like: 0,
          dislike: 0,
          userId: user?.userId,
          lessonId: id,
        }
      );
      fetchData();
      setComment("");
    } catch (e) {
      console.log(e);
    }
  };
  const handleLike = async () => {};
  return (
    <div className="container mt-4">
      <h5 className="mb-3">{comments.length} Comments</h5>
      {[...comments]
        .sort((a, b) => new Date(b.submittedAt) - new Date(a.submittedAt))
        .slice(0, index)
        .map((comment) => (
          <div className="d-flex mb-3" key={comment.commentId}>
            {/* Avatar */}
            {/* <img
            src={comment.avatar}
            alt={comment.author}
            className="rounded"
            style={{ width: "40px", height: "40px" }}
          /> */}
            {/* Comment content */}
            <div className="ms-3 flex-grow-1 border rounded p-3 shadow-sm bg-light">
              <div className="d-flex align-items-center mb-2">
                <strong>{comment?.user?.username}</strong>
                <span className="mx-2"></span>
                <small
                  className="text-muted"
                  title={new Date(comment?.submittedAt).toDateString()}
                >
                  <time dateTime={comment?.submittedAt}>
                    {dayjs(comment?.submittedAt).fromNow()}
                  </time>
                </small>
              </div>

              {isEdit == comment.commentId ? (
                <>
                  {" "}
                  <p className="mb-2">
                    <textarea
                      value={commentEdit}
                      name=""
                      onChange={(e) => {
                        setCommentEdit(e.target.value);
                      }}
                      id="editContent"
                      cols={50}
                    ></textarea>
                  </p>
                </>
              ) : (
                <p className="mb-2">{comment.content}</p>
              )}

              <div className="d-flex gap-2">
                {/* <button className="btn btn-sm btn-outline-primary">
                  👍 {comment.like}
                </button>
                <button className="btn btn-sm btn-outline-secondary">
                  👎 {comment.dislike}
                  {user?.userId}
                  {comment?.user?.userId}
                </button> */}

                {comment?.user?.userId === user?.userId ? (
                  <>
                    <div className="d-flex gap-2">
                      {/* Edit button */}
                      {isEdit == comment?.commentId ? (
                        <>
                          <button
                            onClick={() => handleSubmitEdit(comment?.commentId)}
                            className="btn btn-sm d-flex align-items-center gap-1 text-success"
                            style={{
                              background: "transparent",
                              border: "none",
                            }}
                          >
                            {/* Icon check from Bootstrap Icons */}
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              width="16"
                              height="16"
                              fill="currentColor"
                              className="bi bi-check-circle"
                              viewBox="0 0 16 16"
                            >
                              <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm3.97-8.03a.75.75 0 0 0-1.06-1.06L7.75 9.19 5.53 6.97a.75.75 0 0 0-1.06 1.06l2.5 2.5a.75.75 0 0 0 1.06 0l4-4z" />
                            </svg>
                            <span>Submit</span>
                          </button>
                        </>
                      ) : (
                        <>
                          <button
                            onClick={() => {
                              handleEdit(comment);
                            }}
                            className="btn btn-sm d-flex align-items-center gap-1 text-primary"
                            style={{
                              background: "transparent",
                              border: "none",
                            }}
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              width="16"
                              height="16"
                              fill="currentColor"
                              viewBox="0 0 16 16"
                            >
                              <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z" />
                            </svg>
                            <span>Edit</span>
                          </button>
                        </>
                      )}

                      {/* Delete button */}
                      <button
                        onClick={() => handleDeleteComment(comment?.commentId)}
                        className="btn btn-sm d-flex align-items-center gap-1 text-danger"
                        style={{ background: "transparent", border: "none" }}
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="16"
                          height="16"
                          fill="currentColor"
                          viewBox="0 0 16 16"
                        >
                          <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5" />
                        </svg>
                        <span>Delete</span>
                      </button>
                    </div>
                  </>
                ) : (
                  ""
                )}
                {/* <button className="btn btn-primary ">Reply</button> */}
                {/* <button className="btn btn-sm btn-link">Reply</button> */}
              </div>
            </div>
          </div>
        ))}
      {index < comments.length ? (
        <button
          onClick={() => setIndex(comments.length)}
          className="btn btn-sm btn-outline-secondary mt-2"
        >
          👁️ Xem thêm bình luận
        </button>
      ) : (
        <button
          onClick={() => setIndex(3)}
          className="btn btn-sm btn-outline-dark mt-2"
        >
          🔼 Ẩn bớt bình luận
        </button>
      )}

      {/* Write a comment */}
      <form
        style={{ marginBottom: "20px", marginTop: "10px" }}
        onSubmit={(e) => {
          e.preventDefault();
          handleSubmit(e);
        }}
      >
        <textarea
          placeholder="Viết bình luận của bạn..."
          rows="4"
          value={comment}
          onChange={async (e) => {
            await setComment(e.target.value);
          }}
          style={{ width: "100%", padding: "10px", fontSize: "14px" }}
        />
        <div className="mt-4">
          <button className="btn btn-outline-dark">✍️ Write a comment</button>
        </div>
      </form>
    </div>
  );
};

export default Comment;
