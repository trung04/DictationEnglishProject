import Card2 from "./Card2";
const ToggleDown = (props) => {
  const topics = props.topics;
  return (
    <>
      {topics &&
        topics?.map((topic) => {
          return (
            <div className="accordion mb-3" key={topic.topicId}>
              <div className="accordion-item">
                <h2 className="accordion-header">
                  <button
                    className="accordion-button p-2 collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target={`#${topic.topicId}`}
                    aria-expanded="false"
                    aria-controls="groupsAccordion"
                  >
                    <span className="d-block d-sm-flex align-items-center flex-wrap">
                      <span className="fw-bolder fs-5 d-inline-block me-2">
                        {topic.title}
                      </span>
                      <span className="d-block my-2">
                        <span className="border rounded me-2 px-1 text-muted">
                          <span>{topic.lessons.length}</span>
                          <i className="bi bi-star star-0"></i>
                        </span>
                      </span>
                    </span>
                  </button>
                </h2>
                <div
                  id={`${topic.topicId}`}
                  className="accordion-collapse collapse"
                >
                  <div className="accordion-body px-2">
                    <div className="row">
                      {topic.lessons &&
                        topic.lessons.map((lesson, index) => {
                          return (
                            <div className="col-lg-4" key={lesson.id}>
                              <Card2 index={index + 1} lesson={lesson} />
                            </div>
                          );
                        })}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          );
        })}
    </>
  );
};
export default ToggleDown;
