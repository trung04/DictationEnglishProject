import ToggleDown from "../components/UI/ToggleDown";
import Navbar2 from "../components/Layout/Navbar2";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
const ListExercise = () => {
  const { id } = useParams();
  const [topics, setTopics] = useState([]);
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/topics/parentTopic/${id}`)
      .then((response) => {
        setTopics(response.data);
      })
      .catch((error) => {
        console.error("Lỗi khi lấy danh sách topics:", error);
      });
  }, []);

  return (
    <>
      <Navbar2 />
      <div className="container-lg">
        <div className="mb-4">
          <ToggleDown topics={topics} />
        </div>
      </div>
    </>
  );
};
export default ListExercise;
