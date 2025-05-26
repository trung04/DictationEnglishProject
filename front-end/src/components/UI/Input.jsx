import { useEffect, useState } from "react";
import useSpeechRecognition from "../../hooks/useSpeechRecognition";
const focusGuardStyle = {
  width: "1px",
  height: "0px",
  padding: "0px",
  overflow: "hidden",
  position: "fixed",
  top: "1px",
  left: "1px",
};

const micButtonStyle = {
  borderRadius: "50%",
  bottom: "-14px",
  right: "8px",
};
const Input = ({ onInputChange, inputText }) => {
  const { text, isListening, startListening, stopListening, hasSpeechSupport } =
    useSpeechRecognition();
  useEffect(() => {
    onInputChange(text);
  }, [text]);
  return (
    <>
      <div className="my-3 position-relative">
        <div
          data-focus-guard="true"
          tabIndex="-1"
          style={focusGuardStyle}
        ></div>

        <div data-focus-lock-disabled="disabled">
          <textarea
            id="dictationInput"
            className="form-control dictation__input"
            placeholder="Type what you hear..."
            autoComplete="off"
            autoCorrect="off"
            autoCapitalize="off"
            spellCheck="false"
            value={inputText}
            onChange={(e) => {
              onInputChange(e.target.value);
            }}
            style={{ height: "76px" }}
          ></textarea>
        </div>

        <div
          data-focus-guard="true"
          tabIndex="-1"
          style={focusGuardStyle}
        ></div>
        {hasSpeechSupport ? (
          <>
            {!isListening ? (
              <>
                <button
                  className="btn btn-sm bg-body-tertiary border position-absolute z-3"
                  onClick={() => {
                    startListening();
                  }}
                  style={micButtonStyle}
                >
                  <i className="bi bi-mic-fill"></i>
                </button>
              </>
            ) : (
              <>
                <div className="d-flex align-items-center gap-3 my-3">
                  <div className="text-danger fw-bold">
                    <i className="bi bi-record-circle-fill me-2"></i>{" "}
                    Recording...
                  </div>
                  <button
                    className="btn btn-outline-danger btn-sm"
                    onClick={() => {
                      stopListening();
                    }}
                  >
                    <i className="bi bi-stop-circle-fill me-1"></i>
                    Stop Recording
                  </button>
                </div>
              </>
            )}
          </>
        ) : null}
      </div>
    </>
  );
};
export default Input;
