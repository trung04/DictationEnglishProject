import { Link } from "react-router-dom";
const Login = () => {
  return (
    <>
      <main className="container-lg">
        <div className="message"></div>
        <div className="row">
          <div className="offset-md-3 col-md-6 border rounded shadow p-3">
            <h1 className="fs-2 mb-4">Login</h1>
            <div className="">
              <div className="px-2">
                <div className="g_id_signin" data-type="standard">
                  <div
                    className="S9gUrf-YoZ4jf"
                    style={{ position: "relative" }}
                  >
                    <div></div>
                    <iframe
                      src="https://accounts.google.com/gsi/button?type=standard&amp;is_fedcm_supported=true&amp;client_id=137224404338-b39pf1qkslvhhlhllfque37qeljrd7sa.apps.googleusercontent.com&amp;iframe_id=gsi_532032_595227&amp;cas=GxYJvmgqcnhb7nZVhal2Ygn53bzUx9GWOf9idoau8Oc"
                      allow="identity-credentials-get"
                      id="gsi_532032_595227"
                      title="Nút Đăng nhập bằng Google"
                      style={{
                        display: "block",
                        position: "relative",
                        top: "0px",
                        left: "0px",
                        height: "44px",
                        width: "399px",
                        border: "0px",
                        margin: "-2px -10px",
                      }}
                    ></iframe>
                  </div>
                </div>
                <div className="text-danger d-none" id="google-signin-error">
                  An error happened, please try again later!
                </div>
              </div>
            </div>
            <hr />
            <h4>Or enter your password</h4>
            <form method="post">
              <div>
                <div className="mb-3">
                  <label for="_username" className="form-label required">
                    Username or Email
                  </label>
                  <input
                    type="text"
                    id="_username"
                    name="_username"
                    required="required"
                    data-test="login-username"
                    className="form-control"
                  />{" "}
                </div>
                <div className="mb-3">
                  <label for="_password" className="form-label required">
                    Password
                  </label>
                  <input
                    type="password"
                    id="_password"
                    name="_password"
                    required="required"
                    data-test="login-password"
                    className="form-control"
                  />{" "}
                </div>
                <div className="mb-3">
                  <button
                    type="submit"
                    id="submit"
                    name="submit"
                    className="btn-success btn"
                    data-test="login-submit-btn"
                  >
                    Submit
                  </button>
                </div>
                <input
                  type="hidden"
                  id="_token"
                  name="_token"
                  value="8eec7879d127d9acfb5e2ba6ad7ad.XL6cVzZQw3ricStzP35AGCdrwEe0hjlhMF_pEqIgVCU.Gt_-HUM7piKrCX0SbhwnfnMv9TaA_BRSZGavXdVPDUg2ivs_eSCHL69HZg"
                />
              </div>
            </form>
            <p className="pt-3">
              Forgot your password?{" "}
              <Link to="/forgot-password">Click here</Link>
            </p>
            <p>
              Haven't had an account? <Link to="/register">Register here</Link>
            </p>
          </div>
        </div>
      </main>
    </>
  );
};
export default Login;
