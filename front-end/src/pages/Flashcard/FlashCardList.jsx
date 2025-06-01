import axios from "axios";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
const FlashCardList = ({ userData }) => {
  console.log(userData);
  const [listFC, setListFC] = useState([]);
  const [error, setError] = useState();
  const [flashcard, setFlashcard] = useState();

  const fetchFlashCardDetail = async (flashcardId) => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/flashcards/${flashcardId}`
      );
      setFlashcard(res.data);
      console.log(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchFlashCard = async () => {
    try {
      const userId = Number(userData?.userId); // ép kiểu để tránh lỗi khi backend yêu cầu Long
      if (!userId) {
        console.error("Invalid userId:", userData?.userId);
        return;
      }

      const res = await axios.get(
        `http://localhost:8080/api/flashcards/user/${userId}`
      );
      setListFC(res.data);
      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };
  // Gọi khi component mount hoặc userData thay đổi
  useEffect(() => {
    if (userData?.userId) {
      fetchFlashCard();
    }
  }, [userData]);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();
  const [notification, setNotificaton] = useState(null);

  const handlSub = async (data) => {
    const token = await localStorage.getItem("token"); // giả sử JWT token được lưu ở đây
    const { title, description } = data;
    try {
      const res = await axios.post(
        `http://localhost:8080/api/flashcards/user/${userData?.userId}?title=${title}&description=${description}`,
        null, // không có body, nên để null
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json", // hoặc có thể bỏ vì không dùng JSON body
          },
        }
      );
      fetchFlashCard();
      setNotificaton("Add successfully !");
      setTimeout(() => {
        setNotificaton(null);
      }, 3000);
      reset();
      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFlashcard((prev) => ({ ...prev, [name]: value }));
  };
  const handleEditChange = async (flashcardId) => {
    try {
      const res = await axios.put(
        `http://localhost:8080/api/flashcards/${flashcardId}?title=${flashcard?.title}&&description=${flashcard?.description}`,
        null,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      setNotificaton("Edit successfully!");
      fetchFlashCard();
      setTimeout(() => {
        setNotificaton(null);
      }, 3000);
    } catch (error) {
      setError("Edit failed!");
      setTimeout(() => {
        setError(null);
      }, 3000);
      console.log(error);
    }
  };

  const handleDeleteFC = async (flashcardId) => {
    alert(flashcardId);
    try {
      const res = await axios.delete(
        `http://localhost:8080/api/flashcards/${flashcardId}`
      );
      await fetchFlashCard();
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

  return (
    <>
      <div className="container mt-4">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="mb-0">List FlashCard</h2>
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
          <button
            type="button"
            className="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#addFlashCard"
          >
            Add new
          </button>

          {/* Modal */}
          <div
            className="modal fade"
            id="addFlashCard"
            tabIndex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
          >
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="exampleModalLabel">
                    Add Flashcard
                  </h5>
                  <button
                    type="button"
                    className="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div className="modal-body">
                  <form onSubmit={handleSubmit(handlSub)}>
                    <div className="mb-3">
                      <label htmlFor="title" className="form-label">
                        Title
                      </label>
                      <input
                        type="text"
                        name="title"
                        id="title"
                        className="form-control"
                        placeholder="Enter title"
                        {...register("title", {
                          required: "title's required!",
                        })}
                      />
                      <p className="text-danger">{errors?.title?.message}</p>
                    </div>

                    <div className="mb-3">
                      <label htmlFor="description" className="form-label">
                        Description
                      </label>
                      <input
                        type="text"
                        name="description"
                        id="description"
                        className="form-control"
                        placeholder="Enter description"
                        {...register("description", {
                          required: "description's required!",
                        })}
                      />
                      <p className="text-danger">
                        {errors?.description?.message}
                      </p>
                    </div>

                    <button type="submit" className="btn btn-primary">
                      Add
                    </button>
                    {notification && (
                      <div class="alert alert-success" role="alert">
                        {notification}
                      </div>
                    )}
                  </form>
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

        <div className="row">
          {listFC && listFC.length > 0 ? (
            listFC.map((item, index) => (
              <div key={item.id || index} className="col-md-4 mb-4">
                <div className="card h-100 shadow border-0 rounded-3">
                  <div className="card-body d-flex flex-column justify-content-between">
                    <div>
                      <h5 className="card-title">
                        <Link
                          to={`/flashcard/${item.id}`}
                          className="text-decoration-none text-primary"
                        >
                          {item.title}
                        </Link>
                      </h5>
                      <p className="card-text text-muted">
                        <strong>Mô tả:</strong> {item.description}
                      </p>
                    </div>
                    <div className="mt-3 d-flex justify-content-between">
                      <button
                        type="button"
                        className="btn btn-sm btn-warning"
                        data-bs-toggle="modal"
                        data-bs-target="#editFlashCard"
                        onClick={() => {
                          fetchFlashCardDetail(item.id);
                        }}
                      >
                        ✏️ Sửa
                      </button>

                      {/* Modal */}
                      <div
                        className="modal fade"
                        id="editFlashCard"
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
                                Edit Flashcard
                              </h5>
                              <button
                                type="button"
                                className="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                              ></button>
                            </div>
                            <div className="modal-body">
                              <form onSubmit={handleSubmit(handlSub)}>
                                <div className="mb-3">
                                  <label htmlFor="title" className="form-label">
                                    Title
                                  </label>
                                  <input
                                    type="text"
                                    name="title"
                                    id="title"
                                    className="form-control"
                                    value={flashcard?.title}
                                    placeholder="Enter title"
                                    onChange={(e) => {
                                      handleChange(e);
                                    }}
                                  />
                                </div>

                                <div className="mb-3">
                                  <label
                                    htmlFor="description"
                                    className="form-label"
                                  >
                                    Description
                                  </label>
                                  <input
                                    type="text"
                                    name="description"
                                    id="description"
                                    className="form-control"
                                    value={flashcard?.description}
                                    placeholder="Enter description"
                                    onChange={(e) => {
                                      handleChange(e);
                                    }}
                                  />
                                </div>

                                <button
                                  type="submit"
                                  className="btn btn-primary"
                                  onClick={() => {
                                    handleEditChange(item.id);
                                  }}
                                >
                                  Edit
                                </button>
                                {notification && (
                                  <div class="alert alert-success" role="alert">
                                    {notification}
                                  </div>
                                )}
                              </form>
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

                      <button
                        className="btn btn-sm btn-danger"
                        onClick={() => {
                          if (
                            window.confirm(
                              "Bạn có chắc chắn muốn xóa thẻ này không?"
                            )
                          ) {
                            handleDeleteFC(item.id);
                          }
                        }}
                      >
                        🗑️ Xóa
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <p>Chưa có flashcard nào.</p>
          )}
        </div>
      </div>
    </>
  );
};
export default FlashCardList;
