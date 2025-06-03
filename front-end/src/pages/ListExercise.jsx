import ToggleDown from "../components/UI/ToggleDown";
import Navbar2 from "../components/Layout/Navbar2";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
const ListExercise = () => {
  const { id } = useParams();
  const [topics, setTopics] = useState([]);
  const [parentTopic, setParentTopic] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res1 = await axios.get(
          `http://localhost:8080/api/topics/parentTopic/${id}`
        );
        const res2 = await axios.get(`http://localhost:8080/api/topics/${id}`);
        console.log(res1.data);
        setTopics(res1.data);
        setParentTopic(res2.data);
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchData();
  }, [id]);

  return (
    <>
      <Navbar2 parentTopic={parentTopic} />
      <div className="container-lg">
        <div className="mb-4">
          {topics && (
            <>
              <ToggleDown topics={topics} />
            </>
          )}
        </div>
      </div>
    </>
  );
};
export default ListExercise;
