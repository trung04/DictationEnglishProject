import { Link } from "react-router-dom";
const Card2 = (props) => {
  const lesson = props.lesson;
  const { topicId } = props;
  return (
    <>
      <div className="bg-body-tertiary p-2 border rounded mb-3">
        <div className="d-flex align-items-center">
          <i
            className="bi bi-star star star-0 me-2 fs-5"
            data-bs-toggle="tooltip"
            data-bs-placement="bottom"
            aria-label="Completions: 0"
            data-bs-original-title="Completions: 0"
          ></i>
          <div className="flex-grow-1">
            <div>
              <div>
                <Link
                  className="text-decoration-none"
                  to={`/exercises/lesson/${lesson.lessonId}`}
                >
                  {props.index}. {lesson.title}
                </Link>
              </div>
              <div className="text-muted">
                <small>
                  {/* <small>10 parts</small> */}
                  {/* <small>·</small> */}
                  <small>Vocab level: {lesson.level.name}</small>
                </small>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default Card2;
