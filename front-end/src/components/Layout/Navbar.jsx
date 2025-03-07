import { Link } from "react-router-dom";
const Navbar = () => {
  return (
    <>
      <nav className="navbar navbar-expand-lg border-bottom p-0 bg-body-tertiary">
        <div className="container-lg">
          <Link to="/" className="navbar-brand fs-4 d-flex align-items-center">
            <img
              src="https://dailydictation.com/dailydictation.svg"
              alt="DailyDictation Logo"
              className="me-2"
              width="24"
              height="24"
            />
            <span className="fw-semibold">DailyDictation</span>
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#primaryNavbar"
            aria-controls="primaryNavbar"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <i className="bi bi-list fs-2"></i>
          </button>
          <div className="collapse navbar-collapse" id="primaryNavbar">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item me-1">
                <Link className="nav-link px-3 py-1 active" to="/exercises">
                  All exercises
                </Link>
              </li>
              <li className="nav-item me-1">
                <Link className="nav-link px-3 py-1" to="/top-users">
                  Top users
                </Link>
              </li>

              <li className="nav-item me-1 dropdown active">
                <Link
                  href="#"
                  id="nav-dropdown-videos"
                  className="nav-link dropdown-toggle px-3 py-1"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Video lessons
                </Link>
                <div
                  className="dropdown-menu"
                  aria-labelledby="nav-dropdown-videos"
                >
                  <Link className="dropdown-item" to="/expressions">
                    English expressions
                  </Link>
                  <Link className="dropdown-item" to="/english-pronunciation">
                    English pronunciation
                  </Link>
                </div>
              </li>
             
            </ul>
          </div>
        </div>
      </nav>
      <nav className="navbar navbar-expand border-bottom bg-body py-0 shadow-sm">
      <div className="container-lg d-flex">
        <div className="flex-grow-1">
          <span
            className="nav-link ps-0 d-inline"
            title="Time you've spent practicing today"
            data-bs-toggle="tooltip"
            data-bs-placement="bottom"
          >
            <i className="bi bi-clock-history me-1"></i>
            <span id="time-spent"> 0 minutes </span>
          </span>
        </div>
        <div>
          <ul className="navbar-nav">
            <li
              className="nav-item dropdown-center js-incomplete-lessons-dropdown me-2"
              data-fetch-incomplete-lessons-url="/api/user/incomplete-lessons"
            >
              <Link
                to="#"
                id="incomplete-lessons-toggle"
                className="dropdown-toggle nav-link"
                title="The exercises you have started but not finished"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i className="bi bi-star-half"></i>
                <span className="d-none d-lg-inline">In-progress</span>
              </Link>
              <div
                className="dropdown-menu shadow mt-0"
                aria-labelledby="#incomplete-lessons-toggle"
                style={{ width: "300px" }}
              >
                <div className="js-no-incomplete-lessons d-none px-2">
                  You don't have any incomplete exercises!
                </div>
                <div className="js-incomplete-lessons-container"></div>
              </div>
            </li>
            <li className="nav-item me-2" id="app-user-notes"></li>
            <li className="nav-item me-2 dropdown-center js-dropdown-hover">
              <Link
                to="#"
                id="account-dropdown-toggle"
                className="dropdown-toggle nav-link"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i className="bi bi-person-circle"></i>
                <span className="d-none d-md-inline"> jimmy </span>
              </Link>
              <div
                className="dropdown-menu dropdown-menu-right dropdown-menu-md-start shadow mt-0"
                aria-labelledby="#account-dropdown-toggle"
              >
                <Link className="dropdown-item" to="/profile/337536">
                  Public profile
                </Link>
                <Link className="dropdown-item" to="/user/profile">
                  Account information
                </Link>
                <Link className="dropdown-item" to="/user/notifications">
                  Notifications
                  <span className="badge bg-danger rounded-pill"></span>
                </Link>
                <Link className="dropdown-item" to="/user/comments">
                  Comments
                </Link>
                <Link className="dropdown-item" to="/user/favorite-lessons">
                  Favorite lessons
                </Link>
                <Link className="dropdown-item" to="/user/change-password">
                  Change password
                </Link>
                <Link className="dropdown-item" to="/user/edit-email">
                  Change Email
                </Link>
                <div className="dropdown-divider"></div>
                <Link className="dropdown-item" to="/logout">
                  Logout
                </Link>
              </div>
            </li>
            <li className="nav-item dropdown">
              <button
                className="btn btn-link nav-link p-2 dropdown-toggle d-flex align-items-center border-0"
                id="bd-theme"
                type="button"
                title="Switch theme"
                aria-expanded="false"
                data-bs-toggle="dropdown"
                data-bs-display="static"
                aria-label="Toggle theme (dark)"
              > 
                <i className="bi bi-sun-fill"></i>
                <span id="bd-theme-text"></span>
              </button>
              <ul
                className="dropdown-menu dropdown-menu-end"
                aria-labelledby="bd-theme-text"
              >
                <li>
                  <button
                    type="button"
                    className="dropdown-item d-flex align-items-center"
                    data-bs-theme-value="light"
                    aria-pressed="false"
                  >
                    <i className="bi bi-sun-fill me-2"></i> Light
                  </button>
                </li>
                <li>
                  <button
                    type="button"
                    className="dropdown-item d-flex align-items-center active"
                    data-bs-theme-value="dark"
                    aria-pressed="true"
                  >
                    <i className="bi bi-moon-stars-fill me-2"></i> Dark
                  </button>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
      
    </>
  );
};

export default Navbar;
