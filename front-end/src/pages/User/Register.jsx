import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [formData, setFormData] = useState({
    nickname: "",
    email: "",
    password: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Gửi yêu cầu đăng ký tới API backend
      const response = await axios.post(
        "http://localhost:8080/api/auth/register",
        {
          username: formData.nickname,
          email: formData.email,
          password: formData.password,
        },
        {
          headers: {
            "Content-Type": "application/json",
            // Nếu cần gửi token thì thêm dòng dưới:
            // "Authorization": `Bearer ${yourAccessToken}`
          },
        }
      );
      window.location.reload();
      // Nếu đăng ký thành công, chuyển hướng tới trang đăng nhập
      navigate("/login");
    } catch (err) {
      console.log(err.response.data.message);
      // Nếu có lỗi, hiển thị thông báo lỗi
      setError(err.response.data.message);
    }
  };

  return (
    <>
      <main className="container-lg">
        <div className="message"></div>
        <div className="row mb-3">
          <div className="offset-md-3 col-sm-6 border rounded shadow p-3">
            <h1 className="fs-2 mb-4">Create an account</h1>
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label htmlFor="nickname" className="form-label required">
                  Nickname
                </label>
                <input
                  type="text"
                  id="nickname"
                  name="nickname"
                  value={formData.nickname}
                  onChange={handleInputChange}
                  required
                  className="form-control"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="email" className="form-label required">
                  Email
                </label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  required
                  className="form-control"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label required">
                  Password
                </label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  required
                  className="form-control"
                />
              </div>
              {error && <div className="text-danger">{error}</div>}
              <div className="mb-3">
                <button type="submit" className="btn-success btn">
                  Submit
                </button>
              </div>
            </form>
            <p className="pt-3">
              <span className="align-middle">
                Had an account already? <a href="/login">Login here</a>
              </span>
            </p>
          </div>
        </div>
      </main>
    </>
  );
};

export default Register;
