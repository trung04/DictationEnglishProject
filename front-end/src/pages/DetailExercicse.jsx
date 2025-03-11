import { Link } from "react-router-dom";
import Navbar2 from "../components/Layout/Navbar2";
import { useState } from "react";
import YoutubeFrame from "../components/UI/YoutubeFrame";
import ToggleDownSimple from "../components/UI/ToggleDownSimple";
const DetailExercise = () => {
  const [sizeVideo, setSizeVieo] = useState();
  //chia grid bootstrap có 12 cột
  const totalSize = 12;
  const remainderSize = 12 - sizeVideo;
  const [isHidden, setIsHidden] = useState(false);

  return (
    <>
      <Navbar2></Navbar2>

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
            How to enter flow state
          </h1>
          <div className="text-muted d-inline-block ms-1">
            <small>Vocab level: C1</small>
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
                  <YoutubeFrame sizeVideo={sizeVideo} />
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
              <div className="text-center" style={{ paddingBlock: "100px" }}>
                <button className="btn btn-lg btn-success">
                  Start Dictation
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default DetailExercise;
