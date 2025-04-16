import Card from "../components/UI/Card";
import axios from "axios";
import { useState, useEffect } from "react";

const Exercises = () => {
  const [topics, setTopics] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/topics")
      .then((response) => {
        setTopics(response.data);
      })
      .catch((error) => {
        console.error("Lỗi khi lấy danh sách topics:", error);
      });
  }, []);

  return (
    <>
      <div className="container-lg mt-5">
        <h1>All Topics</h1>
        <div className="row center">
          {topics &&
            topics.map((topic, index) => {
              return (
                <div className="col-lg-4 mb-4">
                  <Card
                    name={topic.title}
                    level={topic.level.name}
                    detail={topic.detail}
                    SrcImage="https://dailydictation.com/images/dd-stories.jpg"
                  />
                </div>
              );
            })}
        </div>
      </div>
    </>
  );
};
export default Exercises;
