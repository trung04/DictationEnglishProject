const Register = () => {
  return (
    <>
      <main className="container-lg">
        <div className="message"></div>

        <div className="row mb-3">
          <div className="offset-md-3 col-sm-6 border rounded shadow p-3">
            <h1 className="fs-2 mb-4">Create an account</h1>
            <div className="">
              <div className="px-2">
                <div
                  id="g_id_onload"
                  data-client_id="137224404338-b39pf1qkslvhhlhllfque37qeljrd7sa.apps.googleusercontent.com"
                  data-callback="handleCredentialResponse"
                ></div>
                <div className="g_id_signin" data-type="standard">
                  <div
                    className="S9gUrf-YoZ4jf"
                    style={{ position: "relative" }}
                  >
                    <div></div>
                  </div>
                </div>
                <div className="text-danger d-none" id="google-signin-error">
                  An error happened, please try again later!
                </div>
              </div>
            </div>

            <hr />
            <h4>Or enter your information</h4>
            <form method="post" novalidate="novalidate">
              <div novalidate="novalidate">
                <div className="mb-3">
                  <label for="displayName" className="form-label required">
                    Nickname
                  </label>
                  <input
                    type="text"
                    id="displayName"
                    name="displayName"
                    required="required"
                    data-test="register-username"
                    className="form-control"
                  />{" "}
                </div>
                <div className="mb-3">
                  <label for="email" className="form-label required">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    required="required"
                    data-test="register-email"
                    className="form-control"
                  />{" "}
                </div>
                <div className="mb-3">
                  <label for="password" className="form-label required">
                    Password
                  </label>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    required="required"
                    className="form-control"
                  />{" "}
                </div>
                <div className="mb-3">
                  <button
                    type="submit"
                    id="submit"
                    name="submit"
                    className="btn-success btn"
                    data-test="register-submit-btn"
                  >
                    Submit
                  </button>
                </div>
                <input
                  type="hidden"
                  id="_token"
                  name="_token"
                  value="b95caaaec48d6.LFh9sOuaNM1iWE1FjRpm7W9v9vNOroFkCDqi-yRyiDo.YGgvnZn4A6c1MRIyuV02oTAAwpYW1MIGe3jVih0063JVaAf3gvhd-lEdOg"
                />
              </div>
            </form>
            <p className="pt-3">
              <span className="align-middle">
                Had an account already?
                <a href="/login">Login here</a>
              </span>
            </p>
          </div>
        </div>
      </main>
    </>
  );
};
export default Register;
