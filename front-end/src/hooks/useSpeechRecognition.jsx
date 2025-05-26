import { useState, useEffect } from "react";
let recognition = null;
const SpeechRecognition =
  window.SpeechRecognition || window.webkitSpeechRecognition;
if (SpeechRecognition) {
  recognition = new SpeechRecognition();
  recognition.continuous = true;
  recognition.lang = "en-US";
}
const useSpeechRecognition = () => {
  const [text, setText] = useState("");
  const [isListening, setIsListening] = useState(false);
  useEffect(() => {
    if (!recognition) return;
    recognition.onresult = (event) => {
      setText(event.results[0][0].transcript);
      setIsListening(false);
      recognition.stop();
    };
  }, [isListening]);
  const startListening = () => {
    setText("");
    setIsListening(true);
    recognition.start();
  };
  const stopListening = () => {
    setIsListening(false);
    recognition.stop();
  };
  return {
    text,
    isListening,
    startListening,
    stopListening,
    hasSpeechSupport: !!recognition,
  };
};
export default useSpeechRecognition;
