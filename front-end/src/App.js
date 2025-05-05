import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import Home from "./pages/Home";
import Contact from "./pages/Contact";
import Exercises from "./pages/Exercises";
import TopUsers from "./pages/TopUsers";
import ListExercise from "./pages/ListExercise";
import DetailExercise from "./pages/DetailExercicse";
import Test from "./pages/Test";
import Login from "./pages/User/Login";
import Register from "./pages/User/Register";
import UserProfile from "./pages/User/UserProfile";
import ChangePassword from "./pages/User/ChangePassword";
import ListTopic from "./pages/ListTopic";
import { Navigate } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
function App() {
  const isLoggedIn = JSON.parse(localStorage.getItem("keepLoggedIn"));
  const token = localStorage.getItem("token");
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  console.log(localStorage);
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

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout userData={userData} />}>
            <Route index element={<Home />} />
            <Route path="exercises" element={<Exercises />}>
              <Route index element={<ListTopic />} />
              <Route path=":id" element={<ListExercise />}></Route>
              <Route path="lesson/:id" element={<DetailExercise />} />
            </Route>
            <Route path="top-users" element={<TopUsers />} />
            <Route path="contact" element={<Contact />} />
            <Route path="test" element={<Test />} />
            <Route
              path="login"
              element={isLoggedIn ? <Navigate to="/" /> : <Login />}
            />
            <Route
              path="register"
              element={isLoggedIn ? <Navigate to="/" /> : <Register />}
            />
            <Route
              path="user/profile"
              element={<UserProfile userData={userData} />}
            />
            <Route
              path="user/change-password"
              element={<ChangePassword userData={userData} />}
            />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
