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
function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="exercises" element={<Exercises />} />
            <Route path="top-users" element={<TopUsers />} />
            <Route path="contact" element={<Contact />} />
            <Route path="exercises/short-stories" element={<ListExercise />} />
            <Route
              path="/exercises/english-conversations/1-at-home-1.399/listen-and-type"
              element={<DetailExercise />}
            />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
