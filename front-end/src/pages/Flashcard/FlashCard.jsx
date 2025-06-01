import axios from "axios";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Outlet } from "react-router-dom";
const FlashCard = ({ userData }) => {
  return (
    <>
      <Outlet />
    </>
  );
};
export default FlashCard;
