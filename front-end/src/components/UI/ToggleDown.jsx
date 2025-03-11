import Card2 from "./Card2";
const ToggleDown = () => {
  return (
    <>
      <div className="accordion mb-3" id="groupsAccordion102">
        <div className="accordion-item">
          <h2 className="accordion-header">
            <button
              className="accordion-button p-2 collapsed"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#lessonGroup102"
              aria-expanded="false"
              aria-controls="groupsAccordion"
            >
              <span className="d-block d-sm-flex align-items-center flex-wrap">
                <span className="fw-bolder fs-5 d-inline-block me-2">
                  Section 1
                </span>
                <span className="d-block my-2">
                  <span
                    className="border rounded me-2 px-1 text-muted"
                    data-bs-toggle="tooltip"
                    data-bs-placement="bottom"
                    data-bs-original-title="20 lessons never completed."
                  >
                    <span>20</span>
                    <i className="bi bi-star star-0"></i>
                  </span>
                </span>
              </span>
            </button>
          </h2>
          <div id="lessonGroup102" className="accordion-collapse collapse">
            <div className="accordion-body px-2">
              <div className="row">
                <div className="col-lg-4">
                  <Card2 />
                  <Card2 />
                  <Card2 />
                </div>
                <div className="col-lg-4">
                  <Card2 />
                  <Card2 />
                  <Card2 />
                </div>
                <div className="col-lg-4">
                  <Card2 />
                  <Card2 />
                  <Card2 />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default ToggleDown;
