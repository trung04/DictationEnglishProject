import { useEffect, useRef } from "react";
const YoutubeFrame = () => {
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
          src="https://www.youtube.com/embed/0rIjFCNay2Q?modestbranding=1&amp;title&amp;autohide=1&amp;wmode=transparent&amp;rel=0&amp;showinfo=0&amp;theme=light&amp;enablejsapi=1&amp;origin=https%3A%2F%2Fdailydictation.com&amp;widgetid=1&amp;forigin=https%3A%2F%2Fdailydictation.com%2Fexercises%2Fted-ed%2Fhow-to-enter-flow-state.1470%2Flisten-and-type&amp;aoriginsup=1&amp;gporigin=https%3A%2F%2Fdailydictation.com%2Fexercises%2Fted-ed&amp;vf=1"
          id="widget2"
          data-gtm-yt-inspected-16="true"
          data-gtm-yt-inspected-8="true"
        ></iframe>
      </div>
    </>
  );
};
export default YoutubeFrame;
