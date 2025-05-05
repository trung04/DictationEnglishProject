import Footer from "./Footer";
import Navbar from "./Navbar";
import { Outlet } from "react-router-dom"; // Hiển thị nội dung từng trang
const Layout = (props) => {
  const userData = props.userData;
  return (
    <div className="flex flex-col min-h-screen">
      <Navbar userData={userData} />
      <main className="flex-grow">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};
export default Layout;
