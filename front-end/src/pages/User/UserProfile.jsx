import { Link } from "react-router-dom";

const UserProfile = () => {
  return (
    <>
      <main className="container-lg">
        <div className="message"></div>

        <h2>Account information</h2>
        <table className="table table-bordered">
          <tbody>
            <tr>
              <th>Email</th>
              <td>
                trunghy2k4111@gmail.com
                <Link className="btn btn-sm btn-link" to="/user/edit-email">
                  Edit
                </Link>
              </td>
            </tr>
            <tr>
              <th>Display name</th>
              <td className="d-flex align-items-center">
                jimmy
                <Link
                  className="btn btn-sm btn-link"
                  to="/user/edit-display-name"
                >
                  Edit
                </Link>
              </td>
            </tr>
            <tr>
              <th>Google login</th>
              <td>
                <span className="badge bg-success">Yes</span>
                <Link
                  to="/user/social-account/google/disconnect"
                  onclick="return confirm('You will not be able to login using Google, do you still want to continue?')"
                  className="btn btn-sm btn-link text-secondary"
                  title="Disconnect your dailydictation.com account with your Google account."
                >
                  Disconnect
                </Link>
              </td>
            </tr>
            <tr>
              <th>Join date</th>
              <td>2025-02-12</td>
            </tr>
            <tr>
              <th>Total days</th>
              <td>49</td>
            </tr>
            <tr>
              <th>Active days</th>
              <td>25</td>
            </tr>
            <tr>
              <th>Inactive days</th>
              <td>24</td>
            </tr>
            <tr>
              <th>Active hours</th>
              <td>39 hours 31 minutes</td>
            </tr>
          </tbody>
        </table>
      </main>
    </>
  );
};
export default UserProfile;
