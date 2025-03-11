import { Link } from "react-router-dom";
const Navbar2 = (props) => {
  return (
    <>
      <nav className="container-lg mb-3">
        <ol className="breadcrumb">
          <li className="breadcrumb-item">
            <Link to="http://localhost:3000/exercises/">All topic</Link>
          </li>
          <li className="breadcrumb-item">hai </li>
        </ol>
      </nav>
    </>
  );
};
export default Navbar2;
