import { Link } from "react-router-dom";
const Card = (props)=>{
    return(
        <>
        <div className="card border shadow-sm bg-body-tertiary">
          <div className="card-body">
            <div className="d-flex">
                            <div className="me-3">
                <Link to="/exercises/short-stories" className="img-thumbnail p-1 d-block">
                <img src={props.SrcImage} alt="Short Stories" width="80" height="80"/>
                </Link>
              </div>
                            <div className="flex-grow-1">
                <h2 className="fs-4 mb-2">
                  <a href="/exercises/short-stories">{props.Name}</a>
                </h2>
                <div>
                  <span className="text-muted">Levels: A1-C1</span>
                </div>
                <div className="text-muted d-flex align-items-center">
                  <i className="me-1 bi bi-soundwave opacity-75"></i>
                  <span className="me-1">289 lessons</span>
                  <button className="btn btn-sm js-btn-show-desc" data-course-id="2"><i className="bi bi-three-dots"></i></button>
                </div>
              </div>
            </div>
            <div className="card-text mt-2 d-none" id="course-desc-2">
              <p>A collection of audio articles introducing culture, people, places, historical events and daily life in English-speaking countries, especially Canada and America.</p>
            </div>
          </div>
        </div>
       
        </>
    );
}
export default Card;