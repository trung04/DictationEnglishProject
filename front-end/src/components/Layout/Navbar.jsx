import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import NotePopup from "../../pages/Note/NotePopup";
import axios from "axios";

const Navbar = ({ userData }) => {
  const [progress, setProgress] = useState([]);
  const [showNotes, setShowNotes] = useState(false);
  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
    window.location.reload();
  };
  const fetchProgress = () => {
    const token = localStorage.getItem("token");

    if (userData?.userId && token) {
      const fetchData = async () => {
        try {
          const res = await axios.get(
            `http://localhost:8080/api/progress/user/${userData.userId}`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
          setProgress(res.data);
          console.log(res.data);
        } catch (error) {
          console.error("Lỗi khi fetch progress:", error);
        }
      };

      fetchData();
    }
  };

  useEffect(() => {
    fetchProgress();
  }, [userData?.userId]);
  const handleDeleteProgress = async (id) => {
    const confirmDelete = window.confirm(
      "Bạn có chắc chắn muốn xóa không?" + id
    );
    if (confirmDelete) {
      try {
        const res = await axios.delete(
          `http://localhost:8080/api/progress/delete/${id}`
        );
        alert("xóa thành công");
        await fetchProgress();
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg border-bottom p-0 bg-body-tertiary">
        <div className="container-lg">
          <Link to="/" className="navbar-brand fs-4 d-flex align-items-center">
            <img
              src="https://dailydictation.com/dailydictation.svg"
              alt="DailyDictation Logo"
              className="me-2"
              width="24"
              height="24"
            />
            <span className="fw-semibold">DailyDictation</span>
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#primaryNavbar"
            aria-controls="primaryNavbar"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <i className="bi bi-list fs-2"></i>
          </button>
          <div className="collapse navbar-collapse" id="primaryNavbar">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item me-1">
                <Link className="nav-link px-3 py-1 active" to="/exercises">
                  All exercises
                </Link>
              </li>
              {/* <li className="nav-item me-1">
                <Link className="nav-link px-3 py-1" to="/top-users">
                  Top users
                </Link>
              </li> */}
            </ul>
          </div>
        </div>
      </nav>
      <nav className="navbar navbar-expand border-bottom bg-body py-0 shadow-sm">
        <div className="container-lg d-flex">
          <div className="flex-grow-1">
            <span
              className="nav-link ps-0 d-inline"
              title="Time you've spent practicing today"
              data-bs-toggle="tooltip"
              data-bs-placement="bottom"
            >
              <i className="bi bi-clock-history me-1"></i>
              <span id="time-spent">
                {" "}
                {userData?.activeHours
                  ? Math.round(userData.activeHours / 60)
                  : "0"}{" "}
                minutes{" "}
              </span>
            </span>
          </div>
          <div>
            <ul className="navbar-nav">
              {userData ? (
                <>
                  <li
                    className="nav-item dropdown-center js-incomplete-lessons-dropdown me-2"
                    data-fetch-incomplete-lessons-url="/api/user/incomplete-lessons"
                  >
                    <Link
                      to="#"
                      id="incomplete-lessons-toggle"
                      className="dropdown-toggle nav-link"
                      title="The exercises you have started but not finished"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      <i className="bi bi-star-half"></i>
                      <span className="d-none d-lg-inline">In-progress</span>
                    </Link>
                    <div
                      className="dropdown-menu shadow mt-0"
                      aria-labelledby="#incomplete-lessons-toggle"
                      style={{ width: "300px" }}
                    >
                      <div className="js-no-incomplete-lessons  px-2">
                        {progress && Object.keys(progress).length > 0 ? (
                          Object.entries(progress).map(([key, prog]) => {
                            return (
                              <div className="p-2 js-user-lesson-item">
                                <div className="d-flex align-items-center">
                                  <a
                                    href={`/exercises/lesson/${prog.lessonId}`}
                                    className="mr-2 text-decoration-none flex-grow-1"
                                    style={{ whiteSpace: "normal" }}
                                  >
                                    {prog.lesson.title}
                                  </a>

                                  <button
                                    className="btn btn-sm js-remove-in-progress-lesson"
                                    onClick={() => {
                                      handleDeleteProgress(prog.progressId);
                                    }}
                                  >
                                    <i className="bi bi-x-lg fs-5"></i>
                                  </button>
                                </div>
                                <div
                                  className="progress"
                                  style={{ height: "5px" }}
                                >
                                  <div
                                    className="progress-bar bg-success"
                                    role="progressbar"
                                    data-test="progress-bar"
                                    style={{
                                      width: `${
                                        (prog.attempts /
                                          prog.lesson.questionCount) *
                                        100
                                      }%`,
                                    }}
                                  ></div>
                                </div>
                              </div>
                            );
                          })
                        ) : (
                          <>
                            <div>You don't have any incomplete exercises!</div>
                          </>
                        )}
                      </div>
                      <div className="js-incomplete-lessons-container"></div>
                    </div>
                  </li>
                  <li className="nav-item me-2" id="app-user-notes">
                    <Link
                      to="#"
                      className="nav-link"
                      onClick={() => setShowNotes(true)}
                      title="Your notes"
                    >
                      <i className="bi bi-journal-text"></i>
                      <span className="d-none d-md-inline ms-1">Notes</span>
                    </Link>
                  </li>
                  <li className="nav-item me-2 dropdown-center js-dropdown-hover">
                    <Link
                      to="#"
                      id="account-dropdown-toggle"
                      className="dropdown-toggle nav-link"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      <i className="bi bi-person-circle"></i>
                      <span className="d-none d-md-inline">
                        {userData.username}
                      </span>
                    </Link>
                    <div
                      className="dropdown-menu dropdown-menu-right dropdown-menu-md-start shadow mt-0"
                      aria-labelledby="#account-dropdown-toggle"
                    >
                      <Link className="dropdown-item" to="/user/profile">
                        Account information
                      </Link>
                      {/* <Link className="dropdown-item" to="/user/notifications">
                      Notifications
                      <span className="badge bg-danger rounded-pill"></span>
                    </Link> */}
                      {/* <Link className="dropdown-item" to="/user/comments">
                        Comments
                      </Link> */}

                      <Link
                        className="dropdown-item"
                        to="/user/change-password"
                      >
                        Change password
                      </Link>

                      <div className="dropdown-divider"></div>
                      <button
                        className="dropdown-item"
                        onClick={() => {
                          handleLogout();
                        }}
                      >
                        Logout
                      </button>
                    </div>
                  </li>
                </>
              ) : (
                <>
                  <li>
                    <Link
                      to="/login"
                      className="  p-2  d-flex align-items-center border-0"
                    >
                      Login
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/register"
                      className="  p-2  d-flex align-items-center border-0"
                    >
                      Register
                    </Link>
                  </li>
                </>
              )}
            </ul>
          </div>
        </div>
      </nav>
      {showNotes && (
        <NotePopup isOpen={showNotes} onClose={() => setShowNotes(false)} />
      )}
    </>
  );
};

export default Navbar;
