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
function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="exercises" element={<Exercises />}>
              <Route
                path="english-conversations/1-at-home-1.399/listen-and-type"
                element={<DetailExercise />}
              />
            </Route>

            <Route path="top-users" element={<TopUsers />} />
            <Route path="contact" element={<Contact />} />
            <Route path="test" element={<Test />} />
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route path="user/profile" element={<UserProfile />} />
            <Route path="user/change-password" element={<ChangePassword />} />

            <Route path="exercises/short-stories" element={<ListExercise />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
