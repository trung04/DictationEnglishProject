import { Link, useParams } from "react-router-dom";
import Navbar2 from "../components/Layout/Navbar2";
import { useState, useEffect, useRef } from "react";
import YoutubeFrame from "../components/UI/YoutubeFrame";
import ToggleDownSimple from "../components/UI/ToggleDownSimple";
import Input from "../components/UI/Input";
import axios from "axios";
// dữ liệu mẫu

// Hàm so sánh văn bản và tìm các từ không khớp
const compareText = (inputText, correctText) => {
  const inputWords = inputText
    .toLowerCase()
    .replace(/^[\s\W_]+|[\s\W_]+$/g, "")
    .split(/\s+/)

    .filter((word) => word.length > 0);
  const correctWords = correctText
    .toLowerCase()
    .replace(/^[\s\W_]+|[\s\W_]+$/g, "")
    .split(/\s+/)

    .filter((word) => word.length > 0);
  const errors = [];

  for (let i = 0; i < Math.min(inputWords.length, correctWords.length); i++) {
    if (inputWords[i] !== correctWords[i]) {
      errors.push({ index: i, word: correctWords[i] });
    }
  }

  return {
    errors,
    inputLength: inputWords.length,
    correctLength: correctWords.length,
  };
};

const DetailExercise = ({ userData }) => {
  const { id } = useParams();
  const [sizeVideo, setSizeVieo] = useState();
  //chia grid bootstrap có 12 cột
  const totalSize = 12;
  const remainderSize = 12 - sizeVideo;
  const [isHidden, setIsHidden] = useState(false);
  //trạng thái làm bài
  const [showInput, setShowInput] = useState(false);
  // const inputWords = inputText.trim().split(" ");
  const [lesson, setLesson] = useState([]);

  //demo
  const [challengeId, setChallengeId] = useState(1);
  const [inputText, setInputText] = useState("");
  const [result, setResult] = useState(null);
  const [errors, setErrors] = useState([]);
  const [inputLength, setInputLength] = useState(0);
  const [correctLength, setCorrectLength] = useState(0);
  const [hideUnreached, setHideUnreached] = useState(true);
  const [challenges, setChallenges] = useState([]);

  //thoi gian chinh sua video YoutubeFrame
  const [startTime, setStartTime] = useState(0);
  const [endTime, setEndTime] = useState(2);

  //end demo

  //hàm kiểm tra đáp án có trùng hay không
  const handleCheck = () => {
    // Tìm challenge tương ứng với ID nhập vào
    const challenge = challenges.find((ch) => ch.id == challengeId);

    if (!challenge) {
      setResult("Không tìm thấy challenge!");
      setErrors([]);
      setInputLength(0);
      setCorrectLength(0);
      return;
    }

    const { errors, inputLength, correctLength } = compareText(
      inputText,
      challenge.text
    );
    setErrors(errors);
    setInputLength(inputLength);
    setCorrectLength(correctLength);

    if (inputText.trim().toLowerCase() === challenge.text.toLowerCase()) {
      setResult("Correct");
    } else {
      setResult("Incorrect");
    }
  };
  //hàm chuyển thành giây
  const convertTimeToSeconds = (time) => {
    const [minutes, seconds] = time.split(":").map(Number);
    return minutes * 60 + seconds;
  };
  //hàm xử lý bắt đầu làm bài
  const startDictation = async () => {
    const challenge = challenges.find((ch) => ch.id == challengeId);
    const startTime = await convertTimeToSeconds(challenge.start);
    const endTime = await convertTimeToSeconds(challenge.end);
    // Cập nhật các state sau khi tính toán thời gian
    setStartTime(startTime);
    setEndTime(endTime);

    setShowInput(true);
  };

  //hàm xử l
  useEffect(() => {
    const challenge = challenges.find((ch) => ch.id === challengeId);
    if (challenge) {
      const start = convertTimeToSeconds(challenge.start);
      const end = convertTimeToSeconds(challenge.end);
      setStartTime(start);
      setEndTime(end);
      setResult(null);
      setErrors([]);
      setInputText("");
      setInputLength(0);
      setCorrectLength(0);
    }
  }, [challengeId]);
  const handleChangeChallenge = async (status) => {
    // Kiểm tra trạng thái và cập nhật challengeId
    let newChallengeId = challengeId;
    if (status == 0 && challengeId > 1) {
      newChallengeId = challengeId - 1;
    }
    if (status == 1 && challengeId < challenges.length) {
      newChallengeId = challengeId + 1;
    }
    setChallengeId(newChallengeId);

    if (userData?.userId) {
      console.log("thành công blala");

      const addProgress = async () => {
        try {
          const res = await axios.post(
            "http://localhost:8080/api/progress/add",
            null,
            {
              params: {
                lessonStatus: 0,
                attempts: challengeId,
                userId: userData.userId,
                lessonId: id,
              },
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          console.log("thành công");
        } catch (error) {
          console.log(error);
        }
      };
      addProgress();
    }
  };

  // Hàm hiển thị văn bản với các từ sai được gạch dưới và từ chưa nhập hiển thị là * (nếu bật hideUnreached)
  const renderTextWithErrors = (text) => {
    const words = text.split(/\s+/).filter((word) => word.length > 0);
    return words.map((word, index) => {
      if (hideUnreached && index >= inputLength) {
        return (
          <span key={index} style={{ marginRight: "5px", color: "gray" }}>
            *
          </span>
        );
      }
      const isError = errors.some((error) => error.index === index);
      return (
        <span
          key={index}
          style={{
            textDecoration: isError ? "underline red" : "none",
            marginRight: "5px",
          }}
        >
          {word}
        </span>
      );
    });
  };
  //api kéo dữ liệu lesson
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/lessons/${id}`)
      .then((response) => {
        setLesson(response.data);
        setChallenges(JSON.parse(response.data.transcript));
      })
      .catch((error) => {
        console.error("Lỗi khi lấy danh sách topics:", error);
      });
  }, [id]);
  //api xử lý enter
  useEffect(() => {
    const handleKeyDown = (event) => {
      if (event.key === "Enter") {
        if (result === "Correct") {
          handleChangeChallenge(1); // Gọi hàm chuyển câu hỏi
        } else {
          // Không làm gì
          console.log("Kết quả sai, không chuyển câu");
        }
      }
    };

    window.addEventListener("keydown", handleKeyDown);

    return () => {
      window.removeEventListener("keydown", handleKeyDown);
    };
  }, [result]);

  //api kéo progress
  useEffect(() => {
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

          setChallengeId(res.data.find((item) => item.lessonId == id).attempts);
        } catch (error) {
          console.error("Lỗi khi fetch progress:", error);
        }
      };

      fetchData();
    }
  }, [userData?.userId, id]);

  return (
    <>
      <Navbar2 title={lesson.title}></Navbar2>
      <div className="container-lg">
        <div className="mb-3">
          <h1 className="fs-5 mb-0 d-inline">
            <i
              className="bi bi-star-fill star star-5 me-2 fs-5"
              data-bs-toggle="tooltip"
              data-bs-placement="bottom"
              aria-label="Completions: 5"
              data-bs-original-title="Completions: 5"
            ></i>
            {lesson.title}
          </h1>
          <div className="text-muted d-inline-block ms-1">
            <small>Vocab level: {lesson.level ? lesson.level.name : ""}</small>
          </div>
          <div className="text-muted d-inline-block ms-1">
            <div className="dropdown">
              <Link
                className="btn border-0"
                to="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i className="bi bi-three-dots text-muted"></i>
              </Link>

              <ul className="dropdown-menu">
                <li>
                  <Link className="dropdown-item" to="/user/reset-lesson/1470">
                    <i className="bi bi-arrow-clockwise"></i> Reset (keep the
                    star)
                  </Link>
                </li>
                <li>
                  <Link
                    className="dropdown-item js-btn-toggle-favorite"
                    to="#"
                    data-lesson-id="1470"
                    data-is-added="0"
                    data-should-alert="1"
                  >
                    <span className="js-add-favorite-text ">
                      <i className="bi bi-heart"></i>
                      Add to favorite list
                    </span>
                    <span className="js-remove-favorite-text d-none">
                      <i className="bi bi-heart"></i>
                      Remove from favorite list
                    </span>
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div
          id="app-dictation"
          className="js-only p-4 mb-4 border rounded shadow bg-body-tertiary"
          style={{ minHeight: "450px", display: "block" }}
        >
          <div className="row">
            <div className={`col-sm-7 col-lg-${sizeVideo}`}>
              <div className="position-relative">
                <div className="d-block d-lg-none text-end mb-2 position-absolute top-0 end-0">
                  <button
                    onClick={() => {
                      setIsHidden(!isHidden);
                    }}
                    className="btn btn-sm border bg-body"
                  >
                    Hide
                  </button>
                </div>
                <div
                  className={` ${
                    isHidden ? "" : "d-none"
                  } bg-secondary-subtle `}
                  style={{
                    position: "relative",
                    width: "100%",
                    paddingBottom: "56.25%", // 16:9 ratio
                    height: 0,
                    overflow: "hidden",
                    background: "blue",
                  }}
                >
                  <div className="text-center" style={{ marginTop: "25%" }}>
                    <button
                      onClick={() => {
                        setIsHidden(!isHidden);
                      }}
                      className="btn btn-outline-secondary"
                    >
                      Show Video
                    </button>
                  </div>
                </div>
                <div className={` ${isHidden ? "d-none" : ""}`}>
                  <YoutubeFrame
                    userData={userData}
                    startTime={startTime}
                    endTime={endTime}
                    url={lesson.url}
                    sizeVideo={sizeVideo}
                  />
                </div>
              </div>
              <div className="mt-1 d-none d-lg-block">
                <ToggleDownSimple value={sizeVideo} onChange={setSizeVieo} />
                <button
                  onClick={() => {
                    setIsHidden(!isHidden);
                  }}
                  className="btn border"
                >
                  {isHidden ? "Show Video" : "Hide Video"}
                </button>
              </div>
            </div>
            <div className={`col-sm-5 col-lg-${12 - sizeVideo}`}>
              <div className="text-center">
                {!showInput ? (
                  <div
                    className=" text-center"
                    style={{ paddingBlock: "100px" }}
                  >
                    <button
                      onClick={() => {
                        startDictation();
                      }}
                      className="btn btn-lg btn-success"
                    >
                      Bắt đầu làm bài
                    </button>
                  </div>
                ) : (
                  <>
                    <div class="d-flex align-items-center flex-wrap">
                      <div class="flex-grow-1 d-flex">
                        <div class="d-flex align-items-center">
                          <button
                            onClick={() => {
                              handleChangeChallenge(0);
                            }}
                            id="btn-arrow-left"
                            class="btn btn-sm border-0"
                            disabled=""
                            style={{ fontSize: "1rem" }}
                          >
                            <i class="bi bi-lg bi-arrow-left"></i>
                          </button>
                          <div class="mx-1 d-flex align-items-center">
                            <div class="dropdown">
                              <button
                                type="button"
                                id="react-aria6118915310-1"
                                aria-expanded="false"
                                class="border-0 px-0 none btn btn-none"
                              >
                                <span>{challengeId}</span>
                                <span> / </span>
                                <span>55</span>
                              </button>
                            </div>
                          </div>
                          <button
                            onClick={() => {
                              handleChangeChallenge(1);
                            }}
                            id="btn-arrow-right"
                            class="btn btn-sm border-0"
                            style={{ fontSize: "1rem" }}
                          >
                            <i class="bi bi-arrow-right"></i>
                          </button>
                        </div>
                      </div>
                      <div>
                        <button class="btn text-muted">
                          <i class="bi bi-gear-fill"></i>
                        </button>
                      </div>
                    </div>

                    <Input inputText={inputText} onInputChange={setInputText} />
                    <div class="d-flex align-items-center mb-3">
                      <div class="flex-grow-1">
                        <button
                          onClick={() => {
                            handleCheck();
                          }}
                          id="btn-check"
                          class="btn btn-primary me-3"
                        >
                          Check
                        </button>
                        <button id="btn-skip" class="btn btn-outline-secondary">
                          Skip
                        </button>
                      </div>
                      {result == "Correct" && (
                        <button
                          id="btn-next"
                          class="btn btn-success ms-2"
                          onClick={() => {
                            handleChangeChallenge(1);
                          }}
                        >
                          Next
                        </button>
                      )}
                    </div>

                    <div className="result-section">
                      {result && <h3>{result}!</h3>}
                      {result &&
                        challenges.find((ch) => ch.id == challengeId) && (
                          <div>
                            <label>
                              <input
                                type="checkbox"
                                checked={hideUnreached}
                                onChange={(e) =>
                                  setHideUnreached(e.target.checked)
                                }
                              />
                              Ẩn các từ chưa nhập (hiển thị *)
                            </label>

                            <p
                              style={{
                                wordBreak: "break-word",
                                whiteSpace: "normal",
                              }}
                            >
                              {renderTextWithErrors(
                                challenges.find((ch) => ch.id == challengeId)
                                  .text
                              )}
                            </p>
                          </div>
                        )}
                    </div>
                  </>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default DetailExercise;
