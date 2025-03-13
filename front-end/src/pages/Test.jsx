import { useEffect, useRef } from "react";
import YoutubeFrame from "../components/UI/YoutubeFrame";
export default function CtrlClickDiv() {
  const videoRef = useRef(null);

  useEffect(() => {
    const handleKeyDown = (event) => {
      if (event.ctrlKey && videoRef.current) {
        videoRef.current.play();
      }
    };

    window.addEventListener("keydown", handleKeyDown);
    return () => window.removeEventListener("keydown", handleKeyDown);
  }, []);

  return (
    <div className="flex justify-center items-center h-screen">
      <video ref={videoRef} width="560" height="315" controls>
        <source src="video.mp4" type="video/mp4" />
      </video>
    </div>
  );
}
