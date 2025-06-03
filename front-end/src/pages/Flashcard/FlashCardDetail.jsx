import axios from "axios";
import React, { useEffect } from "react";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import { useState } from "react";
function FlashCardDetail() {
  const { id } = useParams();
  const [flashcard, setFlashcard] = useState();
  const [flashcardDetail, setFlashcardDetail] = useState();

  const fetchFlashCardDetail = async (flashcardDetailId) => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/flashcard-details/${flashcardDetailId}`
      );
      setFlashcardDetail(res.data);
      console.log(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    if (!id) return;
    const fetchData = async () => {
      try {
        const res = await axios.get(
          `http://localhost:8080/api/flashcards/${id}`
        );
        setFlashcard(res.data);
        console.log(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [id]);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();
  const [notification, setNotificaton] = useState(null);
  const [error, setError] = useState(null);
  const [words, setWords] = useState([]);
  const fetchWords = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/flashcard-details/by-flashcard/${id}`
      );
      setWords(res.data);
      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    fetchWords();
  }, []);
  const handleAdd = async (data) => {
    try {
      const res = await axios.post(
        `http://localhost:8080/api/flashcard-details/create`,
        {
          ...data,
          flashcardId: id,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      reset();
      setNotificaton("Add successfully!");
      fetchWords();
      setTimeout(() => {
        setNotificaton(null);
      }, 3000);
    } catch (error) {
      setError("Add Failed!");
      setTimeout(() => {
        setError(null);
      }, 3000);
    }
  };

  //xóa
  const handleDelete = async (flashcardDetailId) => {
    try {
      const res = await axios.delete(
        `http://localhost:8080/api/flashcard-details/${flashcardDetailId}`
      );
      await fetchWords();
      setNotificaton("Deleted successfully !");
      setTimeout(() => {
        setNotificaton(null);
      }, 3000);
    } catch (error) {
      console.log(error);
      setError("Delete failed !");
      setTimeout(() => {
        setError(null);
      }, 3000);
    }
  };

  //edit
  const handleEdit = async (flashcardDetailId) => {
    try {
      const res = await axios.put(
        `http://localhost:8080/api/flashcard-details/${flashcardDetailId}`,
        flashcardDetail,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      await fetchWords();

      setNotificaton("Edit successfully!");
      setTimeout(() => {
        setNotificaton(null);
      }, 3000);
    } catch (error) {
      console.log(error);
      setError("Edit failed !");
      setTimeout(() => {
        setError(null);
      }, 3000);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFlashcardDetail((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <div className="container py-5">
      <div className="bg-light rounded shadow p-4">
        <div className="text-center mb-4">
          {notification && (
            <div className="alert alert-success text-center" role="alert">
              {notification}
            </div>
          )}

          {error && (
            <div className="alert alert-danger text-center" role="alert">
              {error}
            </div>
          )}
          <h1 className="text-primary fw-bold">
            📘 Flashcard: {flashcard?.title}
          </h1>

          <Link
            to={`/flashcard/practice/${id}`}
            className="btn btn-success btn-lg rounded-pill fw-semibold w-100 text-white"
            style={{ maxWidth: 400, letterSpacing: "0.08em" }}
          >
            Luyện tập
          </Link>
          <div>
            {/* Button trigger modal */}
            <button
              type="button"
              className="btn btn-success btn-lg mt-3"
              data-bs-toggle="modal"
              data-bs-target="#addWord"
            >
              ➕ Thêm từ mới
            </button>

            {/* Modal */}
            <div
              className="modal fade"
              id="addWord"
              tabIndex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div className="modal-dialog">
                <div className="modal-content">
                  <div className="modal-header">
                    <h5 className="modal-title" id="exampleModalLabel">
                      Thêm từ vựng
                    </h5>
                    <button
                      type="button"
                      className="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div className="modal-body">
                    <form
                      className="p-4 bg-white rounded shadow-sm"
                      style={{
                        maxWidth: 400,
                        margin: "2rem auto",
                        fontFamily:
                          "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
                      }}
                      onSubmit={handleSubmit(handleAdd)}
                    >
                      <div className="mb-4">
                        <label
                          htmlFor="term"
                          className="form-label fw-semibold d-flex align-items-center"
                        >
                          <i className="bi bi-journal-text me-2 fs-5 text-primary"></i>{" "}
                          Từ tiếng Anh
                        </label>
                        <div className="input-group">
                          <span className="input-group-text bg-primary text-white">
                            <i className="bi bi-type"></i>
                          </span>
                          <input
                            type="text"
                            className={`form-control border-start-0 ${
                              errors?.term ? "is-invalid" : ""
                            }`}
                            id="term"
                            placeholder="Nhập từ (ví dụ: Cat)"
                            {...register("term", {
                              required: "Bạn cần nhập từ!",
                            })}
                          />
                          <div className="invalid-feedback">
                            {errors?.term?.message}
                          </div>
                        </div>
                      </div>

                      <div className="mb-4">
                        <label
                          htmlFor="meaning"
                          className="form-label fw-semibold d-flex align-items-center"
                        >
                          <i className="bi bi-card-text me-2 fs-5 text-success"></i>{" "}
                          Nghĩa tiếng Việt
                        </label>
                        <div className="input-group">
                          <span className="input-group-text bg-success text-white">
                            <i className="bi bi-bookmark"></i>
                          </span>
                          <textarea
                            type="text"
                            className={`form-control border-start-0 ${
                              errors?.meaning ? "is-invalid" : ""
                            }`}
                            id="meaning"
                            placeholder="Nhập nghĩa (ví dụ: Con mèo)"
                            {...register("meaning", {
                              required: "Bạn cần nhập nghĩa!",
                            })}
                          />
                          <div className="invalid-feedback">
                            {errors?.meaning?.message}
                          </div>
                        </div>
                      </div>

                      <button
                        type="submit"
                        className="btn fw-bold w-100"
                        style={{
                          background:
                            "linear-gradient(45deg, #4caf50, #81c784)",
                          color: "white",
                          fontSize: "1.1rem",
                          padding: "0.6rem",
                          borderRadius: "0.4rem",
                          boxShadow: "0 4px 10px rgb(72 180 97 / 0.4)",
                          transition: "background 0.3s ease",
                        }}
                        onMouseEnter={(e) =>
                          (e.currentTarget.style.background =
                            "linear-gradient(45deg, #388e3c, #66bb6a)")
                        }
                        onMouseLeave={(e) =>
                          (e.currentTarget.style.background =
                            "linear-gradient(45deg, #4caf50, #81c784)")
                        }
                      >
                        💾 Lưu từ mới
                      </button>
                    </form>
                    {notification && (
                      <div
                        className="alert alert-success text-center"
                        role="alert"
                      >
                        {notification}
                      </div>
                    )}

                    {error && (
                      <div
                        className="alert alert-danger text-center"
                        role="alert"
                      >
                        {error}
                      </div>
                    )}
                  </div>
                  <div className="modal-footer">
                    <button
                      type="button"
                      className="btn btn-secondary"
                      data-bs-dismiss="modal"
                    >
                      Close
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="row mt-4">
          {words &&
            words?.map((word, index) => (
              <div key={index} className="col-md-6 col-lg-4 mb-4">
                <div className="card h-100 border-0 shadow-sm rounded-4 bg-white">
                  <div className="card-body text-center">
                    <h4 className="card-title text-capitalize text-primary fw-semibold">
                      {word.term}
                    </h4>
                    <p className="card-text text-dark fs-5">{word.meaning}</p>
                    {/* Nút hành động */}
                    <div className="d-flex justify-content-center gap-2 mt-3">
                      <div>
                        {/* Button trigger modal */}
                        <button
                          type="button"
                          className="btn btn-sm btn-outline-primary"
                          data-bs-toggle="modal"
                          data-bs-target="#editWord"
                          onClick={() => {
                            fetchFlashCardDetail(word.id);
                          }}
                        >
                          ✏️ Sửa
                        </button>

                        {/* Modal */}
                        <div
                          className="modal fade"
                          id="editWord"
                          tabIndex="-1"
                          aria-labelledby="exampleModalLabel"
                          aria-hidden="true"
                        >
                          <div className="modal-dialog">
                            <div className="modal-content">
                              <div className="modal-header">
                                <h5
                                  className="modal-title"
                                  id="exampleModalLabel"
                                >
                                  Chỉnh sửa từ vựng
                                </h5>
                                <button
                                  type="button"
                                  className="btn-close"
                                  data-bs-dismiss="modal"
                                  aria-label="Close"
                                ></button>
                              </div>
                              <div className="modal-body">
                                <form
                                  className="p-4 bg-white rounded shadow-sm"
                                  style={{
                                    maxWidth: 400,
                                    margin: "2rem auto",
                                    fontFamily:
                                      "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
                                  }}
                                >
                                  <div className="mb-4">
                                    <label
                                      htmlFor="term"
                                      className="form-label fw-semibold d-flex align-items-center"
                                    >
                                      <i className="bi bi-journal-text me-2 fs-5 text-primary"></i>{" "}
                                      Từ tiếng Anh
                                    </label>
                                    <div className="input-group">
                                      <span className="input-group-text bg-primary text-white">
                                        <i className="bi bi-type"></i>
                                      </span>
                                      <input
                                        type="text"
                                        className={`form-control border-start-0 ${
                                          errors?.term ? "is-invalid" : ""
                                        }`}
                                        id="term"
                                        name="term"
                                        value={flashcardDetail?.term}
                                        onChange={(e) => {
                                          handleChange(e);
                                        }}
                                      />
                                    </div>
                                  </div>

                                  <div className="mb-4">
                                    <label
                                      htmlFor="meaning"
                                      className="form-label fw-semibold d-flex align-items-center"
                                    >
                                      <i className="bi bi-card-text me-2 fs-5 text-success"></i>{" "}
                                      Nghĩa tiếng Việt
                                    </label>
                                    <div className="input-group">
                                      <span className="input-group-text bg-success text-white">
                                        <i className="bi bi-bookmark"></i>
                                      </span>
                                      <textarea
                                        type="text"
                                        className={`form-control border-start-0 ${
                                          errors?.meaning ? "is-invalid" : ""
                                        }`}
                                        id="meaning"
                                        value={flashcardDetail?.meaning}
                                        name="meaning"
                                        onChange={(e) => {
                                          handleChange(e);
                                        }}
                                      />
                                    </div>
                                  </div>

                                  <button
                                    type="button"
                                    onClick={() => {
                                      handleEdit(flashcardDetail.id);
                                    }}
                                    className="btn fw-bold w-100"
                                    style={{
                                      background:
                                        "linear-gradient(45deg, #4caf50, #81c784)",
                                      color: "white",
                                      fontSize: "1.1rem",
                                      padding: "0.6rem",
                                      borderRadius: "0.4rem",
                                      boxShadow:
                                        "0 4px 10px rgb(72 180 97 / 0.4)",
                                      transition: "background 0.3s ease",
                                    }}
                                    onMouseEnter={(e) =>
                                      (e.currentTarget.style.background =
                                        "linear-gradient(45deg, #388e3c, #66bb6a)")
                                    }
                                    onMouseLeave={(e) =>
                                      (e.currentTarget.style.background =
                                        "linear-gradient(45deg, #4caf50, #81c784)")
                                    }
                                  >
                                    💾 edit
                                  </button>
                                </form>
                                {notification && (
                                  <div
                                    className="alert alert-success text-center"
                                    role="alert"
                                  >
                                    {notification}
                                  </div>
                                )}

                                {error && (
                                  <div
                                    className="alert alert-danger text-center"
                                    role="alert"
                                  >
                                    {error}
                                  </div>
                                )}
                              </div>
                              <div className="modal-footer">
                                <button
                                  type="button"
                                  className="btn btn-secondary"
                                  data-bs-dismiss="modal"
                                >
                                  Close
                                </button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>

                      <button
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => {
                          if (
                            window.confirm(
                              "❗Bạn có chắc chắn muốn xóa từ này không?"
                            )
                          ) {
                            handleDelete(word.id);
                          }
                        }}
                      >
                        🗑️ Xóa
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          {words.length === 0 && (
            <div className="col-12 text-center text-muted">Chưa có từ nào.</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default FlashCardDetail;
