import axios from "axios";
import React from "react";
import { useState } from "react";
// const comments = [
//   {
//     id: 2,
//     author: "linda",
//     avatar: "https://randomuser.me/api/portraits/women/65.jpg",
//     content: "This video helped me a lot, thank you!",
//     datetime: "2025-03-01T08:10:00+00:00",
//     displayTime: "1 month ago",
//   },
// ];

const Comment = (props) => {
  const comments = props.lessons || [];
  const [comment, setComment] = useState(null);
  const handleSubmit = async () => {
    try {
      const res = await axios.post(
        "http://localhost:8080/api/comment/addComment",
        {
          content: comment,
          like: 0,
          dislike: 0,
          userId: 1,
          lessonId: 8,
        }
      );
      console.log(res.data);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="container mt-4">
      <h5 className="mb-3">{comments.length} Comments</h5>
      {comments.map((comment) => (
        <div className="d-flex mb-3" key={comment.id}>
          {/* Avatar */}
          {/* <img
            src={comment.avatar}
            alt={comment.author}
            className="rounded"
            style={{ width: "40px", height: "40px" }}
          /> */}

          {/* Comment content */}

          <div className="ms-3 flex-grow-1">
            <div className="d-flex align-items-center mb-1">
              <strong>{comment?.user?.username}</strong>
              <span className="mx-2"></span>
              <small
                className="text-muted"
                title={new Date(comment?.submittedAt).toDateString()}
              >
                <time dateTime={comment?.submittedAt}>
                  {comment?.submittedAt}
                </time>
              </small>
            </div>

            <p className="mb-2">{comment.content}</p>

            <div className="d-flex gap-2">
              <button className="btn btn-sm btn-outline-primary">
                {comment.like}Like
              </button>
              <button className="btn btn-sm btn-outline-secondary">
                {comment.dislike}
                Dislike
              </button>
              {/* <button className="btn btn-sm btn-link">Reply</button> */}
            </div>
          </div>
        </div>
      ))}

      {/* Write a comment */}
      <form style={{ marginBottom: "20px" }}>
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
