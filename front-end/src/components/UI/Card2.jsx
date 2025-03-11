const Card2=()=>{
    return(
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
                            <a
                              className="text-decoration-none"
                              href="/exercises/english-conversations/1-at-home-1.399/listen-and-type"
                            >
                              1. At home (1)
                            </a>
                          </div>
                          <div className="text-muted">
                            <small>
                              <small>10 parts</small>
                              <small>·</small>
                              <small>Vocab level: A1</small>
                            </small>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
        </>
    );
}
export default Card2;