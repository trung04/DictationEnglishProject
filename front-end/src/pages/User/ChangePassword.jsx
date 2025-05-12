import { useState } from "react";
import axios from "axios";

const ChangePassword = ({ userData }) => {
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = localStorage.getItem("token"); // giả sử JWT token được lưu ở đây

      const response = await axios.post(
        "http://localhost:8080/api/user/update-password", // đổi thành URL thực tế nếu cần
        { newPassword: newPassword },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setMessage("Password changed successfully.");
    } catch (error) {
      console.log(error);
      setMessage("Failed to change password.");
    }
  };

  return (
    <>
      <main className="container-lg">
        <h1 className="mb-4">Change password</h1>

        <div className="row">
          <div className="col-lg-4">
            <div className="p-3 border shadow">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label
                    htmlFor="change_password_newPassword"
                    className="form-label required"
                  >
                    New password
                  </label>
                  <input
                    type="password"
                    id="change_password_newPassword"
                    name="newPassword"
                    required
                    className="form-control"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                  />
                </div>
                <div className="mb-3">
                  <button type="submit" className="btn btn-primary">
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
          <h1 className="message ">{message}</h1>
        </div>
      </main>
    </>
  );
};

export default ChangePassword;
