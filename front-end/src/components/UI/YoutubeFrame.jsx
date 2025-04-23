import { useEffect, useRef } from "react";
const YoutubeFrame = (props) => {
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
        <iframe
          frameborder="0"
          allowfullscreen
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          referrerpolicy="strict-origin-when-cross-origin"
          title="How to enter flow state"
          style={{
            position: "absolute",
            top: 0,
            left: 0,
            width: "100%",
            height: "100%",
          }}
          src={props.url}
          id="widget2"
          data-gtm-yt-inspected-16="true"
          data-gtm-yt-inspected-8="true"
        ></iframe>
      </div>
    </>
  );
};
export default YoutubeFrame;
