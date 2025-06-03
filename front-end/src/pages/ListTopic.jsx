import ToggleDown from "../components/UI/ToggleDown";
import Navbar2 from "../components/Layout/Navbar2";
import axios from "axios";
import { useEffect, useState } from "react";
import Card from "../components/UI/Card";
const ListTopic = () => {
  const [topics, setTopics] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/topics")
      .then((response) => {
        setTopics(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("Lỗi khi lấy danh sách topics:", error);
      });
  }, []);
  return (
    <>
      <h1>All Topics</h1>

      <div className="row center">
        {topics &&
          topics?.map((topic, index) => {
            return (
              <div className="col-lg-4 mb-4" key={index}>
                <Card
                  topicId={topic?.topicId}
                  name={topic?.title}
                  level={topic?.level.name}
                  detail={topic?.detail}
                  SrcImage={`http://localhost:8080${topic?.parentImagePath}`}
                />
              </div>
            );
          })}
      </div>
    </>
  );
};
export default ListTopic;
