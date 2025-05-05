import { Link } from "react-router-dom";
const Navbar2 = (props) => {
  console.log(props);

  return (
    <>
      <nav className="container-lg mb-3">
        <ol className="breadcrumb">
          <li className="breadcrumb-item">
            <Link to="http://localhost:3000/exercises/">All topic</Link>
          </li>
          {props.parentTopic && (
            <li className="breadcrumb-item">{props.parentTopic.title}</li>
          )}
          {props.title && <li className="breadcrumb-item">{props.title}</li>}
        </ol>
      </nav>
    </>
  );
};
export default Navbar2;
