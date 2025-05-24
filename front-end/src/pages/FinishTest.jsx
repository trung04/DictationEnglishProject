import React from "react";
import { Link, useParams } from "react-router-dom";
const FinishTest = () => {
  const { lessonId } = useParams();

  return (
    <>
      <main className="container-lg mt-4">
        <div className="row">
          <div className="col-lg-12" id="dictation-layout">
            <div className="p-3 mb-4 border-0 border-start border-end border-bottom rounded-bottom shadow bg-body-tertiary">
              <div
                id="app-dictation-container"
                className="js-only js-tab-content"
                style={{ minHeight: "450px", display: "block" }}
              >
                <div id="app-dictation">
                  <div className="d-flex align-items-center pt-5">
                    <div className="flex-grow-1">
                      <div className="text-center">
                        <div className="fs-2">
                          You have completed this exercise, <br />
                          good job!
                        </div>
                        <i
                          className="bi bi-check-circle-fill text-success"
                          style={{ fontSize: "4rem" }}
                        ></i>
                        <div className="mt-3">
                          <Link
                            to={`/exercises/lesson/${lessonId}`}
                            className="btn btn-lg btn-outline-secondary mb-3 mx-2"
                          >
                            Repeat this exercise
                          </Link>
                        </div>
                        <div>
                          <Link to="/exercises">View all exercises</Link>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div
                id="app-transcript"
                className="js-only js-tab-content d-none"
                style={{ display: "block" }}
              ></div>
            </div>
          </div>
        </div>
      </main>
    </>
  );
};
export default FinishTest;
