const Footer = () => {
  return (
    <>
      <footer id="footer" className="border-top mt-5 pt-3">
        <div className="container-lg text-left mb-4">
          <div className="row">
            <div className="col-6 col-md-3">
              <nav className="nav flex-column">
                <a className="nav-link" href="">
                  Home
                </a>
                <a className="nav-link" href="/exercises">
                  All exercises
                </a>
              </nav>
            </div>

            <div className="col-6 col-md-3">
              {/* <nav className="nav flex-column">
                <a className="nav-link" href="https://dailydictation.com/blog/">
                  Blog
                </a>
                <a className="nav-link" href="/contact">
                  Contact
                </a>
                <a className="nav-link" href="/terms-and-rules">
                  Terms &amp; rules
                </a>
                <a className="nav-link" href="/privacy-policy">
                  Privacy policy
                </a>
              </nav> */}
            </div>
            <div className="col-6 col-md-3">
              {/* <nav className="nav flex-column">
                <a
                  className="nav-link"
                  href="https://www.facebook.com/dailydictation"
                  target="_blank"
                  rel="noopener"
                  aria-label="Facebook"
                >
                  <i className="bi bi-facebook"></i> Follow us on Facebook
                </a>
                <a
                  className="nav-link"
                  href="https://www.tiktok.com/@daily_dictation"
                  target="_blank"
                  rel="noopener"
                  aria-label="TikTok"
                >
                  <i className="bi bi-tiktok"></i> Follow us on TikTok
                </a>
              </nav> */}
            </div>
          </div>
        </div>
        <p className="text-center bg-dark text-white p-3 m-0 border-top">
          © dailydictation
        </p>
      </footer>
    </>
  );
};

export default Footer;
