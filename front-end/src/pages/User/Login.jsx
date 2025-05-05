import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

const Login = () => {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/login", {
        email: form.username,
        password: form.password,
      });

      const token = response.data.token;
      localStorage.setItem("token", token);
      alert("Đăng nhập thành công!");
      // navigate hoặc cập nhật UI tại đây
    } catch (err) {
      console.log(err);
      setError("Sai tài khoản hoặc mật khẩu!");
    }
  };

  return (
    <main className="container-lg">
      <div className="row">
        <div className="offset-md-3 col-md-6 border rounded shadow p-3">
          <h1 className="fs-2 mb-4">Login</h1>

          <div className="px-2 mb-3">
            <div className="g_id_signin" data-type="standard">
              <div className="S9gUrf-YoZ4jf" style={{ position: "relative" }}>
                <iframe
                  src="https://accounts.google.com/gsi/button?type=standard&client_id=YOUR_GOOGLE_CLIENT_ID"
                  allow="identity-credentials-get"
                  title="Google Login Button"
                  style={{
                    display: "block",
                    height: "44px",
                    width: "100%",
                    border: "none",
                  }}
                ></iframe>
              </div>
            </div>
            <div className="text-danger d-none" id="google-signin-error">
              An error happened, please try again later!
            </div>
          </div>

          <hr />
          <h4>Or enter your password</h4>
          {error && <div className="text-danger">{error}</div>}

          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="_username" className="form-label required">
                Username or Email
              </label>
              <input
                type="text"
                id="_username"
                name="username"
                value={form.username}
                onChange={handleChange}
                required
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="_password" className="form-label required">
                Password
              </label>
              <input
                type="password"
                id="_password"
                name="password"
                value={form.password}
                onChange={handleChange}
                required
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <button type="submit" className="btn btn-success">
                Submit
              </button>
            </div>
          </form>

          <p className="pt-3">
            Forgot your password? <Link to="/forgot-password">Click here</Link>
          </p>
          <p>
            Haven't had an account? <Link to="/register">Register here</Link>
          </p>
        </div>
      </div>
    </main>
  );
};

export default Login;
