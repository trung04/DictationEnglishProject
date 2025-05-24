import { useState } from "react";
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

        {/* <button
          className="btn btn-sm bg-body-tertiary border position-absolute z-3"
          style={micButtonStyle}
        >
          <i className="bi bi-mic-fill"></i>
        </button> */}
      </div>
    </>
  );
};
export default Input;
