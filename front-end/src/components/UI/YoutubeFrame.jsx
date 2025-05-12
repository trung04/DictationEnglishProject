import { useState, useEffect, useRef } from "react";
import YouTube from "react-youtube";
import axios from "axios";

const YoutubeFrame = ({ url, startTime, endTime }) => {
  const [userData, setUserData] = useState();
  const [error, setError] = useState();
  const playerRef = useRef(null);
  const videoId = url; // ID video
  const opts = {
    height: "100%",
    width: "100%",
    playerVars: {
      autoplay: 0,
      mute: 0,
    },
  };
  const onReady = (event) => {
    playerRef.current = event.target;
  };
  useEffect(() => {
    // Lấy token từ localStorage
    const token = localStorage.getItem("token");

    if (token) {
      // Nếu có token, gọi API để lấy thông tin người dùng
      axios
        .get("http://localhost:8080/api/user/account-info", {
          headers: {
            Authorization: `Bearer ${token}`, // Thêm token vào header
          },
        })
        .then((response) => {
          setUserData(response.data); // Lưu thông tin người dùng vào state
        })
        .catch((err) => {
          console.error(err);
          setError("Không thể lấy thông tin người dùng.");
        });
    } else {
      setError("Bạn chưa đăng nhập.");
    }
  }, []);
  //hàm xử lý phát video
  const handlePlayVideo = () => {
    if (!playerRef.current) return;

    const start = parseInt(startTime, 10);
    const end = parseInt(endTime, 10);
    const userId = userData.userId;
    const playedDuration = end - start;
    // const token = localStorage.getItem("token"); // hoặc từ React Context

    // Gọi API có gửi token
    axios
      .post("http://localhost:8080/api/user/add-time", null, {
        params: {
          userId: userData?.userId,
          seconds: playedDuration,
        },
        // headers: {
        //   Authorization: `Bearer ${token}`,
        // },
      })
      .then((response) => {
        console.log("Tổng thời gian đã cộng:", response.data);
      })
      .catch((error) => {
        console.log(error);
        console.log("Lỗi khi cộng thời gian:");
      });

    playerRef.current.seekTo(start, true);
    playerRef.current.playVideo();

    const interval = setInterval(() => {
      const currentTime = playerRef.current.getCurrentTime();
      if (currentTime >= end) {
        playerRef.current.pauseVideo();
        clearInterval(interval);
      }
    }, 500);
  };
  //hàm xử lý bắt sự kiện ctr
  useEffect(() => {
    const handleKeyDown = (event) => {
      if (event.ctrlKey && event.key === "Control") {
        handlePlayVideo();
      }
    };

    window.addEventListener("keydown", handleKeyDown);

    return () => {
      window.removeEventListener("keydown", handleKeyDown);
    };
  }, [startTime, endTime, userData]);

  return (
    <>
      <div
        class=""
        style={{
          position: "relative",
          width: "100%",
          paddingBottom: "56.25%", // 16:9 ratio
          height: 0,
          overflow: "hidden",
        }}
      >
        {" "}
        <YouTube
          videoId={videoId}
          opts={opts}
          onReady={onReady}
          className="mt-6"
          style={{
            position: "absolute",
            top: 0,
            left: 0,
            width: "100%",
            height: "100%",
          }}
        />
      </div>
      <button
        onClick={handlePlayVideo}
        className="btn btn-primary bg-green-600  px-6 py-2 rounded hover:bg-green-700"
      >
        Play {startTime} {endTime}
      </button>
    </>
  );
};
export default YoutubeFrame;
