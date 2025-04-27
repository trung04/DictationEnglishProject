import Card from "../components/UI/Card";
import axios from "axios";
import { useState, useEffect } from "react";
import { Outlet } from "react-router-dom";

const Exercises = () => {
  return (
    <>
      <div className="container-lg mt-5">
        <Outlet />
      </div>
    </>
  );
};
export default Exercises;
